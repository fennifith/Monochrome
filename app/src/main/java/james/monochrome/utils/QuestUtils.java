package james.monochrome.utils;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import james.monochrome.R;
import james.monochrome.data.quests.QuantityQuestData;
import james.monochrome.data.quests.QuestData;

public class QuestUtils {

    public static List<QuestData> getQuests(Context context) {
        List<QuestData> quests = new ArrayList<>();
        quests.addAll(Arrays.asList(
                new QuantityQuestData(context, context.getString(R.string.quest_title_apples), context.getString(R.string.quest_desc_apples), context.getString(R.string.quest_complete_apples), MapUtils.KEY_ITEM_APPLE, 10)
        ));

        return quests;
    }

    @Nullable
    public static QuestData getNextQuest(Context context) {
        for (QuestData quest : getQuests(context)) {
            if (!quest.isReallyCompleted()) return quest;
        }

        return null;
    }

}
