package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.PreferenceManager;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class CheckpointTileData extends TileData {

    public CheckpointTileData(Context context, PositionData position) {
        super(context, TileUtils.getTile(TileUtils.TILE_CHECKPOINT), position);
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
                        String mapKey = getMapKey();
                        if (mapKey != null) {
                            PositionData position = getPosition();

                            PreferenceManager.getDefaultSharedPreferences(getContext())
                                    .edit()
                                    .putString(MapUtils.KEY_MAP, mapKey)
                                    .putInt(MapUtils.KEY_SCENE_X + mapKey, position.getSceneX())
                                    .putInt(MapUtils.KEY_SCENE_Y + mapKey, position.getSceneY())
                                    .putInt(MapUtils.KEY_CHARACTER_X + mapKey, position.getTileX())
                                    .putInt(MapUtils.KEY_CHARACTER_Y + mapKey, position.getTileY())
                                    .apply();
                        }

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
