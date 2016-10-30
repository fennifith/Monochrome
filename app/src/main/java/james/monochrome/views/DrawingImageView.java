package james.monochrome.views;

import android.content.Context;
import android.util.AttributeSet;

public class DrawingImageView extends SquareImageView {

    public DrawingImageView(Context context) {
        super(context);
    }

    public DrawingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        size = (size / 100) * 100;
        setMeasuredDimension(size, size);
    }
}
