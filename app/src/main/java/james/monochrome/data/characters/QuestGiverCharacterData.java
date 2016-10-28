package james.monochrome.data.characters;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.quests.QuestData;
import james.monochrome.utils.TileUtils;

public class QuestGiverCharacterData extends CharacterData {

    private QuestData quest;

    public QuestGiverCharacterData(Context context, PositionData position, QuestData quest) {
        super(context, TileUtils.TILE_PERSON_2, position);
        this.quest = quest;
    }

    @Override
    String getTitle() {
        if (quest != null)
            return String.format(getContext().getString(R.string.action_quest), quest.getName());
        else return null;
    }

    @Override
    String getMessage() {
        if (quest != null) {
            if (!quest.isAccepted()) return quest.getDescription();
            else if (quest.isCompleted()) {
                quest.onComplete();
                return quest.getMessage();
            } else return getContext().getString(R.string.msg_quest_incomplete);
        } else return getContext().getString(R.string.msg_no_quests);
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
        if (quest != null) quest.onAccept();
    }

    @Override
    boolean canAccept() {
        return quest != null && !quest.isAccepted();
    }
}
