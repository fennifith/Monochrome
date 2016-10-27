package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
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

public class CharacterView extends SquareImageView {

    private String mapKey;
    private SceneryData scenery;
    private List<CharacterData> characters;
    private List<ItemData> items;

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
                else offsetY = -1;
                setY(getY() + (offsetY * pixelSize));

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
        if (tile == null) return;

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
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void moveDown() {
        List<TileData> tiles = MapUtils.getTilesAt(mapKey, scenery, characters, items, characterX, characterY + 1);
        if (tile == null) return;

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
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
    }

    public void setCharacterPosition(int x, int y) {
        PositionData position = MapUtils.getEmptyPosition(scenery, characters, items, new PositionData(mapKey, scenery.getX(), scenery.getY(), x, y));
        characterX = position.getTileX();
        characterY = position.getTileY();

        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (scenery == null || characters == null) return;

        tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        pixelSize = tileSize / 10;

        for (CharacterData character : characters) {
            PositionData position = character.getPosition();
            canvas.drawBitmap(monochrome.getBitmap(character.getTile(), tileSize, paint), tileSize * position.getTileX(), tileSize * position.getTileY(), paint);
        }

        canvas.drawBitmap(monochrome.getBitmap(tile, tileSize, paint), tileSize * characterX, tileSize * characterY, paint);
    }
}
