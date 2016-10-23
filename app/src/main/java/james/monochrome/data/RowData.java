package james.monochrome.data;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class RowData implements Serializable {

    private int y;
    private List<SceneryData> row;

    public RowData(int y, List<SceneryData> row) {
        this.y = y;
        this.row = row;
    }

    public int getY() {
        return y;
    }

    public List<SceneryData> getRow() {
        return row;
    }

    @Nullable
    public SceneryData getScenery(int x) {
        if (x >= 0 && x < row.size()) return row.get(x);
        else return null;
    }

}
