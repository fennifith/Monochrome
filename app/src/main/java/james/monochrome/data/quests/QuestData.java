package james.monochrome.data.quests;

import android.content.Context;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.items.ItemData;

public abstract class QuestData {

    private static final String KEY_ACCEPTED = "accepted";
    private static final String KEY_COMPLETED = "completed";

    private Context context;
    private Monochrome monochrome;
    private String name, description, message;

    private ItemData reward;

    public QuestData(Context context, String name, String description, String message) {
        this.context = context;
        monochrome = (Monochrome) context.getApplicationContext();
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public ItemData getReward() {
        return reward;
    }

    public QuestData setReward(ItemData reward) {
        this.reward = reward;
        return this;
    }

    Context getContext() {
        return context;
    }

    public Monochrome getMonochrome() {
        return monochrome;
    }

    String getId() {
        return name.toLowerCase().replaceAll("\\s", "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCurrent() {
        return isAccepted() && !isCompleted();
    }

    public boolean isCompleted() {
        return getProgress() >= 1;
    }

    public boolean isReallyCompleted() {
        return getBoolean(KEY_COMPLETED, false);
    }

    public void onComplete() {
        putBoolean(KEY_COMPLETED, true);
        if (reward != null) reward.forcePickUp();
    }

    public boolean isAccepted() {
        return getBoolean(KEY_ACCEPTED, false);
    }

    public void onAccept() {
        putBoolean(KEY_ACCEPTED, true);
        monochrome.makeToast(String.format(getContext().getString(R.string.action_quest_accepted), getName()));
    }

    public abstract float getProgress();

    public final void putBoolean(String key, boolean value) {
        monochrome.putBoolean(getKey(key), value);
    }

    public final void putInteger(String key, int value) {
        monochrome.putInt(getKey(key), value);
    }

    public final void putString(String key, String value) {
        monochrome.putString(getKey(key), value);
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        return monochrome.getBoolean(getKey(key), defaultValue);
    }

    public final int getInt(String key, int defaultValue) {
        return monochrome.getInt(getKey(key), defaultValue);
    }

    public final String getString(String key, String defaultValue) {
        return monochrome.getString(getKey(key), defaultValue);
    }

    public String getKey(String key) {
        return getId() + key;
    }
}
