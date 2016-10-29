package james.monochrome.data.tiles;

import android.content.Context;
import android.view.MotionEvent;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class TreeTileData extends TileData {

    public TreeTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_TREE, position);
    }

    @Override
    public void onTouch(MotionEvent event) {
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
