package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.data.SceneryData;
import james.monochrome.data.tiles.TileData;

public class SceneryView extends SquareImageView {

    private SceneryData scenery;
    private Paint paint;

    private Monochrome monochrome;

    public SceneryView(Context context) {
        super(context);
        init();
    }

    public SceneryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SceneryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        monochrome = (Monochrome) getContext().getApplicationContext();

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setScenery(SceneryData scenery) {
        this.scenery = scenery;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (scenery == null) return;

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        int pixelSize = tileSize / 10;

        List<List<TileData>> tiles = scenery.getTiles();
        for (int row = 0; row < tiles.size(); row++) {
            List<TileData> tileRow = tiles.get(row);
            for (int column = 0; column < tileRow.size(); column++) {
                int x = tileSize * column, y = tileSize * row;
                canvas.drawBitmap(monochrome.getBitmap(tileRow.get(column).getTile(), tileSize, paint), x, y, paint);
            }
        }
    }
}
