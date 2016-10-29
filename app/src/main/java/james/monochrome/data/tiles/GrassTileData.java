package james.monochrome.data.tiles;

import android.content.Context;
import android.view.MotionEvent;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class GrassTileData extends TileData {

    public GrassTileData(Context context, int thickness, PositionData position) {
        super(context, TileUtils.getRandomGrass(1, thickness), position);
    }

    @Override
    public void onTouch(MotionEvent event) {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
