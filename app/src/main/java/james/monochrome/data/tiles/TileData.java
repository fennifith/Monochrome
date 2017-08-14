package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.utils.MapUtils;

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

    public Monochrome getMonochrome() {
        return monochrome;
    }

    public int[][] getTile() {
        return tile;
    }

    public void setTile(int[][] tile) {
        this.tile = tile;
        monochrome.onTileChange(this);
    }

    void setMap(PositionData position) {
        monochrome.requestMapChange(position, getPosition());
    }

    void savePosition() {
        monochrome.requestSave();
    }

    void shake() {
        monochrome.requestShake();
    }

    public PositionData getPosition() {
        return position;
    }

    public abstract void onTouch();

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canEnter();

    public final void putBoolean(String key, boolean value) {
        monochrome.putBoolean(getKey(key), value);
    }

    public final void putInteger(String key, int value) {
        monochrome.putInt(getKey(key), value);
    }

    public final void putString(String key, String value) {
        monochrome.putString(getKey(key), value);
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        return monochrome.getBoolean(getKey(key), defaultValue);
    }

    public final int getInt(String key, int defaultValue) {
        return monochrome.getInt(getKey(key), defaultValue);
    }

    public final String getString(String key, String defaultValue) {
        return monochrome.getString(getKey(key), defaultValue);
    }

    public String getKey(String key) {
        return getKey() + key;
    }

    public String getKey() {
        return MapUtils.getTileId(position);
    }

    public int getLight() {
        return 0;
    }

    public int getMaxLight() {
        return 10;
    }

    @Override
    public void onCloseChest() {
    }

    @Override
    public void onItemMoved(ItemData item) {
    }

    @Override
    public void onOpenChest() {
    }

    @Override
    public void onRequestMapChange(PositionData position) {
        monochrome.removeListener(this);
    }

    @Override
    public void onRequestSave() {
    }

    @Override
    public void onPositionChange(PositionData position) {
    }

    @Override
    public void onRequestShake() {
    }

    @Override
    public void onTileChange(TileData tile) {
    }
}
