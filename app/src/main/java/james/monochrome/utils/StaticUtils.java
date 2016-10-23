package james.monochrome.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import james.monochrome.R;

public class StaticUtils {

    public static float getPixelsFromDp(Context context, int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static Toast makeToast(Context context, String message) {
        Toast toast = new Toast(context);

        View v = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        ((TextView) v.findViewById(R.id.text)).setText(message);
        toast.setView(v);

        toast.setDuration(message.length() > 140 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);

        return toast;
    }
}
