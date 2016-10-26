package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;

public abstract class ItemData extends TileData {

    public ItemData(Context context, int[][] tile, PositionData position) {
        super(context, tile, position);
    }
    
}
