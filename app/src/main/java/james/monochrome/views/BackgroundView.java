package james.monochrome.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import james.monochrome.Monochrome;
import james.monochrome.utils.MapUtils;

public class BackgroundView extends SquareImageView {

    private String mapKey;
    private Paint paint;

    private Monochrome monochrome;

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
        monochrome = (Monochrome) getContext().getApplicationContext();

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setMap(String mapKey) {
        this.mapKey = mapKey;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mapKey == null) return;

        int tileSize = Math.min(canvas.getWidth(), canvas.getHeight()) / 10;

        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                canvas.drawBitmap(monochrome.getBitmap(MapUtils.getBackground(mapKey), tileSize, paint), tileSize * column, tileSize * row, paint);
            }
        }
    }
}
