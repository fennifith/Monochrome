package james.monochrome.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.data.items.ItemData;
import james.monochrome.utils.ItemUtils;
import jp.wasabeef.blurry.Blurry;

public class ItemsDialog extends AppCompatDialog {

    private Blurry.ImageComposer image;

    private RecyclerView holding;
    private ItemAdapter holdingAdapter;

    public ItemsDialog(Context context, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        this.image = image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chest);

        image.into(((ImageView) findViewById(R.id.background)));

        View overlay = findViewById(R.id.overlay);
        overlay.setBackgroundColor(Color.argb(100, 200, 200, 200));
        overlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) dismiss();
                return false;
            }
        });

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "VT323-Regular.ttf");
        ((TextView) findViewById(R.id.titleHolding)).setTypeface(typeface);
        ((TextView) findViewById(R.id.titleChest)).setTypeface(typeface);

        holding = (RecyclerView) findViewById(R.id.holding);
        holding.setLayoutManager(new GridLayoutManager(getContext(), 3));

        List<ItemData> holdingItems = new ArrayList<>();
        for (ItemData item : ItemUtils.getHoldingItems(getContext())) {
            if (!item.isUseless()) holdingItems.add(item);
        }

        holdingAdapter = new ItemAdapter(getContext(), holdingItems, false);
        holding.setAdapter(holdingAdapter);

        findViewById(R.id.chestLayout).setVisibility(View.GONE);
    }
}
