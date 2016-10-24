package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class CheckpointTileData extends TileData {

    public CheckpointTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_CHECKPOINT, position);
    }

    @Override
    public void onTouch() {
        StaticUtils.makeDialog(
                getContext(),
                getContext().getString(R.string.action_checkpoint),
                getContext().getString(R.string.msg_save),
                getContext().getString(R.string.action_save),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        savePosition();
                        dialog.dismiss();
                    }
                },
                getContext().getString(R.string.action_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
