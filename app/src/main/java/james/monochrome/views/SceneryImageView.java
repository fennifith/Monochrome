package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.R;
import james.monochrome.data.SceneryData;
import james.monochrome.data.TileData;
import james.monochrome.utils.TileUtils;

public class SceneryImageView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private int tileSize, pixelSize;

    public SceneryImageView(Context context) {
        super(context);
        init();
    }

    public SceneryImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SceneryImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (scenery == null) return;

        tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        pixelSize = tileSize / 10;

        List<List<TileData>> tiles = scenery.getTiles();
        for (int row = 0; row < tiles.size(); row++) {
            List<TileData> tileRow = tiles.get(row);
            for (int column = 0; column < tileRow.size(); column++) {
                int x = tileSize * column, y = tileSize * row;
                TileUtils.drawTile(getContext(), canvas, paint, x, y, pixelSize, tileRow.get(column).getTile());
            }
        }
    }
}
