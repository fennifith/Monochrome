package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;

public class DoorTileData extends TileData {

    private String mapKey;

    public DoorTileData(Context context, int[][] tile, String mapKey, PositionData position) {
        super(context, tile, position);
        this.mapKey = mapKey;
    }

    @Override
    public void onTouch() {
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
