package james.monochrome.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import james.monochrome.Monochrome;
import james.monochrome.data.PositionData;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.items.AppleItemData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.items.KeyItemData;
import james.monochrome.data.items.PumpkinItemData;

public class ItemUtils {

    public static final String KEY_ITEM_KEY = "key";
    public static final String KEY_ITEM_APPLE = "apple";
    public static final String KEY_ITEM_PUMPKIN = "pumpkin";

    public static List<ItemData> getItems(Context context, String mapKey) {
        List<ItemData> items = new ArrayList<>();

        for (ItemData item : getConstantItems(context)) {
            if (!item.isUseless() && item.getPosition() != null && item.getPosition().getMapKey().equals(mapKey))
                items.add(item);
        }

        Random random = new Random();

        int[][][][] map = MapUtils.getMap(mapKey);
        List<RowData> mapList = MapUtils.getMapList(context, mapKey);
        List<CharacterData> characters = MapUtils.getCharacters(context, mapKey);
        for (int i = 0; i < map.length; i++) {
            for (int i2 = 0; i2 < map[i].length; i2++) {
                int appleCount = 0, pumpkinCount = 0;
                int[][] scene = map[i][i2];

                SceneryData scenery = mapList.get(i).getScenery(i2);

                for (int i3 = 0; i3 < scene.length; i3++) {
                    for (int i4 = 0; i4 < scene[i3].length; i4++) {
                        PositionData position = MapUtils.getEmptyPosition(scenery, characters, items, new PositionData(mapKey, i2, i, i4, i3));

                        if (appleCount < 2 && map[i][i2][i3][i4] == MapUtils.TILE_TREE && random.nextInt(4) == 0) {
                            items.add(new AppleItemData(context, position));
                            appleCount++;
                        }

                        if (pumpkinCount < 2 && map[i][i2][i3][i4] == MapUtils.TILE_GRASS_THICK && random.nextInt(4) == 0) {
                            items.add(new PumpkinItemData(context, position));
                            pumpkinCount++;
                        }
                    }
                }
            }
        }

        return items;
    }

    private static List<ItemData> getConstantItems(Context context) {
        List<ItemData> items = new ArrayList<>();
        for (KeyItemData key : getKeys(context)) {
            if (!key.isUseless()) {
                items.add(key);
                break;
            }
        }

        return items;
    }

    private static List<KeyItemData> getKeys(Context context) {
        List<KeyItemData> keys = new ArrayList<>();
        keys.add(new KeyItemData(context, new PositionData(MapUtils.KEY_MAP_DEFAULT, 0, 0, 2, 5)));
        keys.add(new KeyItemData(context, "finishTutorial"));
        return keys;
    }

    public static List<ItemData> getHoldingItems(Context context) {
        List<ItemData> items = new ArrayList<>();
        for (ItemData item : getPickedUpItems(context)) {
            if (item.isHolding()) items.add(item);
        }

        return items;
    }

    public static List<ItemData> getChestItems(Context context) {
        List<ItemData> items = new ArrayList<>();
        for (ItemData item : getPickedUpItems(context)) {
            if (!item.isHolding()) items.add(item);
        }

        return items;
    }

    public static List<ItemData> getPickedUpItems(Context context) {
        List<ItemData> items = new ArrayList<>();
        items.addAll(getItemsOf(context, KEY_ITEM_APPLE));
        items.addAll(getItemsOf(context, KEY_ITEM_PUMPKIN));

        for (ItemData item : getConstantItems(context)) {
            if (item.hasPickedUp() && !item.isUseless()) items.add(item);
        }

        return items;
    }

    public static int getFreeVolume(Context context) {
        int volume = 100;
        for (ItemData item : getHoldingItems(context)) {
            if (!item.isUseless()) volume -= item.getVolume();
        }

        return volume;
    }

    private static List<ItemData> getItemsOf(Context context, String itemKey) {
        List<ItemData> items = new ArrayList<>();

        Monochrome monochrome = (Monochrome) context.getApplicationContext();
        int pickedUp = monochrome.getInt(ItemData.KEY_PICKED_UP + itemKey, 0), holding = monochrome.getInt(ItemData.KEY_HOLDING + itemKey, 0);
        for (int i = 0; i < pickedUp; i++) {
            switch (itemKey) {
                case KEY_ITEM_APPLE:
                    items.add(new AppleItemData(context, true, i < holding));
                    break;
                case KEY_ITEM_PUMPKIN:
                    items.add(new PumpkinItemData(context, true, i < holding));
                    break;
            }
        }

        return items;
    }

    public static void addToHolding(Context context, String itemKey) {
        ;
        Monochrome monochrome = (Monochrome) context.getApplicationContext();
        monochrome.putInt(ItemData.KEY_PICKED_UP + itemKey, monochrome.getInt(ItemData.KEY_PICKED_UP + itemKey, 0) + 1);
        monochrome.putInt(ItemData.KEY_HOLDING + itemKey, monochrome.getInt(ItemData.KEY_HOLDING + itemKey, 0) + 1);
    }

    public static void moveToChest(Context context, String itemKey) {
        Monochrome monochrome = (Monochrome) context.getApplicationContext();
        monochrome.putInt(ItemData.KEY_HOLDING + itemKey, monochrome.getInt(ItemData.KEY_HOLDING + itemKey, 0) - 1);
    }

    public static void moveToHolding(Context context, String itemKey) {
        Monochrome monochrome = (Monochrome) context.getApplicationContext();
        monochrome.putInt(ItemData.KEY_HOLDING + itemKey, monochrome.getInt(ItemData.KEY_HOLDING + itemKey, 0) + 1);
    }

    public static void setUseless(Context context, ItemData item) {
        Monochrome monochrome = (Monochrome) context.getApplicationContext();
        monochrome.putInt(ItemData.KEY_PICKED_UP + item.getKey(), monochrome.getInt(ItemData.KEY_PICKED_UP + item.getKey(), 0) - 1);
        if (item.isHolding())
            monochrome.putInt(ItemData.KEY_HOLDING + item.getKey(), monochrome.getInt(ItemData.KEY_HOLDING + item.getKey(), 0) - 1);
    }

}
