package james.monochrome.dialogs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import james.monochrome.R;
import james.monochrome.activities.AboutActivity;
import james.monochrome.activities.SettingsActivity;

public class StartScreenDialog extends AppCompatDialog {

    public StartScreenDialog(@NonNull Context context) {
        super(context, R.style.AppTheme_Dialog_FullScreen);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_start);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });

        findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });
    }
}
