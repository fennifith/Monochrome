package james.monochrome.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.BuildConfig;
import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.RowData;
import james.monochrome.data.SceneryData;
import james.monochrome.data.characters.CharacterData;
import james.monochrome.data.characters.DialogueCharacterData;
import james.monochrome.data.characters.QuestGiverCharacterData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.BushTileData;
import james.monochrome.data.tiles.CheckpointTileData;
import james.monochrome.data.tiles.ChestTileData;
import james.monochrome.data.tiles.DoorTileData;
import james.monochrome.data.tiles.EmptyTileData;
import james.monochrome.data.tiles.FloorTileData;
import james.monochrome.data.tiles.GrassTileData;
import james.monochrome.data.tiles.HouseTileData;
import james.monochrome.data.tiles.SignTileData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.data.tiles.TreeTileData;
import james.monochrome.data.tiles.WallTileData;

public class MapUtils {

    public static final String KEY_SCENE_X = "posX";
    public static final String KEY_SCENE_Y = "posY";
    public static final String KEY_CHARACTER_X = "charX";
    public static final String KEY_CHARACTER_Y = "charY";

    public static final String KEY_MAP = "map";
    public static final String KEY_MAP_DEFAULT = "default";
    public static final String KEY_MAP_HOUSE = "house";

    private static final int TILE_EMPTY = 0;
    private static final int TILE_GRASS = 6;
    static final int TILE_GRASS_THICK = 7;
    private static final int TILE_WOOD = 22;
    private static final int TILE_BUSH = 1;
    static final int TILE_TREE = 2;
    private static final int TILE_HOUSE = 3;
    private static final int TILE_SIGN = 5;
    private static final int TILE_CHECKPOINT = 8;
    private static final int TILE_WALL = 9;
    private static final int TILE_NOTHING = 10;
    private static final int TILE_DOOR = 17;
    private static final int TILE_CHEST = 21;

    private static final int[][][][] MAP_DEFAULT = new int[][][][]{
            new int[][][]{
                    new int[][]{
                            new int[]{2, 2, 5, 5, 5, 5, 2, 2, 2, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{2, 0, 0, 3, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 1, 1, 1, 1, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 7, 7, 7, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 7, 7, 0, 0}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 2, 2, 2, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 1, 1, 1, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 1, 0, 0, 0, 2, 2, 2},
                            new int[]{0, 0, 0, 0, 0, 2, 2, 2, 2, 2},
                            new int[]{0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    }
            },
            new int[][][]{
                    new int[][]{
                            new int[]{2, 0, 0, 0, 0, 0, 7, 7, 7, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 7, 7, 7},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                            new int[]{2, 1, 1, 1, 2, 2, 1, 1, 1, 1},
                            new int[]{2, 1, 1, 1, 2, 2, 1, 1, 1, 1},
                            new int[]{2, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 1, 1, 0, 0, 0, 2}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{7, 7, 7, 0, 7, 7, 0, 7, 7, 7},
                            new int[]{7, 7, 0, 0, 0, 0, 0, 0, 7, 7},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 7, 7},
                            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 1, 1, 0, 0, 0, 2},
                            new int[]{1, 1, 1, 1, 2, 2, 1, 0, 0, 2},
                            new int[]{1, 1, 1, 1, 2, 2, 1, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 1, 1, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    }
            },
            new int[][][]{
                    new int[][]{
                            new int[]{2, 0, 0, 0, 2, 2, 0, 0, 0, 2},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    },
                    new int[][]{
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 17},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
                    }
            }
    };

    private static final int[][][][] MAP_HOUSE = new int[][][][]{
            new int[][][]{
                    new int[][]{
                            new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            new int[]{10, 10, 9, 9, 9, 9, 9, 9, 10, 10},
                            new int[]{10, 10, 9, 0, 0, 0, 21, 9, 10, 10},
                            new int[]{10, 10, 9, 0, 0, 0, 0, 9, 10, 10},
                            new int[]{10, 10, 9, 8, 0, 0, 0, 9, 10, 10},
                            new int[]{10, 10, 9, 0, 0, 0, 0, 9, 10, 10},
                            new int[]{10, 10, 9, 9, 9, 17, 9, 9, 10, 10},
                            new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                            new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
                    }
            }
    };

    public static int[][] getBackground(String key) {
        switch (key) {
            case KEY_MAP_HOUSE:
                return TileUtils.TILE_WOOD;
            default:
                return TileUtils.getRandomGrass(1, 3);
        }
    }

    public static String getDoorMapKey(PositionData doorPosition) {
        if (doorPosition.getMapKey().equals(KEY_MAP_HOUSE))
            return KEY_MAP_DEFAULT;

        return null;
    }

    public static List<RowData> getMapList(Context context, String key) {
        int[][][][] map = getMap(key);

        List<RowData> rows = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            List<SceneryData> row = new ArrayList<>();
            for (int i2 = 0; i2 < map[i].length; i2++) {
                List<List<TileData>> tileRows = new ArrayList<>();
                for (int i3 = 0; i3 < map[i][i2].length; i3++) {
                    List<TileData> tileRow = new ArrayList<>();
                    for (int i4 = 0; i4 < map[i][i2][i3].length; i4++) {
                        PositionData position = new PositionData(key, i2, i, i4, i3);
                        switch(map[i][i2][i3][i4]) {
                            case TILE_EMPTY:
                                tileRow.add(new EmptyTileData(context, position));
                                break;
                            case TILE_GRASS:
                                tileRow.add(new GrassTileData(context, 3, position));
                                break;
                            case TILE_GRASS_THICK:
                                tileRow.add(new GrassTileData(context, 4, position));
                                break;
                            case TILE_WOOD:
                                tileRow.add(new FloorTileData(context, TileUtils.TILE_WOOD, position));
                                break;
                            case TILE_BUSH:
                                tileRow.add(new BushTileData(context, position));
                                break;
                            case TILE_TREE:
                                tileRow.add(new TreeTileData(context, position));
                                break;
                            case TILE_HOUSE:
                                tileRow.add(new HouseTileData(context, position));
                                break;
                            case TILE_SIGN:
                                tileRow.add(new SignTileData(context, position));
                                break;
                            case TILE_CHECKPOINT:
                                tileRow.add(new CheckpointTileData(context, position));
                                break;
                            case TILE_WALL:
                                tileRow.add(new WallTileData(context, position));
                                break;
                            case TILE_NOTHING:
                                tileRow.add(new FloorTileData(context, TileUtils.TILE_NOTHING, position));
                                break;
                            case TILE_DOOR:
                                tileRow.add(new DoorTileData(context, position));
                                break;
                            case TILE_CHEST:
                                tileRow.add(new ChestTileData(context, position));
                                break;
                        }
                    }
                    tileRows.add(tileRow);
                }
                row.add(new SceneryData(i2, i, tileRows));
            }
            rows.add(new RowData(i, row));
        }

        return rows;
    }

    public static List<CharacterData> getCharacters(Context context, String mapKey) {
        List<CharacterData> characters = new ArrayList<>();
        switch (mapKey) {
            case KEY_MAP_DEFAULT:
                characters.add(new DialogueCharacterData(context, new PositionData(mapKey, 0, 0, 5, 4), "tutorial1", context.getString(R.string.title_char_tutorial1), context.getString(R.string.msg_char_tutorial1)));
                break;
            case KEY_MAP_HOUSE:
                characters.add(new QuestGiverCharacterData(context, new PositionData(mapKey, 0, 0, 4, 3), QuestUtils.getQuests(context)));
                break;
        }
        return characters;
    }

    public static String getTileId(PositionData position) {
        return position.getMapKey() + position.getSceneX() + "," + position.getSceneY() + "," + position.getTileX() + "," + position.getTileY();
    }

    public static String getMessage(Context context, String tileId) {
        if (tileId.equals(getTileId(new PositionData(KEY_MAP_DEFAULT, 0, 0, 2, 0))))
            return context.getString(R.string.msg_sign_tutorial1);
        else if (tileId.equals(getTileId(new PositionData(KEY_MAP_DEFAULT, 0, 0, 3, 0))))
            return context.getString(R.string.msg_sign_tutorial2);
        else if (tileId.equals(getTileId(new PositionData(KEY_MAP_DEFAULT, 0, 0, 4, 0))))
            return context.getString(R.string.msg_sign_tutorial3);
        else if (tileId.equals(getTileId(new PositionData(KEY_MAP_DEFAULT, 0, 0, 5, 0))))
            return context.getString(R.string.msg_sign_tutorial4);
        else if (BuildConfig.DEBUG) return tileId;
        else return "";
    }

    public static PositionData getEmptyPosition(SceneryData scenery, List<CharacterData> characters, List<ItemData> items, PositionData startPosition) {
        String mapKey = startPosition.getMapKey();
        int x = startPosition.getTileX(), y = startPosition.getTileY();

        for (int scale = 0; !isValidPosition(mapKey, scenery, characters, items, x, y) && scale < 10; scale++) {
            if (x - scale >= 0) {
                x -= scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else x += scale;
            }

            if (x + scale < 10) {
                x += scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else x -= scale;
            }

            if (y - scale >= 0) {
                y -= scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else y += scale;
            }

            if (y + scale < 10) {
                y += scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else y -= scale;
            }

            if (x + scale < 10 && y + scale < 10) {
                x += scale;
                y += scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else {
                    x -= scale;
                    y -= scale;
                }
            }

            if (x - scale >= 0 && y - scale >= 0) {
                x -= scale;
                y -= scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else {
                    x += scale;
                    y += scale;
                }
            }

            if (x + scale < 10 && y - scale >= 0) {
                x += scale;
                y -= scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else {
                    x -= scale;
                    y += scale;
                }
            }

            if (x - scale >= 0 && y + scale < 10) {
                x -= scale;
                y += scale;

                if (isValidPosition(mapKey, scenery, characters, items, x, y)) break;
                else {
                    x += scale;
                    y -= scale;
                }
            }
        }

        return new PositionData(mapKey, startPosition.getSceneX(), startPosition.getSceneY(), x, y);
    }

    public static boolean isValidPosition(String mapKey, SceneryData scenery, List<CharacterData> characters, List<ItemData> items, int x, int y) {
        for (TileData tile : getTilesAt(mapKey, scenery, characters, items, x, y)) {
            if (tile != null && !tile.canEnter()) return false;
        }

        return true;
    }

    public static List<TileData> getTilesAt(String mapKey, SceneryData scenery, List<CharacterData> characters, List<ItemData> items, int x, int y) {
        List<TileData> tiles = new ArrayList<>();
        tiles.add(scenery.getTile(x, y));

        for (CharacterData character : characters) {
            PositionData position = character.getPosition();
            if (position.getMapKey().equals(mapKey) && position.getSceneX() == scenery.getX() && position.getSceneY() == scenery.getY() && position.getTileX() == x && position.getTileY() == y)
                tiles.add(character);
        }

        for (ItemData item : items) {
            PositionData position = item.getPosition();
            if (position != null && position.getMapKey().equals(mapKey) && position.getSceneX() == scenery.getX() && position.getSceneY() == scenery.getY() && position.getTileX() == x && position.getTileY() == y && !item.hasPickedUp())
                tiles.add(item);
        }

        return tiles;
    }

    public static int[][][][] getMap(String key) {
        switch (key) {
            case KEY_MAP_HOUSE:
                return MAP_HOUSE;
            default:
                return MAP_DEFAULT;
        }
    }
}
