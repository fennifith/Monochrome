package james.monochrome;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import james.monochrome.utils.TileUtils;

public class Monochrome extends Application {

    private Map<int[][], Bitmap> bitmaps;

    @Override
    public void onCreate() {
        super.onCreate();
        bitmaps = new ArrayMap<>();
    }

    public Bitmap getBitmap(int[][] tile, int tileSize, Paint paint) {
        if (bitmaps.containsKey(tile)) {
            Bitmap bitmap = bitmaps.get(tile);
            if (!bitmap.isRecycled()) return bitmap;
        }

        Bitmap bitmap = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        TileUtils.drawTile(this, canvas, paint, tileSize / 10, TileUtils.getTile(tile));
        bitmaps.put(tile, bitmap);
        return bitmap;
    }

}
