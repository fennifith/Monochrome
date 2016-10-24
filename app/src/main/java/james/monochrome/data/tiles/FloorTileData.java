package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class FloorTileData extends TileData {

    public FloorTileData(Context context, int[][] tile, PositionData position) {
        super(context, TileUtils.getTile(tile), position);
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
        return true;
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
