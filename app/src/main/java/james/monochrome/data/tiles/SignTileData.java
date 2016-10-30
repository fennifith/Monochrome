package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class SignTileData extends TileData {

    public SignTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_SIGN, position);
    }

    @Override
    public void onTouch() {
        StaticUtils.makeToast(getContext(), MapUtils.getMessage(getContext(), getPosition().getMapKey(), MapUtils.getTileId(getPosition()))).show();
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
