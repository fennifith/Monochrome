package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;

public abstract class TileData {

    private Context context;
    private int[][] tile;
    private PositionData position;

    public TileData(Context context, int[][] tile, PositionData position) {
        this.context = context;
        this.tile = tile;
        this.position = position;
    }

    public Context getContext() {
        return context;
    }

    public int[][] getTile() {
        return tile;
    }

    public void setTile(int[][] tile) {
        this.tile = tile;
        ((Monochrome) getContext().getApplicationContext()).onTileChange(this);
    }

    void setTileKey(int tileKey) {
        ((Monochrome) getContext().getApplicationContext()).requestTileKeyChange(this, tileKey);
    }

    void setMap(String mapKey) {
        ((Monochrome) getContext().getApplicationContext()).requestMapChange(mapKey);
    }

    void savePosition() {
        ((Monochrome) getContext().getApplicationContext()).requestPositionSave();
    }

    void shake() {
        ((Monochrome) getContext().getApplicationContext()).requestShake();
    }

    public PositionData getPosition() {
        return position;
    }

    public abstract void onTouch();

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canEnter();
}
