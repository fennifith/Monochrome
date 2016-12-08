package james.monochrome.data.tiles;

import android.content.Context;
import android.preference.PreferenceManager;

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

    public abstract void onTouch();

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canEnter();

    public final void putBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(getKey(key), value).apply();
    }

    public final void putInteger(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(getKey(key), value).apply();
    }

    public final void putString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString(getKey(key), value).apply();
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(getKey(key), defaultValue);
    }

    public final int getInt(String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(getKey(key), defaultValue);
    }

    public final String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getString(getKey(key), defaultValue);
    }

    public String getKey(String key) {
        return position.getMapKey() + MapUtils.getTileId(position) + key;
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
    public void onRequestMapChange(String mapKey) {
        monochrome.removeListener(this);
    }

    @Override
    public void onRequestPositionSave() {
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
