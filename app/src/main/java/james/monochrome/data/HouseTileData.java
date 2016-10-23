package james.monochrome.data;

import james.monochrome.utils.TileUtils;

public class HouseTileData extends TileData {

    private boolean isHouseOpen;

    public HouseTileData(boolean isHouseOpen) {
        super(TileUtils.getTile(TileUtils.TILE_HOUSE));
        this.isHouseOpen = isHouseOpen;
    }

    public void setOpen(boolean isHouseOpen) {
        this.isHouseOpen = isHouseOpen;
    }

    @Override
    public void onEnter() {
        if (isHouseOpen) setTile(TileUtils.getTile(TileUtils.TILE_HOUSE_OPEN));
    }

    @Override
    public void onExit() {
        setTile(TileUtils.getTile(TileUtils.TILE_HOUSE));
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public boolean canEnter() {
        return isHouseOpen;
    }
}
