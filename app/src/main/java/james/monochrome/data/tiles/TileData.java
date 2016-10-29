package james.monochrome.data.tiles;

import android.content.Context;
import android.view.MotionEvent;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;

public abstract class TileData implements Monochrome.OnSomethingHappenedListener {

    private Context context;
    private Monochrome monochrome;
    private int[][] tile;
    private PositionData position;

    public TileData(Context context, int[][] tile, PositionData position) {
        this.context = context;
        this.tile = tile;
        this.position = position;

        monochrome = (Monochrome) context.getApplicationContext();
        monochrome.addListener(this);
    }

    public Context getContext() {
        return context;
    }

    public int[][] getTile() {
        return tile;
    }

    public void setTile(int[][] tile) {
        this.tile = tile;
        monochrome.onTileChange(this);
    }

    void setMap(String mapKey) {
        monochrome.requestMapChange(mapKey);
    }

    void savePosition() {
        monochrome.requestPositionSave();
    }

    void shake() {
        monochrome.requestShake();
    }

    public PositionData getPosition() {
        return position;
    }

    public abstract void onTouch(MotionEvent event);

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canEnter();

    @Override
    public void onCloseChest() {
    }

    @Override
    public void onItemMoved(ItemData item) {
    }

    @Override
    public void onOpenChest(MotionEvent event) {
    }

    @Override
    public void onRequestMapChange(String mapKey) {
        monochrome.removeListener(this);
    }

    @Override
    public void onRequestPositionSave() {
    }

    @Override
    public void onRequestShake() {
    }

    @Override
    public void onTileChange(TileData tile) {
    }
}
