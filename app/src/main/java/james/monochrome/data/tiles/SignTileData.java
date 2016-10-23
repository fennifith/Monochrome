package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class SignTileData extends TileData {

    public SignTileData(Context context, int x, int y) {
        super(context, TileUtils.getTile(TileUtils.TILE_SIGN), x, y);
    }

    public String getMessage() {
        return MapUtils.getMessage(getContext(), getY() / 10, getX() / 10, getY() % 10, getX() % 10);
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
