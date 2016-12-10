package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.ItemUtils;

public abstract class ItemData extends TileData {

    public static final String KEY_PICKED_UP = "pickedUp";
    public static final String KEY_HOLDING = "holding";
    public static final String KEY_USELESS = "useless";
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

    @Override
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
        pickUp();
    }

    public void pickUp() {
        if (ItemUtils.getFreeVolume(getContext()) >= getVolume()) {
            if (hasConstantPosition()) {
                putBoolean(KEY_PICKED_UP, true);
                putBoolean(KEY_HOLDING, true);
            } else ItemUtils.addToHolding(getContext(), getKey());

            hasPickedUp = true;
            isHolding = true;

            String name = getName();
            getMonochrome().makeToast(String.format(getContext().getString(VOWELS.indexOf(Character.toLowerCase(name.charAt(0))) == -1 ? R.string.msg_picked_up : R.string.msg_picked_up_vowel), name));
            getMonochrome().onItemMoved(this);

            setTile(getTile());
        } else
            getMonochrome().makeToast(getContext().getString(R.string.msg_no_space));
    }

    public void forcePickUp() {
        if (hasConstantPosition()) {
            putBoolean(KEY_PICKED_UP, true);
            putBoolean(KEY_HOLDING, ItemUtils.getFreeVolume(getContext()) >= getVolume());
        } else ItemUtils.addToHolding(getContext(), getKey());

        hasPickedUp = true;
        isHolding = true;

        String name = getName();
        if (ItemUtils.getFreeVolume(getContext()) >= getVolume())
            getMonochrome().makeToast(String.format(getContext().getString(VOWELS.indexOf(Character.toLowerCase(name.charAt(0))) == -1 ? R.string.msg_picked_up : R.string.msg_picked_up_vowel), name));
        else getMonochrome().makeToast(getContext().getString(R.string.msg_moved_chest));

        getMonochrome().onItemMoved(this);

        setTile(getTile());
    }

    public void moveToChest() {
        if (hasConstantPosition())
            putBoolean(KEY_HOLDING, false);
        else ItemUtils.moveToChest(getContext(), getKey());
        getMonochrome().onItemMoved(this);

        isHolding = false;
    }

    public void moveToHolding() {
        if (ItemUtils.getFreeVolume(getContext()) > getVolume()) {
            if (hasConstantPosition())
                putBoolean(KEY_HOLDING, true);
            else ItemUtils.moveToHolding(getContext(), getKey());
            getMonochrome().onItemMoved(this);

            isHolding = true;
        } else
            getMonochrome().makeToast(getContext().getString(R.string.msg_no_space));
    }

    public void setUseless() {
        if (hasConstantPosition())
            putBoolean(KEY_USELESS, true);
        else ItemUtils.setUseless(getContext(), this);
        getMonochrome().onItemMoved(this);

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
