package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.utils.TileUtils;

public class GrassTileData extends TileData {

    public GrassTileData(Context context, int x, int y) {
        super(context, TileUtils.getRandomGrass(), x, y);
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
