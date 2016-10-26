package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.StaticUtils;

public class DoorTileData extends TileData {

    private String mapKey;

    public DoorTileData(Context context, int[][] tile, String mapKey, PositionData position) {
        super(context, tile, position);
        this.mapKey = mapKey;
    }

    @Override
    public void onTouch() {
        StaticUtils.makeDialog(
                getContext(),
                getContext().getString(R.string.action_door),
                getContext().getString(R.string.msg_door),
                getContext().getString(R.string.action_yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setMap(mapKey);
                        dialog.dismiss();
                    }
                },
                getContext().getString(R.string.action_no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        ).show();
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
