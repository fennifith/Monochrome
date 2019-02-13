package james.monochrome.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import james.monochrome.R;
import james.monochrome.adapters.MapTileAdapter;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import jp.wasabeef.blurry.Blurry;

public class MapDialog extends AppCompatDialog {

    private String mapKey;
    private PositionData position;
    private Blurry.ImageComposer image;

    public MapDialog(Context context, String mapKey, PositionData position, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        this.mapKey = mapKey;
        this.position = position;
        this.image = image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map);

        if (mapKey == null || position == null) return;

        ImageView background = findViewById(R.id.background);
        image.into(background);
        background.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) dismiss();
            return false;
        });

        int[][][][] map = MapUtils.getMap(mapKey);
        int lengthY = map.length, lengthX = map[0].length;

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), lengthX));
        recyclerView.setAdapter(new MapTileAdapter(getContext(), position, lengthX, lengthY));
    }
}
