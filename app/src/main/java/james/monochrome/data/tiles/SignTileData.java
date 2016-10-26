package james.monochrome.data.tiles;

import android.content.Context;
import android.preference.PreferenceManager;

import james.monochrome.activities.MainActivity;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;
import james.monochrome.utils.TileUtils;

public class SignTileData extends TileData {

    public SignTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_SIGN, position);
    }

    public String getMessage() {
        PositionData position = getPosition();
        return MapUtils.getMessage(getContext(), getMapKey(), position.getSceneY(), position.getSceneX(), position.getTileY(), position.getTileX());
    }

    @Override
    public void onTouch() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(MainActivity.KEY_READ_SIGN, true).apply();
        StaticUtils.makeToast(getContext(), getMessage()).show();
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
