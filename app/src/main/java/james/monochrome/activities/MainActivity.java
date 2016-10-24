package james.monochrome.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;
import java.util.Map;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.dialogs.StartScreenDialog;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.views.BackgroundView;
import james.monochrome.views.CharacterView;
import james.monochrome.views.SceneryView;
import james.monochrome.views.SquareImageView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String
            KEY_READ_SIGN = "tutorialSign",
            KEY_SWIPED = "tutorialSwiped";

    private BackgroundView background;
    private SceneryView scenery;
    private CharacterView character;

    private SquareImageView pauseView, soundView, personView, mapView;
    private boolean isMuted;

    private List<RowData> map;
    private String mapKey;

    private SceneryData sceneryData;
    private int sceneX, sceneY;

    private Map<String, PositionData> mapPositions;
    private float downX, downY;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = (BackgroundView) findViewById(R.id.background);
        scenery = (SceneryView) findViewById(R.id.scenery);
        character = (CharacterView) findViewById(R.id.character);

        pauseView = (SquareImageView) findViewById(R.id.pause);
        soundView = (SquareImageView) findViewById(R.id.sound);
        personView = (SquareImageView) findViewById(R.id.person);
        mapView = (SquareImageView) findViewById(R.id.map);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
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

        personView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: implement google games stuff, add items view, etc
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: make a large map dialog thing
            }
        });

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveUp();
            }
        });

        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveDown();
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLeft();
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveRight();
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
                if (!prefs.getBoolean(KEY_READ_SIGN, false))
                    StaticUtils.makeToast(MainActivity.this, getString(R.string.msg_tutorial)).show();
            }
        });
        dialog.show();
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

        setScenery(map.get(sceneY).getScenery(sceneX));
        character.setCharacterPosition(characterX, characterY);
        background.setMap(mapKey);
    }

    public void setScenery(SceneryData data) {
        if (sceneryData != null) {
            for (List<TileData> row : sceneryData.getTiles()) {
                for (TileData tile : row) {
                    tile.setOnTileChangeListener(null);
                }
            }
        }

        sceneryData = data;

        TileData.OnTileChangeListener listener = new TileData.OnTileChangeListener() {
            @Override
            public void onTileChange(TileData tile) {
                scenery.invalidate();
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
            public String getMapKey() {
                return mapKey;
            }
        };

        for (List<TileData> row : sceneryData.getTiles()) {
            for (TileData tile : row) {
                tile.setOnTileChangeListener(listener);
            }
        }

        character.setScenery(data);
        scenery.setScenery(data);
    }

    public void moveUp() {
        if (character.getCharacterY() == 0 && sceneY > 0) {
            sceneY -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 9);
        } else character.moveUp();
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveDown() {
        if (character.getCharacterY() == 9 && sceneY < map.size() - 1) {
            sceneY += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(character.getCharacterX(), 0);
        } else character.moveDown();
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveLeft() {
        if (character.getCharacterX() == 0 && sceneX > 0) {
            sceneX -= 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(9, character.getCharacterY());
        } else character.moveLeft();
        mapPositions.put(mapKey, character.getPosition());
    }

    public void moveRight() {
        if (character.getCharacterX() == 9 && sceneX < map.get(sceneY).getRow().size() - 1) {
            sceneX += 1;

            setScenery(map.get(sceneY).getScenery(sceneX));
            character.setCharacterPosition(0, character.getCharacterY());
        } else character.moveRight();
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
                    if (dx < 0) moveRight();
                    else moveLeft();
                    prefs.edit().putBoolean(KEY_SWIPED, true).apply();
                } else if (Math.abs(dy) > 100) {
                    if (dy < 0) moveDown();
                    else moveUp();
                    prefs.edit().putBoolean(KEY_SWIPED, true).apply();
                } else if (!prefs.getBoolean(KEY_SWIPED, false))
                    StaticUtils.makeToast(this, getString(R.string.msg_tutorial_move)).show();
                break;
        }
        return false;
    }
}
