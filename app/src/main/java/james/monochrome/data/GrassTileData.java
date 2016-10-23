package james.monochrome.data;

import james.monochrome.utils.TileUtils;

public class GrassTileData extends TileData {

    public GrassTileData() {
        super(TileUtils.getRandomGrass());
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canWalkOver() {
        return true;
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
