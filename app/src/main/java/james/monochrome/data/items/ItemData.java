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

    private PositionData position;
    private boolean hasPickedUp;
    private boolean isHolding;

    public ItemData(Context context, int[][] tile, boolean hasPickedUp, boolean isHolding) {
        super(context, tile, null);
        this.hasPickedUp = hasPickedUp;
        this.isHolding = isHolding;
    }

    public ItemData(Context context, int[][] tile, PositionData position) {
        super(context, tile, null);
        this.position = position;

        if (hasConstantPosition()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            hasPickedUp = prefs.getBoolean(KEY_PICKED_UP, false);
            isHolding = prefs.getBoolean(KEY_HOLDING, false);
        }
    }

    public void setPosition(PositionData position) {
        this.position = position;
    }

    @Override
    public PositionData getPosition() {
        return position;
    }

    abstract String getName();

    abstract String getId();

    abstract String getKey();

    abstract boolean hasConstantPosition();

    public boolean hasPickedUp() {
        return hasPickedUp;
    }

    public boolean isHolding() {
        return isHolding;
    }

    public void addToHolding() {
        hasPickedUp = true;
        isHolding = true;
    }

    public void addToChest() {
        hasPickedUp = true;
        isHolding = false;
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
