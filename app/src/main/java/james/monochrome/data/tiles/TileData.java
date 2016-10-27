package james.monochrome.data.tiles;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;

public abstract class TileData {

    private Context context;
    private int[][] tile;
    private PositionData position;
    private OnTileChangeListener listener;

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
        if (listener != null) listener.onTileChange(this);
    }

    void setTileKey(int tileKey) {
        if (listener != null) listener.onRequestTileKeyChange(this, tileKey);
    }

    void setMap(String mapKey) {
        if (listener != null) listener.onRequestMapChange(mapKey);
    }

    @Nullable
    String getMapKey() {
        if (listener != null) return listener.getMapKey();
        else return null;
    }

    List<ItemData> getItems() {
        if (listener != null) return listener.getItems();
        else return new ArrayList<>();
    }

    void savePosition() {
        if (listener != null) listener.onRequestPositionSave();
    }

    void shake() {
        if (listener != null) listener.onRequestShake();
    }

    public PositionData getPosition() {
        return position;
    }

    public abstract void onTouch();

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canEnter();

    public void setOnTileChangeListener(OnTileChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTileChangeListener {
        void onTileChange(TileData tile);

        void onRequestTileKeyChange(TileData tile, int tileKey);

        void onRequestMapChange(String mapKey);

        void onRequestPositionSave();

        void onRequestShake();

        String getMapKey();

        List<ItemData> getItems();
    }
}
