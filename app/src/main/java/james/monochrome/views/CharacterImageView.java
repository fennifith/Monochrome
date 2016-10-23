package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.R;
import james.monochrome.data.SceneryData;
import james.monochrome.data.TileData;
import james.monochrome.utils.TileUtils;

public class CharacterImageView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private int pixelSize, characterX, characterY;
    private boolean isHidden;

    public CharacterImageView(Context context) {
        super(context);
        init();
    }

    public CharacterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CharacterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
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
        }

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
        }

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
        }

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
        }

        isHidden = tile.canEnter() && !tile.canWalkOver();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (scenery == null || isHidden) return;

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        pixelSize = tileSize / 10;

        TileUtils.drawTile(getContext(), canvas, paint, tileSize * characterX, tileSize * characterY, pixelSize, TileUtils.getTransparentTile(TileUtils.TILE_CHARACTER));
    }
}
