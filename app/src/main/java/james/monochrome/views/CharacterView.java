package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class CharacterView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private int[][] tile = TileUtils.TILE_CHARACTER;
    private int characterX, characterY, tileSize, pixelSize, offsetY;

    private List<CharacterData> characters;

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

    public void setScenery(String mapKey, SceneryData scenery) {
        characters = MapUtils.getCharacters(getContext(), mapKey);

        this.scenery = scenery;
        invalidate();
    }

    public int getCharacterX() {
        return characterX;
    }

    public int getCharacterY() {
        return characterY;
    }

    public PositionData getPosition() {
        return new PositionData((scenery.getX() * 10) + characterX, (scenery.getY() * 10) + characterY);
    }

    public void moveUp() {
        List<TileData> tiles = getTilesAt(characterX, characterY - 1);
        if (tile == null) return;

        if (isValidPosition(characterX, characterY - 1)) {
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
        List<TileData> tiles = getTilesAt(characterX, characterY + 1);
        if (tile == null) return;

        if (isValidPosition(characterX, characterY + 1)) {
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
        List<TileData> tiles = getTilesAt(characterX - 1, characterY);

        if (isValidPosition(characterX - 1, characterY)) {
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
        List<TileData> tiles = getTilesAt(characterX + 1, characterY);

        if (isValidPosition(characterX + 1, characterY)) {
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
        characterX = x;
        characterY = y;

        for (int scale = 0; !isValidPosition(characterX, characterY) && scale < 10; scale++) {
            if (characterX - scale >= 0) {
                characterX -= scale;

                if (isValidPosition(characterX, characterY)) break;
                else characterX += scale;
            }

            if (characterX + scale < 10) {
                characterX += scale;

                if (isValidPosition(characterX, characterY)) break;
                else characterX -= scale;
            }

            if (characterY - scale >= 0) {
                characterY -= scale;

                if (isValidPosition(characterX, characterY)) break;
                else characterY += scale;
            }

            if (characterY + scale < 10) {
                characterY += scale;

                if (isValidPosition(characterX, characterY)) break;
                else characterY -= scale;
            }

            if (characterX + scale < 10 && characterY + scale < 10) {
                characterX += scale;
                characterY += scale;

                if (isValidPosition(characterX, characterY)) break;
                else {
                    characterX -= scale;
                    characterY -= scale;
                }
            }

            if (characterX - scale >= 0 && characterY - scale >= 0) {
                characterX -= scale;
                characterY -= scale;

                if (isValidPosition(characterX, characterY)) break;
                else {
                    characterX += scale;
                    characterY += scale;
                }
            }

            if (characterX + scale < 10 && characterY - scale >= 0) {
                characterX += scale;
                characterY -= scale;

                if (isValidPosition(characterX, characterY)) break;
                else {
                    characterX -= scale;
                    characterY += scale;
                }
            }

            if (characterX - scale >= 0 && characterY + scale < 10) {
                characterX -= scale;
                characterY += scale;

                if (isValidPosition(characterX, characterY)) break;
                else {
                    characterX += scale;
                    characterY -= scale;
                }
            }
        }

        invalidate();
    }

    private boolean isValidPosition(int x, int y) {
        if (scenery == null || characters == null) return false;

        for (TileData tile : getTilesAt(x, y)) {
            if (!tile.canEnter()) return false;
        }

        return true;
    }

    private List<TileData> getTilesAt(int x, int y) {
        List<TileData> tiles = new ArrayList<>();

        TileData tile = null;
        if (scenery != null) tile = scenery.getTile(x, y);
        if (tile != null) tiles.add(tile);

        if (characters != null) {
            for (CharacterData character : characters) {
                PositionData position = character.getPosition();
                if (position.getTileX() == x && position.getTileY() == y) tiles.add(character);
            }
        }

        return tiles;
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
