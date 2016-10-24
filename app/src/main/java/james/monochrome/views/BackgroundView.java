package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import java.util.List;

import james.monochrome.utils.TileUtils;

public class BackgroundView extends SquareImageView {

    private List<List<Integer>> backgroundTile;
    private Paint paint;

    public BackgroundView(Context context) {
        super(context);
        init();
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setTile(List<List<Integer>> backgroundTile) {
        this.backgroundTile = backgroundTile;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (backgroundTile == null) return;

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;
        int pixelSize = tileSize / 10;

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                int x = tileSize * column, y = tileSize * row;
                TileUtils.drawTile(getContext(), canvas, paint, x, y, pixelSize, backgroundTile);
            }
        }
    }
}
