package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.utils.TileUtils;

public class BushTileData extends TileData {

    public BushTileData(Context context, int x, int y) {
        super(context, TileUtils.getTile(TileUtils.TILE_BUSH), x, y);
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
