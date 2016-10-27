package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class AppleItemData extends ItemData {

    public AppleItemData(Context context, boolean hasPickedUp, boolean isHolding) {
        super(context, TileUtils.TILE_APPLE, hasPickedUp, isHolding);
    }

    public AppleItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_APPLE, position);
    }

    @Override
    String getName() {
        return "Apple";
    }

    @Override
    String getId() {
        return MapUtils.KEY_ITEM_APPLE;
    }

    @Override
    String getKey() {
        return MapUtils.KEY_ITEM_APPLE;
    }

    @Override
    boolean hasConstantPosition() {
        return false;
    }
}
