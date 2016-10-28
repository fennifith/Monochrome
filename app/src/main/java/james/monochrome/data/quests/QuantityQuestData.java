package james.monochrome.data.quests;

import android.content.Context;

import java.util.List;

import james.monochrome.data.items.ItemData;
import james.monochrome.utils.ItemUtils;

public class QuantityQuestData extends QuestData {

    private String itemKey;
    private int number;

    public QuantityQuestData(Context context, String name, String description, String message, String itemKey, int number) {
        super(context, name, description, message);
        this.itemKey = itemKey;
        this.number = number;
    }

    @Override
    public float getProgress() {
        int items = 0;
        for (ItemData item : ItemUtils.getHoldingItems(getContext())) {
            if (item.getKey().equals(itemKey)) items++;
        }

        return (float) items / number;
    }

    @Override
    public void onComplete() {
        super.onComplete();

        List<ItemData> items = ItemUtils.getHoldingItems(getContext());
        for (ItemData item : items) {
            if (!item.getKey().equals(itemKey)) items.remove(item);
        }

        for (int i = 0; i < number && i < items.size(); i++) {
            items.get(i).setUseless();
        }
    }
}
