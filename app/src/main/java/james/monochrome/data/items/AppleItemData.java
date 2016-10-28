package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class AppleItemData extends ItemData {

    public AppleItemData(Context context, boolean hasPickedUp, boolean isHolding, boolean isUseless) {
        super(context, TileUtils.TILE_APPLE, hasPickedUp, isHolding, isUseless);
    }

    public AppleItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_APPLE, position);
    }

    @Override
    public String getName() {
        return getContext().getString(R.string.item_apple);
    }

    @Override
    String getId() {
        return MapUtils.KEY_ITEM_APPLE;
    }

    @Override
    public String getKey() {
        return MapUtils.KEY_ITEM_APPLE;
    }

    @Override
    boolean hasConstantPosition() {
        return false;
    }
}
