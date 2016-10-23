package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.data.SceneryData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.TileUtils;

public class CharacterView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private int characterX, characterY;
    private boolean isHidden;

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
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setScenery(SceneryData scenery) {
        this.scenery = scenery;

        TileData.OnTileChangeListener listener = new TileData.OnTileChangeListener() {
            @Override
            public void onTileChange(TileData tile) {
                invalidate();
            }
        };

        for (List<TileData> row : scenery.getTiles()) {
            for (TileData tile : row) {
                tile.setOnTileChangeListener(listener);
            }
        }

        invalidate();
    }

    public int getCharacterX() {
        return characterX;
    }

    public int getCharacterY() {
        return characterY;
    }

    public void moveUp() {
        TileData tile = scenery.getTile(characterX, characterY - 1);
        if (tile == null) return;

        if (tile.canEnter() || tile.canWalkOver()) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterY--;
        }

        if (tile.canEnter()) {
            tile.onEnter();
        } else tile.onTouch();

        isHidden = tile.canEnter() && !tile.canWalkOver();
        invalidate();
    }

    public void moveDown() {
        TileData tile = scenery.getTile(characterX, characterY + 1);
        if (tile == null) return;

        if (tile.canEnter() || tile.canWalkOver()) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterY++;
        }

        if (tile.canEnter()) {
            tile.onEnter();
        } else tile.onTouch();

        isHidden = tile.canEnter() && !tile.canWalkOver();
        invalidate();
    }

    public void moveLeft() {
        TileData tile = scenery.getTile(characterX - 1, characterY);
        if (tile == null) return;

        if (tile.canEnter() || tile.canWalkOver()) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterX--;
        }

        if (tile.canEnter()) {
            tile.onEnter();
        } else tile.onTouch();

        isHidden = tile.canEnter() && !tile.canWalkOver();
        invalidate();
    }

    public void moveRight() {
        TileData tile = scenery.getTile(characterX + 1, characterY);
        if (tile == null) return;

        if (tile.canEnter() || tile.canWalkOver()) {
            TileData prevTile = scenery.getTile(characterX, characterY);
            if (prevTile != null && prevTile.canEnter()) prevTile.onExit();

            characterX++;
        }

        if (tile.canEnter()) {
            tile.onEnter();
        } else tile.onTouch();

        isHidden = tile.canEnter() && !tile.canWalkOver();
        invalidate();
    }

    public void setCharacterPosition(int x, int y) {
        characterX = x;
        characterY = y;

        TileData tile = scenery.getTile(characterX, characterY);
        for (int scale = 0; (tile == null || !tile.canWalkOver()) && scale < 10; scale++) {
            if (characterX - scale >= 0) {
                characterX -= scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else characterX += scale;
            }

            if (characterX + scale < 10) {
                characterX += scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else characterX -= scale;
            }

            if (characterY - scale >= 0) {
                characterY -= scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else characterY += scale;
            }

            if (characterY + scale < 10) {
                characterY += scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else characterY -= scale;
            }
        }

        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (scenery == null || isHidden) return;

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        int pixelSize = tileSize / 10;

        TileUtils.drawTile(getContext(), canvas, paint, tileSize * characterX, tileSize * characterY, pixelSize, TileUtils.getTransparentTile(TileUtils.TILE_CHARACTER));
    }
}
