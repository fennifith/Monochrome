package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class PumpkinItemData extends ItemData {

    public PumpkinItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_PUMPKIN, position);
    }

    @Override
    public String getName() {
        return getContext().getString(R.string.item_pumpkin);
    }

    @Override
    String getId() {
        return MapUtils.getTileId(getPosition());
    }

    @Override
    public String getKey() {
        return MapUtils.KEY_ITEM_PUMPKIN;
    }

    @Override
    boolean hasConstantPosition() {
        return true;
    }
}
