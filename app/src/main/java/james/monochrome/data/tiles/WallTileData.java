package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;

public class WallTileData extends TileData {

    public WallTileData(Context context, int[][] tile, PositionData position) {
        super(context, tile, position);
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
