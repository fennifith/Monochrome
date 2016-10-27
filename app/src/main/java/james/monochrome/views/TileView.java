package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class TileView extends AppCompatImageView {

    private static final int[][] IMAGE_TITLE = new int[][]{
            new int[]{0},
            new int[]{0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            new int[]{0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            new int[]{0},
            new int[]{1},
            new int[]{0},
    };

    private Paint paint;
    private int[][] tile = IMAGE_TITLE;

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
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    public void setTile(int[][] tile) {
        this.tile = tile;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int pixelHeight = canvas.getHeight() / tile.length;
        for (int i = 0; i < tile.length; i++) {
            int[] row = tile[i];
            int pixelWidth = canvas.getWidth() / row.length;
            for (int i2 = 0; i2 < row.length; i2++) {
                int x = i2 * pixelWidth, y = i * pixelHeight;
                if (tile[i][i2] > 0)
                    canvas.drawRect(x, y, x + pixelWidth, y + pixelHeight, paint);
            }
        }
    }
}
