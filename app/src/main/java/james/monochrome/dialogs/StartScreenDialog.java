package james.monochrome.dialogs;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import james.monochrome.R;
import james.monochrome.activities.MainActivity;
import james.monochrome.activities.SettingsActivity;
import jp.wasabeef.blurry.Blurry;
import me.jfenn.attribouter.Attribouter;

public class StartScreenDialog extends AppCompatDialog {

    private AppCompatActivity activity;
    private Blurry.ImageComposer image;

    public StartScreenDialog(@NonNull AppCompatActivity context, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        activity = context;
        this.image = image;
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_start);

        image.into(findViewById(R.id.background));

        findViewById(R.id.start).setOnClickListener(v -> dismiss());

        findViewById(R.id.settings).setOnClickListener(v ->
                activity.startActivityForResult(
                        new Intent(getContext(), SettingsActivity.class), MainActivity.REQUEST_SETTINGS));

        findViewById(R.id.about).setOnClickListener(view -> Attribouter.from(view.getContext()).show());
    }
}
