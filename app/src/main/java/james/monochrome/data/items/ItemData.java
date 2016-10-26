package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;

public abstract class ItemData extends TileData {

    private PositionData position;

    public ItemData(Context context, int[][] tile) {
        super(context, tile, null);
    }

    public ItemData(Context context, int[][] tile, PositionData position) {
        super(context, tile, null);
        this.position = position;
    }

    public void setPosition(PositionData position) {
        this.position = position;
    }

    @Override
    public PositionData getPosition() {
        return position;
    }

    @Override
    public void onTouch() {
        if (position != null) {

        }
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
