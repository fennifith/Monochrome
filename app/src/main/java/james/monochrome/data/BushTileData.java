package james.monochrome.data;

import java.util.List;

import james.monochrome.utils.TileUtils;

public class BushTileData extends TileData {

    public BushTileData() {
        super(TileUtils.getTile(TileUtils.TILE_BUSH));
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
