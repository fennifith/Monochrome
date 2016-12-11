package james.monochrome.activities;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.util.ArrayMap;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.klinker.android.peekview.PeekViewActivity;

import java.util.List;
import java.util.Map;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.dialogs.ChestDialog;
import james.monochrome.dialogs.ItemsDialog;
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
import jp.wasabeef.blurry.Blurry;

public class MainActivity extends PeekViewActivity implements View.OnTouchListener, Monochrome.OnSomethingHappenedListener, Monochrome.DialogListener {

    public static final int REQUEST_SETTINGS = 12543;

    public static final String
            KEY_READ_TUTORIAL = "tutorialRead",
            KEY_SWIPED = "tutorialSwiped";

    private CoordinatorLayout root;

    private BackgroundView background;
    private SceneryView scenery;
    private CharacterView character;

    private SquareImageView soundView;
    private boolean isMuted;

    private List<RowData> map;
    private String mapKey;

    private List<CharacterData> characters;
    private List<ItemData> items;
    private int sceneX, sceneY;

    private Map<String, PositionData> mapPositions;
    private float downX, downY;

    private Monochrome monochrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monochrome = (Monochrome) getApplicationContext();
        monochrome.addListener(this);
        monochrome.setDialogListener(this);

        root = (CoordinatorLayout) findViewById(R.id.root);
        background = (BackgroundView) findViewById(R.id.background);
        scenery = (SceneryView) findViewById(R.id.scenery);
        character = (CharacterView) findViewById(R.id.character);

        SquareImageView pauseView = (SquareImageView) findViewById(R.id.pause);
        soundView = (SquareImageView) findViewById(R.id.sound);
        SquareImageView itemsView = (SquareImageView) findViewById(R.id.items);
        SquareImageView mapView = (SquareImageView) findViewById(R.id.map);

        mapPositions = new ArrayMap<>();
        setMap(monochrome.getString(MapUtils.KEY_MAP, MapUtils.KEY_MAP_DEFAULT));

        pauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StartScreenDialog(MainActivity.this, getBlurryImage()).show();
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
                new ItemsDialog(MainActivity.this, getBlurryImage()).show();
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MapDialog(MainActivity.this, mapKey, character.getPosition(), getBlurryImage()).show();
            }
        });

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dpad", false))
            findViewById(R.id.buttonLayout).setVisibility(View.VISIBLE);

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveUp();
            }
        });

        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveDown();
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveLeft();
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveRight();
            }
        });

        root.setOnTouchListener(this);

        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                StartScreenDialog dialog = new StartScreenDialog(MainActivity.this, getBlurryImage());
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (!monochrome.getBoolean(KEY_READ_TUTORIAL, false)) {
                            monochrome.makeDialog(
                                    null,
                                    getString(R.string.msg_tutorial),
                                    getString(R.string.action_ok),
                                    new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        }
                                    },
                                    null,
                                    null
                            );
                            monochrome.putBoolean(KEY_READ_TUTORIAL, true);
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onPause() {
        monochrome.setDialogListener(null);
        super.onPause();
    }

    @Override
    protected void onResume() {
        monochrome.setDialogListener(this);
        super.onResume();
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
            sceneX = monochrome.getInt(MapUtils.KEY_SCENE_X + mapKey, 0);
            sceneY = monochrome.getInt(MapUtils.KEY_SCENE_Y + mapKey, 0);
            characterX = monochrome.getInt(MapUtils.KEY_CHARACTER_X + mapKey, 0);
            characterY = monochrome.getInt(MapUtils.KEY_CHARACTER_Y + mapKey, 0);
        }

        characters = MapUtils.getCharacters(this, mapKey);
        items = ItemUtils.getItems(this, mapKey);

        setScenery(map.get(sceneY).getScenery(sceneX));
        character.setCharacterPosition(characterX, characterY);
        background.setMap(mapKey);
    }

    public void setScenery(SceneryData data) {
        character.setScenery(mapKey, data, characters, items);
        scenery.setScenery(mapKey, data, items);
    }

    public void moveUp() {
        if (character.getCharacterY() == 0 && sceneY > 0) {
            sceneY -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 9);
        } else character.moveUp();

        PositionData position = character.getPosition();
        mapPositions.put(mapKey, position);
        monochrome.onPositionChange(position);
    }

    public void moveDown() {
        if (character.getCharacterY() == 9 && sceneY < map.size() - 1) {
            sceneY += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 0);
        } else character.moveDown();

        PositionData position = character.getPosition();
        mapPositions.put(mapKey, position);
        monochrome.onPositionChange(position);
    }

    public void moveLeft() {
        if (character.getCharacterX() == 0 && sceneX > 0) {
            sceneX -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(9, character.getCharacterY());
        } else character.moveLeft();

        PositionData position = character.getPosition();
        mapPositions.put(mapKey, position);
        monochrome.onPositionChange(position);
    }

    public void moveRight() {
        if (character.getCharacterX() == 9 && sceneX < map.get(sceneY).getRow().size() - 1) {
            sceneX += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(0, character.getCharacterY());
        } else character.moveRight();

        PositionData position = character.getPosition();
        mapPositions.put(mapKey, position);
        monochrome.onPositionChange(position);
    }

    private Blurry.ImageComposer getBlurryImage() {
        return Blurry.with(this).capture(findViewById(android.R.id.content));
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
                    if (dx < 0) moveRight();
                    else moveLeft();
                    monochrome.putBoolean(KEY_SWIPED, true);
                } else if (Math.abs(dy) > 100) {
                    if (dy < 0) moveDown();
                    else moveUp();
                    monochrome.putBoolean(KEY_SWIPED, true);
                } else if (!monochrome.getBoolean(KEY_SWIPED, false))
                    makeToast(getString(R.string.msg_tutorial_move));
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
    public void onRequestMapChange(final PositionData position) {
        ValueAnimator animator = ValueAnimator.ofFloat(1, 0);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float alpha = (float) valueAnimator.getAnimatedValue();
                background.setAlpha(alpha);
                scenery.setAlpha(alpha);
                character.setAlpha(alpha);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mapPositions.put(position.getMapKey(), position);
                setMap(position.getMapKey());

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(500);
                valueAnimator.setStartDelay(100);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float alpha = (float) valueAnimator.getAnimatedValue();
                        background.setAlpha(alpha);
                        scenery.setAlpha(alpha);
                        character.setAlpha(alpha);
                    }
                });
                valueAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    @Override
    public void onPositionChange(PositionData position) {
    }

    @Override
    public void onRequestSave() {
        monochrome.putString(MapUtils.KEY_MAP, mapKey);
        for (String mapKey : mapPositions.keySet()) {
            PositionData position = mapPositions.get(mapKey);
            monochrome.putInt(MapUtils.KEY_SCENE_X + mapKey, position.getSceneX());
            monochrome.putInt(MapUtils.KEY_SCENE_Y + mapKey, position.getSceneY());
            monochrome.putInt(MapUtils.KEY_CHARACTER_X + mapKey, position.getTileX());
            monochrome.putInt(MapUtils.KEY_CHARACTER_Y + mapKey, position.getTileY());
        }
    }

    @Override
    public void onRequestShake() {
        scenery.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }

    @Override
    public void onOpenChest() {
        ChestDialog dialog = new ChestDialog(this, getBlurryImage());
        dialog.addOnDismissListener(new ChestDialog.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                monochrome.onCloseChest();
            }
        });
        dialog.show();
    }

    @Override
    public void onCloseChest() {

    }

    @Override
    public void onItemMoved(ItemData item) {

    }

    @Override
    public void makeToast(String message) {
        Snackbar.make(root, message, message.length() > 20 ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void makeDialog(@Nullable String title, @Nullable String message, String primaryText, MaterialDialog.SingleButtonCallback primaryListener, @Nullable String secondaryText, @Nullable MaterialDialog.SingleButtonCallback secondaryListener) {
        Typeface typeface = StaticUtils.getTypeface(this);

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .typeface(typeface, typeface);

        if (title != null) builder.title(title);
        if (message != null) builder.content(message);

        builder.positiveText(primaryText);
        builder.onPositive(primaryListener);

        if (secondaryText != null && secondaryListener != null) {
            builder.negativeText(secondaryText);
            builder.onNegative(secondaryListener);
        }

        builder.negativeColor(Color.BLACK).neutralColor(Color.BLACK).positiveColor(Color.BLACK);

        builder.show();
    }

    @Override
    public void makeItemConfirmationDialog(ItemData item, String message, MaterialDialog.SingleButtonCallback confirmationListener) {
        makeDialog(String.format(getString(R.string.action_use_item), item.getName()), message, getString(R.string.action_ok), confirmationListener, getString(R.string.action_cancel), new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        });
    }
}
