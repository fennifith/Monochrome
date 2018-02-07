package james.monochrome.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import james.monochrome.R;

public class TileUtils {

    public static final int[][]
            TILE_EMPTY = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    },
            TILE_CHARACTER = new int[][]{
                    new int[]{0, 0, 0, 8, 7, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 3, 6, 6, 3, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 7, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 8, 8, 9, 0, 0, 0},
                    new int[]{0, 0, 9, 8, 7, 7, 8, 9, 0, 0},
                    new int[]{0, 0, 8, 7, 6, 6, 7, 8, 0, 0},
                    new int[]{0, 0, 7, 8, 7, 7, 8, 7, 0, 0},
                    new int[]{0, 0, 0, 7, 6, 6, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0}
            },
            TILE_CHARACTER_BACK = new int[][]{
                    new int[]{0, 0, 0, 8, 8, 8, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 7, 6, 6, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 7, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 8, 8, 9, 0, 0, 0},
                    new int[]{0, 0, 9, 8, 7, 7, 8, 9, 0, 0},
                    new int[]{0, 0, 8, 7, 6, 6, 7, 8, 0, 0},
                    new int[]{0, 0, 7, 8, 7, 7, 8, 7, 0, 0},
                    new int[]{0, 0, 0, 7, 6, 6, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0}
            },
            TILE_CHARACTER_LEFT = new int[][]{
                    new int[]{0, 0, 0, 8, 8, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 3, 6, 7, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 7, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 8, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 7, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 7, 8, 7, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 7, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 7, 6, 7, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 0, 0, 0, 0, 0}
            },
            TILE_CHARACTER_RIGHT = new int[][]{
                    new int[]{0, 0, 0, 0, 8, 8, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 7, 6, 3, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 8, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 8, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 7, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 7, 8, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 8, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 7, 6, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 9, 0, 0, 0, 0}
            }, 
            TILE_TREE = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 6, 6, 6, 6, 0, 0, 0},
                    new int[]{0, 0, 6, 6, 12, 12, 7, 6, 0, 0},
                    new int[]{0, 0, 12, 12, 7, 12, 12, 7, 0, 0},
                    new int[]{0, 4, 12, 7, 12, 12, 7, 12, 4, 0},
                    new int[]{0, 0, 6, 7, 12, 7, 12, 6, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 9, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_BUSH = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 5, 5, 5, 5, 0, 0, 0},
                    new int[]{0, 0, 5, 6, 6, 6, 7, 5, 0, 0},
                    new int[]{0, 0, 5, 6, 6, 7, 7, 5, 0, 0},
                    new int[]{0, 0, 0, 5, 5, 5, 5, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_HOUSE = new int[][]{
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 7, 7, 9, 0, 0, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 7, 9, 0, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{9, 8, 7, 3, 7, 7, 3, 7, 8, 9},
                    new int[]{0, 8, 7, 7, 7, 7, 7, 7, 8, 0},
                    new int[]{0, 8, 7, 7, 9, 9, 7, 7, 8, 0},
                    new int[]{0, 8, 7, 7, 9, 9, 7, 7, 8, 0},
                    new int[]{0, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_HOUSE_OPEN = new int[][]{
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 7, 7, 9, 0, 0, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 7, 9, 0, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{9, 8, 7, 3, 7, 7, 3, 7, 8, 9},
                    new int[]{0, 8, 7, 7, 7, 7, 7, 7, 8, 0},
                    new int[]{0, 8, 7, 9, 2, 2, 7, 7, 8, 0},
                    new int[]{0, 8, 7, 9, 2, 2, 7, 7, 8, 0},
                    new int[]{0, 8, 8, 8, 8, 8, 8, 8, 8, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_SIGN = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 0, 7, 9, 7, 9, 9, 7, 0, 0},
                    new int[]{0, 0, 7, 9, 9, 7, 7, 7, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 0, 0, 0, 8, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHECKPOINT = new int[][]{
                    new int[]{0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 9, 7, 7, 0, 7, 7, 7, 0},
                    new int[]{0, 0, 9, 4, 7, 7, 7, 5, 7, 0},
                    new int[]{0, 0, 9, 4, 4, 4, 5, 5, 7, 0},
                    new int[]{0, 0, 9, 4, 4, 5, 5, 5, 7, 0},
                    new int[]{0, 0, 9, 7, 7, 5, 7, 7, 7, 0},
                    new int[]{0, 0, 9, 0, 7, 7, 7, 0, 0, 0},
                    new int[]{0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            },
            TILE_NOTHING = new int[][]{
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            },
            TILE_WALL = new int[][]{
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_WALL_CORNER = new int[][]{
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0}
            },
            TILE_DOOR_WALL = new int[][]{
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 0, 0, 0, 0, 0, 0, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHEST = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 9, 9, 9, 9, 9, 9, 0, 0},
                    new int[]{0, 9, 6, 6, 7, 6, 7, 8, 9, 0},
                    new int[]{0, 9, 6, 7, 7, 7, 7, 8, 9, 0},
                    new int[]{0, 9, 9, 9, 2, 2, 9, 9, 9, 0},
                    new int[]{0, 9, 6, 7, 8, 8, 7, 8, 9, 0},
                    new int[]{0, 9, 6, 7, 7, 7, 7, 8, 9, 0},
                    new int[]{0, 9, 7, 7, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 0}
            },
            TILE_CHEST_OPEN = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
                    new int[]{0, 0, 8, 8, 8, 8, 8, 8, 0, 0},
                    new int[]{0, 8, 9, 9, 9, 9, 9, 9, 8, 0},
                    new int[]{0, 8, 9, 9, 9, 9, 9, 9, 8, 0},
                    new int[]{0, 8, 9, 9, 9, 9, 9, 9, 8, 0},
                    new int[]{0, 9, 6, 7, 8, 8, 7, 8, 9, 0},
                    new int[]{0, 9, 6, 7, 7, 7, 7, 8, 9, 0},
                    new int[]{0, 9, 7, 7, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 0}
            },
            TILE_GUIDE = new int[][]{
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 0, 7, 1, 7, 7, 1, 7, 0, 0},
                    new int[]{0, 0, 7, 9, 7, 7, 9, 7, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 7, 8, 8, 8, 8, 8, 8, 7, 0},
                    new int[]{0, 0, 8, 8, 8, 8, 8, 8, 0, 0},
                    new int[]{0, 0, 9, 9, 9, 9, 9, 9, 0, 0},
                    new int[]{0, 0, 9, 9, 0, 0, 9, 9, 0, 0}
            },
            TILE_PERSON_3 = new int[][]{
                    new int[]{0, 0, 7, 7, 8, 9, 8, 7, 0, 0},
                    new int[]{0, 0, 7, 1, 7, 8, 1, 7, 0, 0},
                    new int[]{0, 0, 7, 9, 7, 7, 9, 7, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 9, 8, 8, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 7, 8, 8, 8, 8, 8, 8, 7, 0},
                    new int[]{0, 0, 8, 8, 8, 8, 8, 8, 0, 0},
                    new int[]{0, 0, 9, 9, 9, 9, 9, 9, 0, 0},
                    new int[]{0, 0, 9, 9, 0, 0, 9, 9, 0, 0}
            },
            TILE_WOOD = new int[][]{
                    new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            },
            TILE_KEY = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 14, 14, 14, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 14, 9, 14, 14, 14, 14, 14, 14, 14},
                    new int[]{0, 14, 14, 14, 9, 9, 9, 14, 9, 14},
                    new int[]{0, 9, 9, 9, 0, 0, 0, 9, 0, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_APPLE = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 10, 7, 10, 9, 0, 0},
                    new int[]{0, 0, 0, 9, 6, 10, 10, 9, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_PUMPKIN = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 8, 0, 0, 0, 0},
                    new int[]{0, 0, 8, 9, 9, 9, 9, 8, 0, 0},
                    new int[]{0, 8, 6, 11, 11, 6, 7, 11, 8, 0},
                    new int[]{0, 9, 6, 11, 11, 11, 11, 7, 9, 0},
                    new int[]{0, 8, 11, 11, 8, 7, 7, 7, 8, 0},
                    new int[]{0, 0, 8, 9, 9, 9, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_BARRIER = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{4, 4, 7, 4, 4, 7, 4, 4, 7, 4},
                    new int[]{4, 7, 4, 4, 7, 4, 4, 7, 4, 4},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_DOOR_OUTSIDE = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 9, 9, 9, 0, 0, 0},
                    new int[]{0, 0, 9, 9, 7, 7, 9, 9, 0, 0},
                    new int[]{0, 4, 9, 8, 7, 7, 7, 9, 4, 0},
                    new int[]{4, 4, 9, 8, 8, 7, 7, 9, 4, 4},
                    new int[]{0, 4, 9, 8, 7, 7, 9, 9, 4, 0},
                    new int[]{0, 4, 9, 8, 8, 7, 7, 9, 4, 0},
                    new int[]{0, 4, 9, 9, 9, 9, 9, 9, 4, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

    public static int[][] getRandomGrass(int min, int max) {
        Random random = new Random();
        int[][] tile = new int[10][];

        for (int i = 0; i < tile.length; i++) {
            tile[i] = new int[10];
            for (int i2 = 0; i2 < tile[i].length; i2++) {
                tile[i][i2] = random.nextInt((max + 1) - min) + min;
            }
        }

        return tile;
    }

    public static List<List<Integer>> getTile(int[][] tile) {
        List<List<Integer>> tileList = new ArrayList<>();
        for (int[] tileRow : tile) {
            List<Integer> rowList = new ArrayList<>();
            for (int tileColumn : tileRow) {
                rowList.add(tileColumn);
            }
            tileList.add(rowList);
        }
        return tileList;
    }

    public static int[][] rotateTile(int[][] tile, int rotation) {
        int[][] newTile = new int[][]{
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10],
                new int[10]
        };

        for (int row = 0; row < tile.length; row++) {
            for (int column = 0; column < tile[row].length; column++) {
                int id = tile[row][column];
                switch (rotation % 4) {
                    case 1:
                        newTile[column][9 - row] = id;
                        break;
                    case 2:
                        newTile[9 - row][9 - column] = id;
                        break;
                    case 3:
                        newTile[9 - column][row] = id;
                        break;
                }
            }
        }

        return newTile;
    }

    public static void drawTile(Context context, Canvas canvas, Paint paint, int pixelSize, List<List<Integer>> tile) {
        int[] colors = context.getResources().getIntArray(R.array.colors);

        for (int row = 0; row < tile.size(); row++) {
            List<Integer> pixelRow = tile.get(row);
            for (int column = 0; column < pixelRow.size(); column++) {
                int x = pixelSize * column, y = pixelSize * row;
                int pixel = pixelRow.get(column);
                if (pixel > 0)
                    paint.setColor(colors[pixel]);
                else continue;

                canvas.drawRect(x, y, x + pixelSize, y + pixelSize, paint);
            }
        }
    }
}
