package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class SignTileData extends TileData {

    public SignTileData(Context context, PositionData position) {
        super(context, TileUtils.getTile(TileUtils.TILE_SIGN), position);
    }

    public String getMessage() {
        PositionData position = getPosition();
        return MapUtils.getMessage(getContext(), position.getSceneY(), position.getSceneX(), position.getTileY(), position.getTileX());
    }

    @Override
    public void onTouch() {
        StaticUtils.makeToast(getContext(), getMessage()).show();
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
