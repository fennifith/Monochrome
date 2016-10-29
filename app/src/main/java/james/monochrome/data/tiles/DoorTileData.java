package james.monochrome.data.tiles;

import android.content.Context;
import android.view.MotionEvent;

import james.monochrome.data.PositionData;

public class DoorTileData extends TileData {

    private String mapKey;

    public DoorTileData(Context context, int[][] tile, String mapKey, PositionData position) {
        super(context, tile, position);
        this.mapKey = mapKey;
    }

    @Override
    public void onTouch(MotionEvent event) {
        setMap(mapKey);
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
