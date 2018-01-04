package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class TitleView extends AppCompatImageView {

    private static final int[][] IMAGE_TITLE = new int[][]{
            new int[]{0},
            new int[]{0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0},
            new int[]{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
            new int[]{0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0},
            new int[]{0},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{0},
    };

    private int[][] drawnTitle = new int[][]{
            new int[1],
            new int[43],
            new int[43],
            new int[43],
            new int[1],
            new int[43],
            new int[1]
    };

    private Paint paint;

    public TitleView(Context context) {
        super(context);
        init();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    private boolean isFinished() {
        for (int i = 0; i < IMAGE_TITLE.length; i++) {
            for (int i2 = 0; i2 < IMAGE_TITLE[i].length; i2++) {
                if (drawnTitle[i][i2] != IMAGE_TITLE[i][i2])
                    return false;
            }
        }

        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        int copied = 0;
        while (copied == 0) {
            int copyY = (int) (Math.random() * IMAGE_TITLE.length);
            int copyX = (int) (Math.random() * IMAGE_TITLE[copyY].length);
            if (drawnTitle[copyY][copyX] != 1) {
                copied = IMAGE_TITLE[copyY][copyX];
                drawnTitle[copyY][copyX] = copied;
            }
        }

        int pixelHeight = canvas.getHeight() / drawnTitle.length;
        for (int i = 0; i < drawnTitle.length; i++) {
            int[] row = drawnTitle[i];
            int pixelWidth = canvas.getWidth() / row.length;
            for (int i2 = 0; i2 < row.length; i2++) {
                int x = i2 * pixelWidth, y = i * pixelHeight;
                if (drawnTitle[i][i2] > 0)
                    canvas.drawRect(x, y, x + pixelWidth, y + pixelHeight, paint);
            }
        }

        if (!isFinished())
            postInvalidate();
    }
}
