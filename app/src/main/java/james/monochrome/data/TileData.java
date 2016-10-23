package james.monochrome.data;

import java.util.List;

public abstract class TileData {

    private List<List<Integer>> tile;
    private OnTileChangeListener listener;

    public TileData(List<List<Integer>> tile) {
        this.tile = tile;
    }

    public List<List<Integer>> getTile() {
        return tile;
    }

    public void setTile(List<List<Integer>> tile) {
        this.tile = tile;
        if (listener != null) listener.onTileChange(this);
    }

    public abstract void onEnter();

    public abstract  void onExit();

    public abstract boolean canWalkOver();

    public abstract boolean canEnter();

    public void setOnTileChangeListener(OnTileChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTileChangeListener {
        void onTileChange(TileData tile);
    }
}
