package james.monochrome.dialogs;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.klinker.android.peekview.PeekViewActivity;

import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.StaticUtils;
import jp.wasabeef.blurry.Blurry;

public class ItemsDialog extends AppCompatDialog {

    private PeekViewActivity activity;
    private Blurry.ImageComposer image;

    public ItemsDialog(PeekViewActivity activity, Blurry.ImageComposer image) {
        super(activity, R.style.AppTheme_Dialog_FullScreen_Fading);
        this.activity = activity;
        this.image = image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chest);

        image.into(((ImageView) findViewById(R.id.background)));

        View overlay = findViewById(R.id.overlay);
        overlay.setBackgroundColor(Color.argb(100, 200, 200, 200));
        overlay.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) dismiss();
            return false;
        });

        Typeface typeface = StaticUtils.getTypeface(getContext());
        ((TextView) findViewById(R.id.titleHolding)).setTypeface(typeface);
        ((TextView) findViewById(R.id.titleChest)).setTypeface(typeface);

        RecyclerView holding = findViewById(R.id.holding);
        holding.setLayoutManager(new GridLayoutManager(getContext(), 3));

        ItemAdapter holdingAdapter = new ItemAdapter(activity, ItemUtils.getHoldingItems(getContext()), null);
        holding.setAdapter(holdingAdapter);

        findViewById(R.id.chestLayout).setVisibility(View.GONE);
    }
}
