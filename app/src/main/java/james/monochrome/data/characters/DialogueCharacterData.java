package james.monochrome.data.characters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class DialogueCharacterData extends CharacterData {

    private static final String KEY_READ = "read";

    private String key, title, message;

    public DialogueCharacterData(Context context, PositionData position, String key, String title, String message) {
        super(context, TileUtils.TILE_PERSON_3, position);
        this.key = key;
        this.title = title;
        this.message = message;
    }

    @Override
    String getTitle() {
        return title;
    }

    @Override
    String getMessage() {
        return message;
    }

    @Override
    public void onTouch() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(KEY_READ + key, true).apply();
        super.onTouch();
    }

    @Nullable
    @Override
    String getAcceptedMessage() {
        return null;
    }

    @Nullable
    @Override
    String getCancelledMessage() {
        return null;
    }

    @Override
    void onAccept() {
    }

    @Override
    boolean canAccept() {
        return false;
    }

    @Override
    public void onPositionChange(PositionData position) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (position.getMapKey().equals(getPosition().getMapKey()) && position.getTileX() == getPosition().getTileX() && position.getTileY() == getPosition().getTileY() + 1 && !prefs.getBoolean(KEY_READ + key, false)) {
            prefs.edit().putBoolean(KEY_READ + key, true).apply();

            try {
                onTouch();
            } catch (MaterialDialog.DialogException e) {
                e.printStackTrace();
            }
        }
    }
}
