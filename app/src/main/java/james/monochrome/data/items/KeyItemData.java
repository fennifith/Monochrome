package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
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
    String getId() {
        return MapUtils.getTileId(getPosition());
    }

    @Override
    String getKey() {
        return MapUtils.KEY_ITEM_KEY;
    }

    @Override
    boolean hasConstantPosition() {
        return true;
    }
}
