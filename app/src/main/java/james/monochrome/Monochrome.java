package james.monochrome;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.TileUtils;

public class Monochrome extends Application {

    private Map<int[][], Bitmap> bitmaps;
    private List<OnSomethingHappenedListener> listeners;

    @Override
    public void onCreate() {
        super.onCreate();
        bitmaps = new ArrayMap<>();
        listeners = new ArrayList<>();
    }

    public Bitmap getBitmap(int[][] tile, @Nullable Integer tileSize, Paint paint) {
        if (bitmaps.containsKey(tile)) {
            Bitmap bitmap = bitmaps.get(tile);
            if (!bitmap.isRecycled() && (tileSize == null || bitmap.getWidth() == tileSize))
                return bitmap;
        }

        if (tileSize == null) tileSize = 100;

        Bitmap bitmap = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        TileUtils.drawTile(this, canvas, paint, tileSize / 10, TileUtils.getTile(tile));
        bitmaps.put(tile, bitmap);
        return bitmap;
    }

    public void addListener(OnSomethingHappenedListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OnSomethingHappenedListener listener) {
        listeners.remove(listener);
    }

    public void onTileChange(TileData tile) {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onTileChange(tile);
        }
    }

    public void requestTileKeyChange(TileData tile, int tileKey) {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onRequestTileKeyChange(tile, tileKey);
        }
    }

    public void requestMapChange(String mapKey) {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onRequestMapChange(mapKey);
        }
    }

    public void requestPositionSave() {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onRequestPositionSave();
        }
    }

    public void requestShake() {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onRequestShake();
        }
    }

    public void onOpenChest(MotionEvent event) {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onOpenChest(event);
        }
    }

    public void onItemMoved(ItemData item) {
        for (OnSomethingHappenedListener listener : listeners) {
            listener.onItemMoved(item);
        }
    }

    public interface OnSomethingHappenedListener {
        void onTileChange(TileData tile);

        void onRequestTileKeyChange(TileData tile, int tileKey);

        void onRequestMapChange(String mapKey);

        void onRequestPositionSave();

        void onRequestShake();

        void onOpenChest(MotionEvent event);

        void onItemMoved(ItemData item);
    }

}
