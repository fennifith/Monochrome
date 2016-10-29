package james.monochrome.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.data.items.ItemData;
import james.monochrome.utils.ItemUtils;

public class ProfileDialog extends AppCompatDialog {

    public ProfileDialog(Context context) {
        super(context, R.style.AppTheme_Dialog_FullScreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_profile);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 3));

        List<ItemData> holdingItems = new ArrayList<>();
        List<ItemData> items = ItemUtils.getHoldingItems(getContext());
        for (ItemData item : items) {
            if (!item.isUseless()) holdingItems.add(item);
        }

        recycler.setAdapter(new ItemAdapter(getContext(), holdingItems, null));
    }
}
