package james.monochrome.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import james.monochrome.R;

public class TileUtils {

    public static final int[][]
            TILE_CHARACTER = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
            new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
            new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
            new int[]{0, 9, 7, 4, 7, 7, 4, 7, 9, 0},
            new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
            new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
            new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
            new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    },
            TILE_CHARACTER_BACK = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHARACTER_LEFT = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 9, 7, 4, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHARACTER_RIGHT = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 4, 7, 9, 0},
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 8, 9, 7, 7, 7, 7, 9, 8, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_TREE = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 6, 6, 6, 6, 0, 0, 0},
                    new int[]{0, 0, 6, 6, 7, 7, 7, 6, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 7, 0, 0},
                    new int[]{0, 4, 7, 7, 7, 7, 7, 7, 4, 0},
                    new int[]{0, 0, 6, 7, 7, 7, 7, 6, 0, 0},
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
                    new int[]{0, 0, 5, 6, 6, 6, 6, 5, 0, 0},
                    new int[]{0, 0, 5, 6, 6, 6, 6, 5, 0, 0},
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
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 9, 0, 0, 6, 6, 0, 6, 0},
                    new int[]{0, 0, 9, 6, 6, 6, 4, 6, 6, 0},
                    new int[]{0, 0, 9, 4, 4, 4, 4, 4, 6, 0},
                    new int[]{0, 0, 9, 4, 4, 4, 4, 4, 6, 0},
                    new int[]{0, 0, 9, 4, 6, 6, 6, 6, 6, 0},
                    new int[]{0, 0, 9, 6, 6, 0, 0, 6, 0, 0},
                    new int[]{0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            },
            TILE_WALL_TOP = new int[][]{
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
            TILE_WALL_BOTTOM = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            },
            TILE_WALL_LEFT = new int[][]{
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0}
            },
            TILE_WALL_RIGHT = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9}
            },
            TILE_WALL_CORNER_TOP_LEFT = new int[][]{
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
            TILE_WALL_CORNER_TOP_RIGHT = new int[][]{
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9}
            },
            TILE_WALL_CORNER_BOTTOM_LEFT = new int[][]{
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            },
            TILE_WALL_CORNER_BOTTOM_RIGHT = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            },
            TILE_WALL_DOOR_TOP = new int[][]{
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
            TILE_WALL_DOOR_BOTTOM = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 0, 0, 0, 0, 0, 0, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            },
            TILE_WALL_DOOR_LEFT = new int[][]{
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{9, 9, 9, 9, 0, 0, 0, 0, 0, 0}
            },
            TILE_WALL_DOOR_RIGHT = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 9, 9, 9},
                    new int[]{0, 0, 0, 0, 0, 0, 9, 9, 9, 9}
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
                    new int[]{0, 9, 7, 7, 7, 7, 7, 7, 9, 0},
                    new int[]{0, 9, 6, 7, 8, 8, 7, 8, 9, 0},
                    new int[]{0, 9, 6, 7, 7, 7, 7, 8, 9, 0},
                    new int[]{0, 9, 7, 7, 8, 8, 8, 8, 9, 0},
                    new int[]{0, 9, 9, 9, 9, 9, 9, 9, 9, 0}
            },
            TILE_PERSON = new int[][]{
                    new int[]{0, 0, 0, 8, 6, 7, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 3, 6, 6, 3, 0, 0, 0},
                    new int[]{0, 0, 0, 7, 5, 5, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 6, 6, 8, 0, 0, 0},
                    new int[]{0, 0, 7, 8, 7, 7, 8, 7, 0, 0},
                    new int[]{0, 0, 8, 7, 6, 6, 7, 8, 0, 0},
                    new int[]{0, 0, 9, 6, 5, 5, 6, 9, 0, 0},
                    new int[]{0, 0, 0, 7, 6, 6, 7, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 0, 9, 0, 0, 0}
            },
            TILE_PERSON_2 = new int[][]{
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
            TILE_WOOD = new int[][]{
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

    public static List<List<Integer>> getRandomGrass(int min, int max) {
        Random random = new Random();
        List<List<Integer>> tile = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>();
            for (int i2 = 0; i2 < 10; i2++) {
                row.add(random.nextInt((max + 1) - min) + min);
            }

            tile.add(row);
        }

        return tile;
    }

    public static List<List<Integer>> getTile(int[][] tile) {
        List<List<Integer>> grass = getRandomGrass(1, 3);

        List<List<Integer>> tileList = new ArrayList<>();
        for (int i = 0; i < tile.length; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int i2 = 0; i2 < tile[i].length; i2++) {
                rowList.add(tile[i][i2] != 0 ? tile[i][i2] : grass.get(i).get(i2));
            }
            tileList.add(rowList);
        }
        return tileList;
    }

    public static List<List<Integer>> getTransparentTile(int[][] tile) {
        List<List<Integer>> tileList = new ArrayList<>();
        for (int i = 0; i < tile.length; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int i2 = 0; i2 < tile[i].length; i2++) {
                rowList.add(tile[i][i2]);
            }
            tileList.add(rowList);
        }
        return tileList;
    }

    public static void drawTile(Context context, Canvas canvas, Paint paint, int offsetX, int offsetY, int pixelSize, List<List<Integer>> tile) {
        for (int row = 0; row < tile.size(); row++) {
            List<Integer> pixelRow = tile.get(row);
            for (int column = 0; column < pixelRow.size(); column++) {
                int x = pixelSize * column, y = pixelSize * row;

                switch (pixelRow.get(column)) {
                    case 1:
                        paint.setColor(ContextCompat.getColor(context, R.color.one));
                        break;
                    case 2:
                        paint.setColor(ContextCompat.getColor(context, R.color.two));
                        break;
                    case 3:
                        paint.setColor(ContextCompat.getColor(context, R.color.three));
                        break;
                    case 4:
                        paint.setColor(ContextCompat.getColor(context, R.color.four));
                        break;
                    case 5:
                        paint.setColor(ContextCompat.getColor(context, R.color.five));
                        break;
                    case 6:
                        paint.setColor(ContextCompat.getColor(context, R.color.six));
                        break;
                    case 7:
                        paint.setColor(ContextCompat.getColor(context, R.color.seven));
                        break;
                    case 8:
                        paint.setColor(ContextCompat.getColor(context, R.color.eight));
                        break;
                    case 9:
                        paint.setColor(ContextCompat.getColor(context, R.color.nine));
                        break;
                    default:
                        continue;
                }

                canvas.drawRect(x + offsetX, y + offsetY, x + pixelSize + offsetX, y + pixelSize + offsetY, paint);
            }
        }
    }
}
