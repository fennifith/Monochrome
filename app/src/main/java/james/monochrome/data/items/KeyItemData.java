package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class KeyItemData extends ItemData {

    private String id;

    public KeyItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_KEY, position);
        if (position != null) id = MapUtils.getTileId(position);
    }

    public KeyItemData(Context context, String id) {
        super(context, TileUtils.TILE_KEY, null);
        this.id = id;
    }

    @Override
    public String getName() {
        return getContext().getString(R.string.item_key);
    }

    @Override
    public String getDescription() {
        return getContext().getString(R.string.item_key_desc);
    }

    @Override
    String getId() {
        return ItemUtils.KEY_ITEM_KEY;
    }

    @Override
    public String getKey() {
        return ItemUtils.KEY_ITEM_KEY;
    }

    @Override
    public int getVolume() {
        return 5;
    }

    @Override
    boolean hasConstantPosition() {
        return true;
    }

    @Override
    public void onUse() {
    }

    @Override
    public void setUseless() {
        putBoolean(KEY_PICKED_UP, false);
        if (id != null) putBoolean(id + KEY_USELESS, true);
        getMonochrome().onItemMoved(this);
    }

    @Override
    public boolean isUseful() {
        return id == null || !getBoolean(id + KEY_USELESS, false);
    }
}
