package james.monochrome.data.characters;

import android.content.Context;
import android.support.annotation.Nullable;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class DialogueCharacterData extends CharacterData {

    private String message;

    public DialogueCharacterData(Context context, PositionData position, String message) {
        super(context, TileUtils.TILE_PERSON_3, position);
        this.message = message;
    }

    @Override
    String getTitle() {
        return null;
    }

    @Override
    String getMessage() {
        return message;
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
}
