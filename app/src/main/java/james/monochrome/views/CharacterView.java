package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;

import james.monochrome.data.PositionData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.TileUtils;

public class CharacterView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private int[][] tile = TileUtils.TILE_CHARACTER;
    private int characterX, characterY, offsetY;
    private boolean isHidden;

    private Handler handler;
    private Runnable runnable;

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

    public void setScenery(SceneryData scenery) {
        this.scenery = scenery;
        isHidden = false;
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
        this.tile = TileUtils.TILE_CHARACTER_BACK;
        invalidate();

        if (!tile.canEnter() && !tile.canWalkOver())
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
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
        this.tile = TileUtils.TILE_CHARACTER;
        invalidate();

        if (!tile.canEnter() && !tile.canWalkOver())
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
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

        if (!tile.canEnter() && !tile.canWalkOver())
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

        isHidden = tile.canEnter() && !tile.canWalkOver();
        this.tile = TileUtils.TILE_CHARACTER_LEFT;
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
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

        if (!tile.canEnter() && !tile.canWalkOver())
            performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

        isHidden = tile.canEnter() && !tile.canWalkOver();
        this.tile = TileUtils.TILE_CHARACTER_RIGHT;
        invalidate();

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 3000);
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

            if (characterX + scale < 10 && characterY + scale < 10) {
                characterX += scale;
                characterY += scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else {
                    characterX -= scale;
                    characterY -= scale;
                }
            }

            if (characterX - scale >= 0 && characterY - scale >= 0) {
                characterX -= scale;
                characterY -= scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else {
                    characterX += scale;
                    characterY += scale;
                }
            }

            if (characterX + scale < 10 && characterY - scale >= 0) {
                characterX += scale;
                characterY -= scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else {
                    characterX -= scale;
                    characterY += scale;
                }
            }

            if (characterX - scale >= 0 && characterY + scale < 10) {
                characterX -= scale;
                characterY += scale;
                tile = scenery.getTile(characterX, characterY);
                if (tile != null && tile.canWalkOver()) break;
                else {
                    characterX += scale;
                    characterY -= scale;
                }
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

        TileUtils.drawTile(getContext(), canvas, paint, tileSize * characterX, (tileSize * characterY) + (pixelSize * offsetY), pixelSize, TileUtils.getTransparentTile(tile));
    }
}
