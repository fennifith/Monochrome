package james.monochrome.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Html;
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
        TextView text = (TextView) v.findViewById(R.id.text);
        text.setText(message);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "VT323-Regular.ttf");
        if (typeface != null) text.setTypeface(typeface);

        toast.setView(v);

        toast.setDuration(message.length() > 140 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);

        return toast;
    }

    public static AlertDialog makeDialog(Context context, @Nullable String title, @Nullable String message, String primaryText, DialogInterface.OnClickListener primaryListener, @Nullable String secondaryText, @Nullable DialogInterface.OnClickListener secondaryListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (message != null) builder.setMessage(message);
        builder.setPositiveButton(primaryText, primaryListener);
        if (secondaryText != null && secondaryListener != null)
            builder.setNegativeButton(secondaryText, secondaryListener);

        AlertDialog dialog = builder.show();

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "VT323-Regular.ttf");
        TextView messageView = null;
        try {
            messageView = (TextView) dialog.findViewById(android.R.id.message);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        if (messageView != null) {
            messageView.setTypeface(typeface);
            if (title != null) {
                String string = "<h1>" + title + "</h1>";
                if (message != null) string += message;
                messageView.setText(Html.fromHtml(string));
            }
        }

        TextView button1 = null, button2 = null, button3 = null;
        try {
            button1 = (TextView) dialog.findViewById(android.R.id.button1);
            button2 = (TextView) dialog.findViewById(android.R.id.button2);
            button3 = (TextView) dialog.findViewById(android.R.id.button3);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        if (button1 != null) button1.setTypeface(typeface);
        if (button2 != null) button2.setTypeface(typeface);
        if (button3 != null) button3.setTypeface(typeface);
        return dialog;
    }
}
