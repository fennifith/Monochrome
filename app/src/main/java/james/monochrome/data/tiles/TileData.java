package james.monochrome.data.tiles;

import android.content.Context;

import java.util.List;

public abstract class TileData {

    private Context context;
    private List<List<Integer>> tile;
    private int x, y;
    private OnTileChangeListener listener;

    public TileData(Context context, List<List<Integer>> tile, int x, int y) {
        this.context = context;
        this.tile = tile;
        this.x = x;
        this.y = y;
    }

    public Context getContext() {
        return context;
    }

    public List<List<Integer>> getTile() {
        return tile;
    }

    public void setTile(List<List<Integer>> tile) {
        this.tile = tile;
        if (listener != null) listener.onTileChange(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    }
}
