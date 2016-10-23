package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class HouseTileData extends TileData {

    private boolean isHouseOpen;

    public HouseTileData(Context context, boolean isHouseOpen, int x, int y) {
        super(context, TileUtils.getTile(TileUtils.TILE_HOUSE), x, y);
        this.isHouseOpen = isHouseOpen;
    }

    public void setOpen(boolean isHouseOpen) {
        this.isHouseOpen = isHouseOpen;
    }

    @Override
    public void onTouch() {
        if (!isHouseOpen) StaticUtils.makeToast(getContext(), "The house is locked.").show();
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
