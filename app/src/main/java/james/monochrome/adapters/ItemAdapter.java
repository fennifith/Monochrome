package james.monochrome.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.items.ItemData;
import james.monochrome.dialogs.ItemDialog;
import james.monochrome.utils.StaticUtils;
import james.monochrome.views.TileView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private Monochrome monochrome;
    private List<ItemTypeData> itemTypes;
    private Boolean isChest;

    private Typeface typeface;

    public ItemAdapter(Context context, List<ItemData> items, @Nullable Boolean isChest) {
        this.context = context;
        this.isChest = isChest;
        monochrome = (Monochrome) context.getApplicationContext();

        Collections.sort(items, new Comparator<ItemData>() {
            @Override
            public int compare(ItemData o1, ItemData o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        itemTypes = new ArrayList<>();
        for (ItemData item : items) {
            if (itemTypes.size() > 0 && itemTypes.get(itemTypes.size() - 1).key.equals(item.getKey()))
                itemTypes.get(itemTypes.size() - 1).addItem(item);
            else itemTypes.add(new ItemTypeData(item));
        }

        typeface = StaticUtils.getTypeface(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tile, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemTypeData itemType = itemTypes.get(position);

        ((TileView) holder.v.findViewById(R.id.tile)).setTile(itemType.tile);

        TextView title = (TextView) holder.v.findViewById(R.id.title);
        title.setTypeface(typeface);
        title.setText(itemType.name);

        TextView number = (TextView) holder.v.findViewById(R.id.number);
        number.setTypeface(typeface);
        number.setText(String.format(monochrome.getString(R.string.item_number), itemType.items.size()));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemTypeData itemType = itemTypes.get(holder.getAdapterPosition());

                if (isChest != null) {
                    ItemData item = itemType.items.get(0);
                    if (isChest) item.moveToHolding();
                    else item.moveToChest();
                } else itemType.items.get(0).onUse();
            }
        });

        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ItemTypeData itemType = itemTypes.get(holder.getAdapterPosition());
                if (itemType.items.size() > 0)
                    new ItemDialog(context, itemType.items.get(0)).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
        }
    }

    private class ItemTypeData {

        String name, description, key;
        int[][] tile;
        List<ItemData> items;

        ItemTypeData(ItemData item) {
            name = item.getName();
            description = item.getDescription();
            key = item.getKey();
            tile = item.getTile();
            items = new ArrayList<>();
            addItem(item);
        }

        void addItem(ItemData item) {
            if (!item.isUseless()) items.add(item);
        }
    }
}
