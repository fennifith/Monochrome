package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.PreferenceManager;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.items.KeyItemData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class HouseTileData extends TileData {

    private static final String KEY_LOCKED = "locked";
    private boolean isLocked;

    private KeyItemData key;

    public HouseTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_HOUSE, position);
        this.isLocked = PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(KEY_LOCKED + position.getMapKey() + MapUtils.getTileId(position), true);
    }

    private void setLocked(boolean isLocked) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_LOCKED + getMapKey() + MapUtils.getTileId(getPosition()), isLocked).apply();
        this.isLocked = isLocked;
    }

    @Override
    public void onTouch() {
        if (isLocked) {
            for (ItemData item : getItems()) {
                if (item instanceof KeyItemData && item.isHolding() && !item.isUseless()) {
                    key = (KeyItemData) item;
                    break;
                }
            }

            if (key != null) {
                StaticUtils.makeItemConfirmationDialog(getContext(), key, getContext().getString(R.string.msg_unlock_house), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (key != null) {
                            dialog.dismiss();
                            key.setUseless();
                            setLocked(false);
                        }
                    }
                }).show();
            } else {
                StaticUtils.makeToast(getContext(), getContext().getString(R.string.msg_locked_house)).show();
            }
        } else {
            setTile(TileUtils.TILE_HOUSE_OPEN);

            StaticUtils.makeDialog(
                    getContext(),
                    getContext().getString(R.string.action_house),
                    getContext().getString(R.string.msg_enter_house),
                    getContext().getString(R.string.action_yes),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setMap(MapUtils.KEY_MAP_HOUSE);
                            dialog.dismiss();
                        }
                    },
                    getContext().getString(R.string.action_no),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setTile(TileUtils.TILE_HOUSE);
                            dialog.dismiss();
                        }
                    }
            ).show();
        }
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
        setTile(TileUtils.TILE_HOUSE);
    }

    @Override
    public boolean canEnter() {
        return false;
    }
}
