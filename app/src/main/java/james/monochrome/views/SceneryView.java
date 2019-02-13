package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;

public class SceneryView extends DrawingImageView {

    private String mapKey;
    private SceneryData scenery;
    private List<ItemData> items;

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

    public void setScenery(String mapKey, SceneryData scenery, List<ItemData> items) {
        this.mapKey = mapKey;
        this.scenery = scenery;
        this.items = items;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (scenery == null || items == null) return;

        int tileSize = Math.min(getWidth(), getHeight()) / 10;

        List<List<TileData>> tiles = scenery.getTiles();
        for (int row = 0; row < tiles.size(); row++) {
            List<TileData> tileRow = tiles.get(row);
            for (int column = 0; column < tileRow.size(); column++) {
                canvas.drawBitmap(monochrome.getBitmap(tileRow.get(column).getTile(), tileSize, paint), tileSize * column, tileSize * row, paint);
            }
        }

        for (ItemData item : items) {
            PositionData position = item.getPosition();
            if (position != null && position.getMapKey().equals(mapKey) && position.getSceneX() == scenery.getX() && position.getSceneY() == scenery.getY() && !item.hasPickedUp())
                canvas.drawBitmap(monochrome.getBitmap(item.getTile(), tileSize, paint), tileSize * position.getTileX(), tileSize * position.getTileY(), paint);
        }
    }
}
