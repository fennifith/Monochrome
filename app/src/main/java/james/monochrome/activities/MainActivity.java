package james.monochrome.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.klinker.android.peekview.PeekViewActivity;
import com.klinker.android.peekview.builder.Peek;
import com.klinker.android.peekview.callback.OnPeek;
import com.klinker.android.peekview.callback.SimpleOnPeek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.data.PositionData;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.dialogs.MapDialog;
import james.monochrome.dialogs.StartScreenDialog;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.OnClickTouchListener;
import james.monochrome.utils.StaticUtils;
import james.monochrome.views.BackgroundView;
import james.monochrome.views.CharacterView;
import james.monochrome.views.SceneryView;
import james.monochrome.views.SquareImageView;

public class MainActivity extends PeekViewActivity implements View.OnTouchListener, Monochrome.OnSomethingHappenedListener {

    public static final int REQUEST_SETTINGS = 12543;

    public static final String
            KEY_READ_TUTORIAL = "tutorialRead",
            KEY_SWIPED = "tutorialSwiped";

    private BackgroundView background;
    private SceneryView scenery;
    private CharacterView character;

    private SquareImageView pauseView, soundView, itemsView, mapView;
    private boolean isMuted;

    private List<RowData> map;
    private String mapKey;

    private SceneryData sceneryData;
    private List<CharacterData> characters;
    private List<ItemData> items;
    private int sceneX, sceneY;

    private Map<String, PositionData> mapPositions;
    private float downX, downY;

    private SharedPreferences prefs;
    private Monochrome monochrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        monochrome = (Monochrome) getApplicationContext();
        monochrome.addListener(this);

        background = (BackgroundView) findViewById(R.id.background);
        scenery = (SceneryView) findViewById(R.id.scenery);
        character = (CharacterView) findViewById(R.id.character);

        pauseView = (SquareImageView) findViewById(R.id.pause);
        soundView = (SquareImageView) findViewById(R.id.sound);
        itemsView = (SquareImageView) findViewById(R.id.items);
        mapView = (SquareImageView) findViewById(R.id.map);

        mapPositions = new ArrayMap<>();
        setMap(prefs.getString(MapUtils.KEY_MAP, MapUtils.KEY_MAP_DEFAULT));

        pauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StartScreenDialog(MainActivity.this).show();
            }
        });

        soundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMuted) {
                    isMuted = false;
                    soundView.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_sound_on, getTheme()));
                } else {
                    isMuted = true;
                    soundView.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_sound_off, getTheme()));
                }
            }
        });

        itemsView.setOnTouchListener(new OnClickTouchListener() {
            @Override
            public void onClick(View view, MotionEvent event) {
                Peek.into(R.layout.dialog_chest, new SimpleOnPeek() {
                    @Override
                    public void onInflated(View rootView) {
                        rootView.findViewById(R.id.chestLayout).setVisibility(View.GONE);

                        Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(), "VT323-Regular.ttf");
                        ((TextView) rootView.findViewById(R.id.titleHolding)).setTypeface(typeface);
                        ((TextView) rootView.findViewById(R.id.titleChest)).setTypeface(typeface);

                        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.holding);
                        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                        List<ItemData> holdingItems = new ArrayList<>();
                        List<ItemData> items = ItemUtils.getHoldingItems(MainActivity.this);
                        for (ItemData item : items) {
                            if (!item.isUseless()) holdingItems.add(item);
                        }

                        recycler.setAdapter(new ItemAdapter(MainActivity.this, holdingItems, null));
                    }
                }).with(StaticUtils.getPeekViewOptions(MainActivity.this)).show(MainActivity.this, event);
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MapDialog(MainActivity.this, mapKey, character.getPosition()).show();
            }
        });

        if (prefs.getBoolean("dpad", false))
            findViewById(R.id.buttonLayout).setVisibility(View.VISIBLE);

        findViewById(R.id.up).setOnTouchListener(new OnClickTouchListener() {
            @Override
            public void onClick(View view, MotionEvent event) {
                moveUp(event);
            }
        });

        findViewById(R.id.down).setOnTouchListener(new OnClickTouchListener() {
            @Override
            public void onClick(View view, MotionEvent event) {
                moveDown(event);
            }
        });

        findViewById(R.id.left).setOnTouchListener(new OnClickTouchListener() {
            @Override
            public void onClick(View view, MotionEvent event) {
                moveLeft(event);
            }
        });

        findViewById(R.id.right).setOnTouchListener(new OnClickTouchListener() {
            @Override
            public void onClick(View view, MotionEvent event) {
                moveRight(event);
            }
        });

        FrameLayout buttonLayout = (FrameLayout) findViewById(R.id.buttonLayout);

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) buttonLayout.getLayoutParams();
        layoutParams.width = Math.abs(size.x - size.y) / 2;
        layoutParams.height = Math.abs(size.x - size.y) / 2;
        findViewById(R.id.buttonLayout).setLayoutParams(layoutParams);

        findViewById(R.id.root).setOnTouchListener(this);

        StartScreenDialog dialog = new StartScreenDialog(this);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!prefs.getBoolean(KEY_READ_TUTORIAL, false)) {
                    StaticUtils.makeDialog(
                            MainActivity.this,
                            null,
                            getString(R.string.msg_tutorial),
                            getString(R.string.action_ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    prefs.edit().putBoolean(KEY_READ_TUTORIAL, true).apply();
                                }
                            },
                            null,
                            null
                    ).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        monochrome.removeListener(this);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SETTINGS) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void setMap(String mapKey) {
        this.mapKey = mapKey;
        map = MapUtils.getMapList(this, mapKey);

        int characterX, characterY;

        if (mapPositions.containsKey(mapKey)) {
            PositionData position = mapPositions.get(mapKey);
            sceneX = position.getSceneX();
            sceneY = position.getSceneY();
            characterX = position.getTileX();
            characterY = position.getTileY();
        } else {
            sceneX = prefs.getInt(MapUtils.KEY_SCENE_X + mapKey, 0);
            sceneY = prefs.getInt(MapUtils.KEY_SCENE_Y + mapKey, 0);
            characterX = prefs.getInt(MapUtils.KEY_CHARACTER_X + mapKey, 0);
            characterY = prefs.getInt(MapUtils.KEY_CHARACTER_Y + mapKey, 0);
        }

        characters = MapUtils.getCharacters(this, mapKey);
        items = ItemUtils.getItems(this, mapKey);

        setScenery(map.get(sceneY).getScenery(sceneX));
        character.setCharacterPosition(characterX, characterY);
        background.setMap(mapKey);
    }

    public void setScenery(SceneryData data) {
        sceneryData = data;

        character.setScenery(mapKey, data, characters, items);
        scenery.setScenery(mapKey, data, items);
    }

    public void moveUp(MotionEvent event) {
        if (character.getCharacterY() == 0 && sceneY > 0) {
            sceneY -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 9);
        } else character.moveUp(event);
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveDown(MotionEvent event) {
        if (character.getCharacterY() == 9 && sceneY < map.size() - 1) {
            sceneY += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 0);
        } else character.moveDown(event);
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveLeft(MotionEvent event) {
        if (character.getCharacterX() == 0 && sceneX > 0) {
            sceneX -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(9, character.getCharacterY());
        } else character.moveLeft(event);
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveRight(MotionEvent event) {
        if (character.getCharacterX() == 9 && sceneX < map.get(sceneY).getRow().size() - 1) {
            sceneX += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(0, character.getCharacterY());
        } else character.moveRight(event);
        mapPositions.put(mapKey, character.getPosition());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float dx = downX - event.getX();
                float dy = downY - event.getY();

                if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > 100) {
                    if (dx < 0) moveRight(event);
                    else moveLeft(event);
                    prefs.edit().putBoolean(KEY_SWIPED, true).apply();
                } else if (Math.abs(dy) > 100) {
                    if (dy < 0) moveDown(event);
                    else moveUp(event);
                    prefs.edit().putBoolean(KEY_SWIPED, true).apply();
                } else if (!prefs.getBoolean(KEY_SWIPED, false))
                    StaticUtils.makeToast(this, getString(R.string.msg_tutorial_move)).show();
                break;
        }
        return false;
    }

    @Override
    public void onTileChange(TileData tile) {
        scenery.invalidate();
        character.invalidate();
    }

    @Override
    public void onRequestTileKeyChange(TileData tile, int tileKey) {
        int[][][][] map = MapUtils.getMap(MainActivity.this, mapKey);
        PositionData position = tile.getPosition();
        map[position.getSceneY()][position.getSceneX()][position.getTileY()][position.getTileX()] = tileKey;
        MapUtils.saveMap(MainActivity.this, mapKey, map);

        setMap(mapKey);
    }

    @Override
    public void onRequestMapChange(String mapKey) {
        setMap(mapKey);
    }

    @Override
    public void onRequestPositionSave() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MapUtils.KEY_MAP, mapKey);
        for (String mapKey : mapPositions.keySet()) {
            PositionData position = mapPositions.get(mapKey);
            editor.putInt(MapUtils.KEY_SCENE_X + mapKey, position.getSceneX());
            editor.putInt(MapUtils.KEY_SCENE_Y + mapKey, position.getSceneY());
            editor.putInt(MapUtils.KEY_CHARACTER_X + mapKey, position.getTileX());
            editor.putInt(MapUtils.KEY_CHARACTER_Y + mapKey, position.getTileY());
        }
        editor.apply();
    }

    @Override
    public void onRequestShake() {
        scenery.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }

    @Override
    public void onOpenChest(MotionEvent event) {
        Peek.into(R.layout.dialog_chest, new OnPeek() {

            private RecyclerView holding, chest;
            private ItemAdapter holdingAdapter, chestAdapter;

            private Monochrome.OnSomethingHappenedListener listener;

            @Override
            public void onInflated(View rootView) {
                Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(), "VT323-Regular.ttf");
                ((TextView) rootView.findViewById(R.id.titleHolding)).setTypeface(typeface);
                ((TextView) rootView.findViewById(R.id.titleChest)).setTypeface(typeface);

                holding = (RecyclerView) rootView.findViewById(R.id.holding);
                holding.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

                List<ItemData> holdingItems = new ArrayList<>();
                for (ItemData item : ItemUtils.getHoldingItems(MainActivity.this)) {
                    if (!item.isUseless()) holdingItems.add(item);
                }

                holdingAdapter = new ItemAdapter(MainActivity.this, holdingItems, false);
                holding.setAdapter(holdingAdapter);

                chest = (RecyclerView) rootView.findViewById(R.id.chest);
                chest.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

                List<ItemData> chestItems = new ArrayList<>();
                for (ItemData item : ItemUtils.getChestItems(MainActivity.this)) {
                    if (!item.isUseless()) chestItems.add(item);
                }

                chestAdapter = new ItemAdapter(MainActivity.this, chestItems, true);
                chest.setAdapter(chestAdapter);

                listener = new Monochrome.OnSomethingHappenedListener() {
                    @Override
                    public void onTileChange(TileData tile) {
                    }

                    @Override
                    public void onRequestTileKeyChange(TileData tile, int tileKey) {
                    }

                    @Override
                    public void onRequestMapChange(String mapKey) {
                    }

                    @Override
                    public void onRequestPositionSave() {
                    }

                    @Override
                    public void onRequestShake() {
                    }

                    @Override
                    public void onOpenChest(MotionEvent event) {
                    }

                    @Override
                    public void onItemMoved(ItemData item) {
                        if (holding != null && chest != null) {
                            List<ItemData> holdingItems = new ArrayList<>();
                            for (ItemData holdingItem : ItemUtils.getHoldingItems(MainActivity.this)) {
                                if (!holdingItem.isUseless()) holdingItems.add(holdingItem);
                            }

                            holdingAdapter = new ItemAdapter(MainActivity.this, holdingItems, false);
                            holding.setAdapter(holdingAdapter);

                            List<ItemData> chestItems = new ArrayList<>();
                            for (ItemData chestItem : ItemUtils.getChestItems(MainActivity.this)) {
                                if (!chestItem.isUseless()) chestItems.add(chestItem);
                            }

                            chestAdapter = new ItemAdapter(MainActivity.this, chestItems, true);
                            chest.setAdapter(chestAdapter);
                        }
                    }
                };

                monochrome.addListener(listener);
            }

            @Override
            public void shown() {
            }

            @Override
            public void dismissed() {
                if (listener != null) monochrome.removeListener(listener);
            }
        }).with(StaticUtils.getPeekViewOptions(MainActivity.this).setFullScreenPeek(true)).show(MainActivity.this, event);
    }

    @Override
    public void onItemMoved(ItemData item) {

    }
}
