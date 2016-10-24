package james.monochrome.data.characters;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class QuestGiverCharacterData extends CharacterData {

    public QuestGiverCharacterData(Context context, PositionData position) {
        super(context, TileUtils.TILE_PERSON_2, position);
    }

    @Override
    String getTitle() {
        return getContext().getString(R.string.action_quest);
    }

    @Override
    String getMessage() {
        return "";
    }

    @Override
    String getAcceptedMessage() {
        return null;
    }

    @Override
    String getCancelledMessage() {
        return null;
    }

    @Override
    void onAccept() {

    }

    @Override
    boolean canAccept() {
        return true;
    }
}
