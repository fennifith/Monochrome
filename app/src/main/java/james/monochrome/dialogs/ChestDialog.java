package james.monochrome.dialogs;

import android.content.Context;
import android.content.DialogInterface;
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

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.StaticUtils;
import jp.wasabeef.blurry.Blurry;

public class ChestDialog extends AppCompatDialog implements Monochrome.OnSomethingHappenedListener {

    private Blurry.ImageComposer image;

    private RecyclerView holding, chest;
    private ItemAdapter holdingAdapter, chestAdapter;

    private Monochrome monochrome;
    private List<OnDismissListener> listeners;

    public ChestDialog(Context context, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        this.image = image;

        listeners = new ArrayList<>();
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                for (OnDismissListener listener : listeners) {
                    listener.onDismiss(dialogInterface);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chest);

        monochrome = (Monochrome) getContext().getApplicationContext();

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

        Typeface typeface = StaticUtils.getTypeface(getContext());
        ((TextView) findViewById(R.id.titleHolding)).setTypeface(typeface);
        ((TextView) findViewById(R.id.titleChest)).setTypeface(typeface);

        holding = (RecyclerView) findViewById(R.id.holding);
        holding.setLayoutManager(new GridLayoutManager(getContext(), 2));

        holdingAdapter = new ItemAdapter(getContext(), ItemUtils.getHoldingItems(getContext()), false);
        holding.setAdapter(holdingAdapter);

        chest = (RecyclerView) findViewById(R.id.chest);
        chest.setLayoutManager(new GridLayoutManager(getContext(), 2));

        chestAdapter = new ItemAdapter(getContext(), ItemUtils.getChestItems(getContext()), true);
        chest.setAdapter(chestAdapter);

        addOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                monochrome.removeListener(ChestDialog.this);
            }
        });

        monochrome.addListener(this);
    }

    @Override
    public void onTileChange(TileData tile) {
    }

    @Override
    public void onRequestMapChange(PositionData position) {
    }

    @Override
    public void onPositionChange(PositionData position) {
    }

    @Override
    public void onRequestSave() {
    }

    @Override
    public void onRequestShake() {
    }

    @Override
    public void onOpenChest() {
    }

    @Override
    public void onCloseChest() {
    }

    @Override
    public void onItemMoved(ItemData item) {
        if (holding != null && chest != null) {
            holdingAdapter = new ItemAdapter(getContext(), ItemUtils.getHoldingItems(getContext()), false);
            holding.setAdapter(holdingAdapter);

            chestAdapter = new ItemAdapter(getContext(), ItemUtils.getChestItems(getContext()), true);
            chest.setAdapter(chestAdapter);
        }
    }

    public void addOnDismissListener(OnDismissListener listener) {
        listeners.add(listener);
    }

    public interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }
}
