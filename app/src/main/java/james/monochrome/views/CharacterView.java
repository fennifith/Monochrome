package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;

import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class CharacterView extends DrawingImageView {

    private String mapKey;
    private SceneryData scenery;
    private List<CharacterData> characters;
    private List<ItemData> items;
    private int[][] light;
    private int baseLight;

    private Paint paint;
    private int[][] tile = TileUtils.TILE_CHARACTER;
    private int characterX, characterY, tileSize, pixelSize, offsetY;

    private Handler handler;
    private Runnable runnable;

    private Monochrome monochrome;

    public CharacterView(Context context) {
        super(context);
        init();
    }

    public CharacterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CharacterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        monochrome = (Monochrome) getContext().getApplicationContext();

        paint = new Paint();
        paint.setAntiAlias(true);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (offsetY < 1) offsetY = 1;
                else offsetY = 0;
                invalidate();

                handler.postDelayed(this, 500);
            }
        };

        handler.postDelayed(runnable, 500);
    }

    public void setScenery(String mapKey, SceneryData scenery, List<CharacterData> characters, List<ItemData> items) {
        this.mapKey = mapKey;
        this.scenery = scenery;
        this.characters = characters;
        this.items = items;

        baseLight = MapUtils.getBaseLight(mapKey);
        makeLight();
        invalidate();
    }

    public int getCharacterX() {
        return characterX;
    }

    public int getCharacterY() {
        return characterY;
    }

    public PositionData getPosition() {
        return new PositionData(mapKey, (scenery.getX() * 10) + characterX, (scenery.getY() * 10) + characterY);
    }

    public void moveUp() {
        List<TileData> tiles = MapUtils.getTilesAt(mapKey, scenery, characters, items, characterX, characterY - 1);

        if (MapUtils.isValidPosition(mapKey, scenery, characters, items, characterX, characterY - 1)) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterY--;
            for (TileData tile : tiles) {
                tile.onEnter();
            }
        } else {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            for (TileData tile : tiles) {
                tile.onTouch();
            }
        }

        this.tile = TileUtils.TILE_CHARACTER_BACK;
        makeLight();
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void moveDown() {
        List<TileData> tiles = MapUtils.getTilesAt(mapKey, scenery, characters, items, characterX, characterY + 1);

        if (MapUtils.isValidPosition(mapKey, scenery, characters, items, characterX, characterY + 1)) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterY++;
            for (TileData tile : tiles) {
                tile.onEnter();
            }
        } else {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            for (TileData tile : tiles) {
                tile.onTouch();
            }
        }

        this.tile = TileUtils.TILE_CHARACTER;
        makeLight();
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void moveLeft() {
        List<TileData> tiles = MapUtils.getTilesAt(mapKey, scenery, characters, items, characterX - 1, characterY);

        if (MapUtils.isValidPosition(mapKey, scenery, characters, items, characterX - 1, characterY)) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterX--;
            for (TileData tile : tiles) {
                tile.onEnter();
            }
        } else {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            for (TileData tile : tiles) {
                tile.onTouch();
            }
        }

        this.tile = TileUtils.TILE_CHARACTER_LEFT;
        makeLight();
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void moveRight() {
        List<TileData> tiles = MapUtils.getTilesAt(mapKey, scenery, characters, items, characterX + 1, characterY);

        if (MapUtils.isValidPosition(mapKey, scenery, characters, items, characterX + 1, characterY)) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterX++;
            for (TileData tile : tiles) {
                tile.onEnter();
            }
        } else {
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
            for (TileData tile : tiles) {
                tile.onTouch();
            }
        }

        this.tile = TileUtils.TILE_CHARACTER_RIGHT;
        makeLight();
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void setCharacterPosition(int x, int y) {
        PositionData position = MapUtils.getEmptyPosition(scenery, characters, items, new PositionData(mapKey, scenery.getX(), scenery.getY(), x, y));
        characterX = position.getTileX();
        characterY = position.getTileY();

        makeLight();
        invalidate();
    }

    public void makeLight() {
        light = new int[scenery.getTiles().size()][];
        for (int i = 0; i < scenery.getTiles().size(); i++) {
            light[i] = new int[scenery.getTiles().get(i).size()];
            for (int i2 = 0; i2 < scenery.getTiles().get(i).size(); i2++) {
                light[i][i2] = baseLight;
            }
        }

        for (int i = 0; i < scenery.getTiles().size(); i++) {
            List<TileData> row = scenery.getTiles().get(i);
            for (int i2 = 0; i2 < row.size(); i2++) {
                int tileLight = (i == characterY && i2 == characterX) ? 5 : 0;
                for (TileData tile : MapUtils.getTilesAt(mapKey, scenery, characters, items, i2, i)) {
                    tileLight += tile.getLight();
                }

                if (tileLight > 0) {
                    addLight(i, i2, tileLight);
                    if (tileLight > 1) {
                        addLight(i - 1, i2, tileLight / 2);
                        addLight(i + 1, i2, tileLight / 2);
                        addLight(i, i2 - 1, tileLight / 2);
                        addLight(i, i2 + 1, tileLight / 2);
                        if (tileLight > 2) {
                            addLight(i - 2, i2, tileLight / 3);
                            addLight(i + 2, i2, tileLight / 3);
                            addLight(i, i2 - 2, tileLight / 3);
                            addLight(i, i2 + 2, tileLight / 3);
                            addLight(i - 1, i2 - 1, tileLight / 3);
                            addLight(i + 1, i2 - 1, tileLight / 3);
                            addLight(i - 1, i2 + 1, tileLight / 3);
                            addLight(i + 1, i2 + 1, tileLight / 3);
                            if (tileLight > 3) {
                                addLight(i - 3, i2, tileLight / 4);
                                addLight(i + 3, i2, tileLight / 4);
                                addLight(i, i2 - 3, tileLight / 4);
                                addLight(i, i2 + 3, tileLight / 4);
                                addLight(i - 1, i2 - 2, tileLight / 4);
                                addLight(i + 1, i2 - 2, tileLight / 4);
                                addLight(i - 1, i2 + 2, tileLight / 4);
                                addLight(i + 1, i2 + 2, tileLight / 4);
                                addLight(i - 2, i2 - 1, tileLight / 4);
                                addLight(i + 2, i2 - 1, tileLight / 4);
                                addLight(i - 2, i2 + 1, tileLight / 4);
                                addLight(i + 2, i2 + 1, tileLight / 4);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addLight(int y, int x, int addLight) {
        if (x >= 0 && y >= 0 && x < 10 && y < 10) {
            for (TileData tile : MapUtils.getTilesAt(mapKey, scenery, characters, items, x, y)) {
                addLight = Math.min(addLight, tile.getMaxLight());
            }

            light[y][x] += addLight;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (scenery == null || characters == null) return;

        tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        pixelSize = tileSize / 10;

        for (CharacterData character : characters) {
            PositionData position = character.getPosition();
            if (position.getSceneX() == scenery.getX() && position.getSceneY() == scenery.getY())
                canvas.drawBitmap(monochrome.getBitmap(character.getTile(), tileSize, paint), tileSize * position.getTileX(), (tileSize * position.getTileY()) - (pixelSize * offsetY), paint);
        }

        canvas.drawBitmap(monochrome.getBitmap(tile, tileSize, paint), tileSize * characterX, (tileSize * characterY) + (pixelSize * offsetY), paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < light.length; i++) {
            for (int i2 = 0; i2 < light[i].length; i2++) {
                paint.setAlpha(255 - (int) (Math.min(light[i][i2], 10) * 25.5));
                canvas.drawRect(tileSize * i2, tileSize * i, tileSize * (i2 + 1), tileSize * (i + 1), paint);
            }
        }

        paint.setAlpha(255);
    }
}
