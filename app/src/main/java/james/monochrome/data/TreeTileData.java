package james.monochrome.data;

import java.util.List;

import james.monochrome.utils.TileUtils;

public class TreeTileData extends TileData {

    public TreeTileData() {
        super(TileUtils.getTile(TileUtils.TILE_TREE));
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
