package james.monochrome.data.tiles;

import android.content.Context;
import android.view.MotionEvent;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class ChestTileData extends TileData {

    public ChestTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_CHEST, position);
    }

    @Override
    public void onTouch(MotionEvent event) {
        setTile(TileUtils.TILE_CHEST_OPEN);
        ((Monochrome) getContext().getApplicationContext()).onOpenChest(event);
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
