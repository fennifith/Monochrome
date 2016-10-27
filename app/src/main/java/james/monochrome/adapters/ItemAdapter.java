package james.monochrome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.items.ItemData;
import james.monochrome.views.TileView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Monochrome monochrome;
    private List<ItemData> items;

    public ItemAdapter(Context context, List<ItemData> items) {
        this.items = items;
        monochrome = (Monochrome) context.getApplicationContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tile, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((TileView) holder.v.findViewById(R.id.tile)).setTile(items.get(position).getTile());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View v;

        public ViewHolder(View itemView) {
            super(itemView);
            v = itemView;
        }
    }
}
