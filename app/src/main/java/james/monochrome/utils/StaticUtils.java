package james.monochrome.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;

public class StaticUtils {

    public static float getPixelsFromDp(Context context, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "VT323-Regular.ttf");
    }
}
