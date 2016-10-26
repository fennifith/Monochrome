package james.monochrome.data.items;

import android.content.Context;
import android.support.annotation.Nullable;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class KeyItemData extends ItemData {

    public KeyItemData(Context context, @Nullable PositionData position) {
        super(context, TileUtils.TILE_KEY, position);
    }

}
