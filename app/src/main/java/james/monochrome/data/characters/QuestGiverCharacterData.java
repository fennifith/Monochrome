package james.monochrome.data.characters;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.quests.QuestData;
import james.monochrome.utils.TileUtils;

public class QuestGiverCharacterData extends CharacterData {

    private List<QuestData> quests;
    private QuestData quest;

    public QuestGiverCharacterData(Context context, PositionData position, List<QuestData> quests) {
        super(context, TileUtils.TILE_PERSON_2, position);
        this.quests = quests;

        for (QuestData quest : quests) {
            if (!quest.isReallyCompleted()) {
                this.quest = quest;
                break;
            }
        }
    }

    public QuestGiverCharacterData(Context context, PositionData position, QuestData... quests) {
        super(context, TileUtils.TILE_PERSON_2, position);
        this.quests = new ArrayList<>(Arrays.asList(quests));

        for (QuestData quest : quests) {
            if (!quest.isReallyCompleted()) {
                this.quest = quest;
                break;
            }
        }
    }

    @Override
    String getTitle() {
        if (quest != null) {
            if (quest.isAccepted() && quest.isCompleted())
                return getContext().getString(R.string.msg_quest_complete);
            else
                return String.format(getContext().getString(R.string.action_quest), quest.getName());
        }
        else return null;
    }

    @Override
    String getMessage() {
        String message;

        if (quest != null) {
            if (!quest.isAccepted()) message = quest.getDescription();
            else if (quest.isCompleted()) {
                quest.onComplete();
                message = quest.getMessage();

                for (QuestData quest : quests) {
                    if (!quest.isReallyCompleted()) {
                        this.quest = quest;
                        break;
                    }
                }

                if (quest.isReallyCompleted()) quest = null;

                return message;
            } else message = getContext().getString(R.string.msg_quest_incomplete);
        } else message = getContext().getString(R.string.msg_no_quests);

        return message;
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
