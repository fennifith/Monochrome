package james.monochrome.data.tiles;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.items.KeyItemData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class DoorTileData extends TileData {

    private static final String KEY_LOCKED = "locked";

    private PositionData doorPosition;
    private boolean isLocked;
    private KeyItemData key;

    public DoorTileData(Context context, PositionData position, int[][] tile) {
        super(context, tile, position);
        this.doorPosition = MapUtils.getDoorPosition(position);
        this.isLocked = getBoolean(KEY_LOCKED, true);

        if (Math.abs(4.5 - position.getTileX()) > Math.abs(4.5 - position.getTileY())) {
            if (position.getTileX() > 4.5)
                setTile(TileUtils.rotateTile(tile, 1));
            else setTile(TileUtils.rotateTile(tile, 3));
        } else {
            if (position.getTileY() > 4.5)
                setTile(TileUtils.rotateTile(tile, 2));
            else setTile(tile);
        }
    }

    private void setLocked(boolean isLocked) {
        putBoolean(KEY_LOCKED, isLocked);
        this.isLocked = isLocked;
    }

    @Override
    public void onTouch() {
        if (isLocked && doorPosition != null) {
            for (ItemData item : ItemUtils.getHoldingItems(getContext())) {
                if (item instanceof KeyItemData && !item.isUseless()) {
                    key = (KeyItemData) item;
                    break;
                }
            }

            if (key != null) {
                getMonochrome().makeItemConfirmationDialog(key, getContext().getString(R.string.msg_unlock), new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (key != null) {
                            dialog.dismiss();
                            key.setUseless();
                            setLocked(false);
                        }
                    }
                });
            } else
                getMonochrome().makeToast(getContext().getString(R.string.msg_locked));
        } else {
            if (doorPosition != null) setMap(doorPosition);
            else getMonochrome().exitMap(getPosition());
        }
    }

    @Override
    public String getKey(String key) {
        return doorPosition != null ? doorPosition.getMapKey() : super.getKey(key);
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


    @Override
    public int getLight() {
        return 2;
    }
}
