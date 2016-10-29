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
        ((Monochrome) getContext().getApplicationContext()).onOpenChest(event);
        setTile(TileUtils.TILE_CHEST_OPEN);
    }

    @Override
    public void onCloseChest() {
        setTile(TileUtils.TILE_CHEST);
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
