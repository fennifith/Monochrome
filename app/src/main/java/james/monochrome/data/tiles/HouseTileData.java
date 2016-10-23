package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class HouseTileData extends TileData {

    private boolean isHouseOpen;

    public HouseTileData(Context context, boolean isHouseOpen, PositionData position) {
        super(context, TileUtils.getTile(TileUtils.TILE_HOUSE), position);
        this.isHouseOpen = isHouseOpen;
    }

    public void setOpen(boolean isHouseOpen) {
        this.isHouseOpen = isHouseOpen;
    }

    @Override
    public void onTouch() {
        if (!isHouseOpen) StaticUtils.makeToast(getContext(), "The house is locked.").show();
    }

    @Override
    public void onEnter() {
        if (isHouseOpen) {
            setTile(TileUtils.getTile(TileUtils.TILE_HOUSE_OPEN));
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
                            dialog.dismiss();
                        }
                    }
            ).show();
        }
    }

    @Override
    public void onExit() {
        setTile(TileUtils.getTile(TileUtils.TILE_HOUSE));
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public boolean canEnter() {
        return isHouseOpen;
    }
}
