package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.utils.TileUtils;

public class TreeTileData extends TileData {

    public TreeTileData(Context context, int x, int y) {
        super(context, TileUtils.getTile(TileUtils.TILE_TREE), x, y);
    }

    @Override
    public void onTouch() {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
