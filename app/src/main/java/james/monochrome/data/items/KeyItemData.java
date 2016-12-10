package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class KeyItemData extends ItemData {

    public KeyItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_KEY, position);
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
        if (getPosition() != null)
            putBoolean(MapUtils.getTileId(getPosition()) + KEY_USELESS, true);
        getMonochrome().onItemMoved(this);
    }

    @Override
    public boolean isUseless() {
        return getPosition() != null && getBoolean(MapUtils.getTileId(getPosition()) + KEY_USELESS, false);
    }
}
