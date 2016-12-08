package james.monochrome.data.tiles;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.items.KeyItemData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.MapUtils;
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
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_LOCKED + getPosition().getMapKey() + MapUtils.getTileId(getPosition()), isLocked).apply();
        this.isLocked = isLocked;
    }

    @Override
    public void onTouch() {
        if (isLocked) {
            for (ItemData item : ItemUtils.getHoldingItems(getContext())) {
                if (item instanceof KeyItemData && !item.isUseless()) {
                    key = (KeyItemData) item;
                    break;
                }
            }

            if (key != null) {
                ((Monochrome) getContext().getApplicationContext()).makeItemConfirmationDialog(getContext(), key, getContext().getString(R.string.msg_unlock_house), new MaterialDialog.SingleButtonCallback() {
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
                ((Monochrome) getContext().getApplicationContext()).makeToast(getContext().getString(R.string.msg_locked_house));
        } else setMap(MapUtils.KEY_MAP_HOUSE);
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
