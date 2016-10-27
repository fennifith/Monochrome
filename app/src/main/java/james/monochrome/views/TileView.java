package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import james.monochrome.Monochrome;

public class TileView extends SquareImageView {

    private Paint paint;
    private int[][] tile;

    private Monochrome monochrome;

    public TileView(Context context) {
        super(context);
        init();
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        monochrome = (Monochrome) getContext().getApplicationContext();

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setTile(int[][] tile) {
        this.tile = tile;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight());
        if (tile != null)
            canvas.drawBitmap(monochrome.getBitmap(tile, tileSize, paint), 0, 0, paint);
    }
}
