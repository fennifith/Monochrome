package james.monochrome.data.items;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.StaticUtils;

public abstract class ItemData extends TileData {

    public static final String KEY_PICKED_UP = "pickedUp";
    public static final String KEY_HOLDING = "holding";
    public static final String KEY_USELESS = "useless";

    private PositionData position;
    private boolean hasPickedUp;
    private boolean isHolding;
    private boolean isUseless;

    public ItemData(Context context, int[][] tile, boolean hasPickedUp, boolean isHolding, boolean isUseless) {
        super(context, tile, null);
        this.hasPickedUp = hasPickedUp;
        this.isHolding = isHolding;
        this.isUseless = isUseless;
    }

    public ItemData(Context context, int[][] tile, PositionData position) {
        super(context, tile, null);
        this.position = position;

        if (hasConstantPosition()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            hasPickedUp = prefs.getBoolean(KEY_PICKED_UP + getKey() + getId(), false);
            isHolding = prefs.getBoolean(KEY_HOLDING + getKey() + getId(), false);
            isUseless = prefs.getBoolean(KEY_USELESS + getKey() + getId(), false);
        }
    }

    public void setPosition(PositionData position) {
        this.position = position;
    }

    @Override
    public PositionData getPosition() {
        return position;
    }

    public abstract String getName();

    abstract String getId();

    abstract String getKey();

    abstract boolean hasConstantPosition();

    public boolean hasPickedUp() {
        return hasPickedUp;
    }

    public boolean isHolding() {
        return isHolding;
    }

    public boolean isUseless() {
        return isUseless;
    }

    @Override
    public void onTouch() {
        if (hasConstantPosition()) {
            PreferenceManager.getDefaultSharedPreferences(getContext())
                    .edit()
                    .putBoolean(KEY_PICKED_UP + getKey() + getId(), true)
                    .putBoolean(KEY_HOLDING + getKey() + getId(), true)
                    .apply();
        } else MapUtils.addToHolding(getContext(), getKey());

        hasPickedUp = true;
        isHolding = true;

        StaticUtils.makeToast(getContext(), String.format(getContext().getString(R.string.msg_picked_up), getName())).show();
        setTile(getTile());
    }

    public void moveToChest() {
        if (hasConstantPosition())
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_HOLDING + getKey() + getId(), false).apply();
        else MapUtils.moveToChest(getContext(), getKey());

        isHolding = false;
    }

    public void moveToHolding() {
        if (hasConstantPosition())
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_HOLDING + getKey() + getId(), true).apply();
        else MapUtils.moveToHolding(getContext(), getKey());

        isHolding = true;
    }

    public void setUseless() {
        if (hasConstantPosition())
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_USELESS + getKey() + getId(), true).apply();

        isUseless = true;
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
