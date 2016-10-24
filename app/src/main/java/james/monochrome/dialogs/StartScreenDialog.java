package james.monochrome.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import james.monochrome.R;

import static james.monochrome.activities.MainActivity.KEY_READ_SIGN;

public class StartScreenDialog extends AppCompatDialog {

    public StartScreenDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_Dialog_FullScreen);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_start);

        AppCompatButton start = (AppCompatButton) findViewById(R.id.start);
        if (PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(KEY_READ_SIGN, false))
            start.setText(R.string.action_resume);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
