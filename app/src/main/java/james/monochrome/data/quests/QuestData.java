package james.monochrome.data.quests;

import android.content.Context;
import android.preference.PreferenceManager;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.data.items.ItemData;

public abstract class QuestData {

    private static final String KEY_ACCEPTED = "accepted";
    private static final String KEY_COMPLETED = "completed";

    private Context context;
    private String name, description, message;

    private ItemData reward;

    public QuestData(Context context, String name, String description, String message) {
        this.context = context;
        this.name = name;
        this.description = description;
        this.message = message;
    }

    public QuestData setReward(ItemData reward) {
        this.reward = reward;
        return this;
    }

    public ItemData getReward() {
        return reward;
    }

    Context getContext() {
        return context;
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
        ((Monochrome) getContext().getApplicationContext()).makeToast(String.format(getContext().getString(R.string.action_quest_accepted), getName()));
    }

    public abstract float getProgress();

    public final void putBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(getKey(key), value).apply();
    }

    public final void putInteger(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putInt(getKey(key), value).apply();
    }

    public final void putString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString(getKey(key), value).apply();
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(getKey(key), defaultValue);
    }

    public final int getInt(String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(getKey(key), defaultValue);
    }

    public final String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getString(getKey(key), defaultValue);
    }

    public String getKey(String key) {
        return getId() + key;
    }
}
