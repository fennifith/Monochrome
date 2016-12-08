package james.monochrome.data.quests;

import android.content.Context;
import android.preference.PreferenceManager;

import james.monochrome.Monochrome;
import james.monochrome.R;

public abstract class QuestData {

    private static final String KEY_ACCEPTED = "accepted";
    private static final String KEY_COMPLETED = "completed";

    private Context context;
    private String name, description, message;

    public QuestData(Context context, String name, String description, String message) {
        this.context = context;
        this.name = name;
        this.description = description;
        this.message = message;
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
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(KEY_COMPLETED + getId(), false);
    }

    public void onComplete() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_COMPLETED + getId(), true).apply();
    }

    public boolean isAccepted() {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(KEY_ACCEPTED + getId(), false);
    }

    public void onAccept() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_ACCEPTED + getId(), true).apply();
        ((Monochrome) getContext().getApplicationContext()).makeToast(String.format(getContext().getString(R.string.action_quest_accepted), getName()));
    }

    public abstract float getProgress();
}
