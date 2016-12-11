package james.monochrome;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.preference.PreferenceManager;
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

    Map<String, Object> preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        bitmaps = new ArrayMap<>();
        listeners = new ArrayList<>();

        preferences = (Map<String, Object>) PreferenceManager.getDefaultSharedPreferences(this).getAll();
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

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.containsKey(key) ? (Boolean) preferences.get(key) : defaultValue;
    }

    public int getInt(String key, int defaultValue) {
        return preferences.containsKey(key) ? (Integer) preferences.get(key) : defaultValue;
    }

    public String getString(String key, String defaultValue) {
        return preferences.containsKey(key) ? (String) preferences.get(key) : defaultValue;
    }

    public void putBoolean(String key, boolean value) {
        preferences.put(key, value);
    }

    public void putInt(String key, int value) {
        preferences.put(key, value);
    }

    public void putString(String key, String value) {
        preferences.put(key, value);
    }

    public void clearPreferences() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply();
        preferences.clear();
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

    public void requestSave() {
        for (OnSomethingHappenedListener listener : new ArrayList<>(listeners)) {
            listener.onRequestSave();
        }

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        for (String key : preferences.keySet()) {
            Object value = preferences.get(key);
            if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof String) {
                editor.putString(key, (String) value);
            }
        }
        editor.apply();
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

    public void makeDialog(@Nullable String title, @Nullable String message, String primaryText, MaterialDialog.SingleButtonCallback primaryListener, @Nullable String secondaryText, @Nullable MaterialDialog.SingleButtonCallback secondaryListener) {
        if (dialogListener != null)
            dialogListener.makeDialog(title, message, primaryText, primaryListener, secondaryText, secondaryListener);
    }

    public void makeItemConfirmationDialog(ItemData item, String message, MaterialDialog.SingleButtonCallback confirmationListener) {
        if (dialogListener != null)
            dialogListener.makeItemConfirmationDialog(item, message, confirmationListener);
    }

    public interface OnSomethingHappenedListener {
        void onTileChange(TileData tile);

        void onRequestMapChange(String mapKey);

        void onPositionChange(PositionData position);

        void onRequestSave();

        void onRequestShake();

        void onOpenChest();

        void onCloseChest();

        void onItemMoved(ItemData item);
    }

    public interface DialogListener {
        void makeToast(String message);

        void makeDialog(@Nullable String title, @Nullable String message, String primaryText, MaterialDialog.SingleButtonCallback primaryListener, @Nullable String secondaryText, @Nullable MaterialDialog.SingleButtonCallback secondaryListener);

        void makeItemConfirmationDialog(ItemData item, String message, MaterialDialog.SingleButtonCallback confirmationListener);
    }

}
