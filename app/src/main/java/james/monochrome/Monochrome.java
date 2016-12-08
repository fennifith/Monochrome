package james.monochrome;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import james.monochrome.data.PositionData;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.TileUtils;

public class Monochrome extends Application {

    private Map<int[][], Bitmap> bitmaps;
    private List<OnSomethingHappenedListener> listeners;
    private DialogListener dialogListener;

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

    public void setDialogListener(DialogListener listener) {
        dialogListener = listener;
    }

    public void onTileChange(TileData tile) {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onTileChange(tile);
        }
    }

    public void requestMapChange(String mapKey) {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onRequestMapChange(mapKey);
        }
    }

    public void onPositionChange(PositionData position) {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onPositionChange(position);
        }
    }

    public void requestPositionSave() {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onRequestPositionSave();
        }
    }

    public void requestShake() {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onRequestShake();
        }
    }

    public void onOpenChest() {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onOpenChest();
        }
    }

    public void onCloseChest() {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onCloseChest();
        }
    }

    public void onItemMoved(ItemData item) {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onItemMoved(item);
        }
    }

    public void makeToast(String message) {
        if (dialogListener != null)
            dialogListener.makeToast(message);
    }

    public void makeDialog(Context context, @Nullable String title, @Nullable String message, String primaryText, MaterialDialog.SingleButtonCallback primaryListener, @Nullable String secondaryText, @Nullable MaterialDialog.SingleButtonCallback secondaryListener) {
        if (dialogListener != null)
            dialogListener.makeDialog(context, title, message, primaryText, primaryListener, secondaryText, secondaryListener);
    }

    public void makeItemConfirmationDialog(Context context, ItemData item, String message, MaterialDialog.SingleButtonCallback confirmationListener) {
        if (dialogListener != null)
            dialogListener.makeItemConfirmationDialog(context, item, message, confirmationListener);
    }

    public interface OnSomethingHappenedListener {
        void onTileChange(TileData tile);

        void onRequestMapChange(String mapKey);

        void onPositionChange(PositionData position);

        void onRequestPositionSave();

        void onRequestShake();

        void onOpenChest();

        void onCloseChest();

        void onItemMoved(ItemData item);
    }

    public interface DialogListener {
        void makeToast(String message);

        void makeDialog(Context context, @Nullable String title, @Nullable String message, String primaryText, MaterialDialog.SingleButtonCallback primaryListener, @Nullable String secondaryText, @Nullable MaterialDialog.SingleButtonCallback secondaryListener);

        void makeItemConfirmationDialog(Context context, ItemData item, String message, MaterialDialog.SingleButtonCallback confirmationListener);
    }

}
