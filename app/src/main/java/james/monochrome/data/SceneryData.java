package james.monochrome.data;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.Nullable;
import james.monochrome.data.tiles.TileData;

public class SceneryData implements Serializable {

    private int x, y;
    private List<List<TileData>> tiles;

    public SceneryData(int x, int y, List<List<TileData>> tiles) {
        this.x = x;
        this.y = y;
        this.tiles = tiles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<List<TileData>> getTiles() {
        return tiles;
    }

    @Nullable
    public List<TileData> getRow(int y) {
        if (y >= 0 && y < tiles.size()) return tiles.get(y);
        else return null;
    }

    @Nullable
    public TileData getTile(int x, int y) {
        if (y >= 0 && y < tiles.size()) {
            List<TileData> row = tiles.get(y);
            if (x >= 0 && x < row.size()) return row.get(x);
        }
        return null;
    }
}
