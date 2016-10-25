package james.monochrome.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import james.monochrome.R;
import james.monochrome.adapters.MapTileAdapter;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;

public class MapDialog extends AppCompatDialog {

    private String mapKey;
    private PositionData position;

    public MapDialog(Context context, String mapKey, PositionData position) {
        super(context, R.style.AppTheme_Dialog_FullScreen);
        this.mapKey = mapKey;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map);

        if (mapKey == null || position == null) return;

        int[][][][] map = MapUtils.getMap(getContext(), mapKey);
        int lengthY = map.length, lengthX = map[0].length;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), lengthX));
        recyclerView.setAdapter(new MapTileAdapter(getContext(), position, lengthX, lengthY));
    }
}
