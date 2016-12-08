package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.ItemUtils;

public abstract class ItemData extends TileData {

    public static final String KEY_PICKED_UP = "pickedUp";
    public static final String KEY_HOLDING = "holding";
    private static final String KEY_USELESS = "useless";
    private static final String VOWELS = "aeiou";

    private PositionData position;
    private boolean hasPickedUp;
    private boolean isHolding;
    private boolean isUseless;

    public ItemData(Context context, int[][] tile, boolean hasPickedUp, boolean isHolding) {
        super(context, tile, null);
        this.hasPickedUp = hasPickedUp;
        this.isHolding = isHolding;
    }

    public ItemData(Context context, int[][] tile, PositionData position) {
        super(context, tile, null);
        this.position = position;

        if (hasConstantPosition()) {
            hasPickedUp = getBoolean(KEY_PICKED_UP, false);
            isHolding = getBoolean(KEY_HOLDING, false);
            isUseless = getBoolean(KEY_USELESS, false);
        }
    }

    @Override
    public String getKey(String key) {
        return getKey() + getId() + key;
    }

    public void setPosition(PositionData position) {
        this.position = position;
    }

    @Override
    public PositionData getPosition() {
        return position;
    }

    public abstract String getName();

    public abstract String getDescription();

    abstract String getId();

    public abstract String getKey();

    public abstract int getVolume();

    abstract boolean hasConstantPosition();

    public abstract void onUse();

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
        if (ItemUtils.getFreeVolume(getContext()) >= getVolume()) {
            if (hasConstantPosition()) {
                putBoolean(KEY_PICKED_UP, true);
                putBoolean(KEY_HOLDING, true);
            } else ItemUtils.addToHolding(getContext(), getKey());

            hasPickedUp = true;
            isHolding = true;

            String name = getName();
            ((Monochrome) getContext().getApplicationContext()).makeToast(String.format(getContext().getString(VOWELS.indexOf(Character.toLowerCase(name.charAt(0))) == -1 ? R.string.msg_picked_up : R.string.msg_picked_up_vowel), name));
            ((Monochrome) getContext().getApplicationContext()).onItemMoved(this);

            setTile(getTile());
        } else
            ((Monochrome) getContext().getApplicationContext()).makeToast(getContext().getString(R.string.msg_no_space));
    }

    public void moveToChest() {
        if (hasConstantPosition())
            putBoolean(KEY_HOLDING, false);
        else ItemUtils.moveToChest(getContext(), getKey());
        ((Monochrome) getContext().getApplicationContext()).onItemMoved(this);

        isHolding = false;
    }

    public void moveToHolding() {
        if (ItemUtils.getFreeVolume(getContext()) > getVolume()) {
            if (hasConstantPosition())
                putBoolean(KEY_HOLDING, true);
            else ItemUtils.moveToHolding(getContext(), getKey());
            ((Monochrome) getContext().getApplicationContext()).onItemMoved(this);

            isHolding = true;
        } else
            ((Monochrome) getContext().getApplicationContext()).makeToast(getContext().getString(R.string.msg_no_space));
    }

    public void setUseless() {
        if (hasConstantPosition())
            putBoolean(KEY_USELESS, true);
        else ItemUtils.setUseless(getContext(), this);
        ((Monochrome) getContext().getApplicationContext()).onItemMoved(this);

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
