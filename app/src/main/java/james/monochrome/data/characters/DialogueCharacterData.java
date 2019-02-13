package james.monochrome.data.characters;

import android.content.Context;

import androidx.annotation.Nullable;
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
        putBoolean(KEY_READ, true);
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
    public String getKey() {
        return key;
    }

    @Override
    public void onPositionChange(PositionData position) {
        if (position.getMapKey().equals(getPosition().getMapKey())
                && position.getTileX()
                == getPosition().getTileX()
                && position.getTileY()
                == getPosition().getTileY() + 1
                && !getBoolean(KEY_READ, false)) {
            putBoolean(KEY_READ, true);

            try {
                onTouch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
