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
                    new int[]{0, 0, 9, 7, 7, 7, 5, 9, 8, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 9, 8, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHARACTER_WALKING_1 = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 9, 9, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 9, 9, 7, 7, 7, 5, 9, 8, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 9, 8, 9},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 9, 9},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_CHARACTER_WALKING_2 = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 8, 9, 7, 7, 9, 8, 9, 9},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 9, 8, 9},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 0, 9, 7, 7, 7, 5, 5, 9, 0},
                    new int[]{0, 9, 9, 7, 7, 7, 5, 9, 8, 0},
                    new int[]{0, 9, 9, 9, 7, 7, 9, 8, 0, 0},
                    new int[]{0, 0, 0, 8, 9, 9, 8, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            TILE_TREE = new int[][]{
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 6, 6, 6, 6, 0, 0, 0},
                    new int[]{0, 0, 6, 6, 7, 7, 7, 6, 0, 0},
                    new int[]{0, 0, 7, 7, 7, 7, 7, 6, 0, 0},
                    new int[]{0, 6, 7, 7, 7, 7, 7, 7, 6, 0},
                    new int[]{0, 0, 6, 7, 7, 7, 7, 6, 0, 0},
                    new int[]{0, 0, 0, 9, 0, 9, 9, 0, 0, 0},
                    new int[]{0, 0, 0, 0, 9, 9, 0, 0, 0, 0},
                    new int[]{0, 0, 0, 9, 9, 0, 0, 0, 0, 0},
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
            };

    public static List<List<Integer>> getRandomGrass() {
        Random random = new Random();
        List<List<Integer>> tile = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            List<Integer> row = new ArrayList<>();
            for (int i2 = 0; i2 < 10; i2++) {
                row.add(random.nextInt(3) + 1);
            }

            tile.add(row);
        }

        return tile;
    }

    public static List<List<Integer>> getTile(int[][] tile) {
        List<List<Integer>> grass = getRandomGrass();

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

                switch(pixelRow.get(column)) {
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
