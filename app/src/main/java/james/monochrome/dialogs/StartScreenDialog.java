package james.monochrome.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.ImageView;

import james.monochrome.R;
import james.monochrome.activities.MainActivity;
import james.monochrome.activities.SettingsActivity;
import jp.wasabeef.blurry.Blurry;
import me.jfenn.attribouter.Attribouter;

public class StartScreenDialog extends AppCompatDialog {

    private Activity activity;
    private Blurry.ImageComposer image;

    public StartScreenDialog(@NonNull Activity context, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        activity = context;
        this.image = image;
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_start);

        image.into((ImageView) findViewById(R.id.background));

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        findViewById(R.id.settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivityForResult(new Intent(getContext(), SettingsActivity.class), MainActivity.REQUEST_SETTINGS);
            }
        });

        findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attribouter.from(view.getContext()).show();
            }
        });
    }
}
