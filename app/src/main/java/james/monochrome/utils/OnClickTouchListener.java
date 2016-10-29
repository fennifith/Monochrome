package james.monochrome.utils;

import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;

public abstract class OnClickTouchListener implements View.OnTouchListener {

    private long time;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                time = Calendar.getInstance().getTimeInMillis();
                break;
            case MotionEvent.ACTION_UP:
                if (Calendar.getInstance().getTimeInMillis() - time < 200)
                    onClick(view, event);
        }
        return true;
    }

    public abstract void onClick(View view, MotionEvent event);
}
