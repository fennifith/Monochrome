package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class SignTileData extends TileData {

    public SignTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_SIGN, position);
    }

    @Override
    public void onTouch() {
        ((Monochrome) getContext().getApplicationContext()).makeToast(MapUtils.getMessage(getContext(), getPosition().getMapKey(), MapUtils.getTileId(getPosition())));
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
