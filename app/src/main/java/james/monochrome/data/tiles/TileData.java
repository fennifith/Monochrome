package james.monochrome.data.tiles;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

import james.monochrome.data.PositionData;

public abstract class TileData {

    private Context context;
    private List<List<Integer>> tile;
    private PositionData position;
    private OnTileChangeListener listener;

    public TileData(Context context, List<List<Integer>> tile, PositionData position) {
        this.context = context;
        this.tile = tile;
        this.position = position;
    }

    public Context getContext() {
        return context;
    }

    public List<List<Integer>> getTile() {
        return tile;
    }

    void setTile(List<List<Integer>> tile) {
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

    void shake() {
        if (listener != null) listener.onRequestShake();
    }

    public PositionData getPosition() {
        return position;
    }

    public abstract void onTouch();

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canWalkOver();

    public abstract boolean canEnter();

    public void setOnTileChangeListener(OnTileChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTileChangeListener {
        void onTileChange(TileData tile);

        void onRequestTileKeyChange(TileData tile, int tileKey);

        void onRequestMapChange(String mapKey);

        void onRequestShake();

        String getMapKey();
    }
}
