package james.monochrome.data.characters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;

public abstract class CharacterData extends TileData {

    public CharacterData(Context context, int[][] tile, PositionData position) {
        super(context, tile, position);
    }

    abstract String getTitle();

    abstract String getMessage();

    @Nullable
    abstract String getAcceptedMessage();

    @Nullable
    abstract String getCancelledMessage();

    abstract void onAccept();

    abstract boolean canAccept();

    @Override
    public void onTouch() {
        if (canAccept()) {
            getMonochrome().makeDialog(
                    getTitle(),
                    getMessage(),
                    getContext().getString(R.string.action_yes),
                    (dialog, which) -> {
                        onAccept();

                        String message = getAcceptedMessage();
                        if (message != null)
                            getMonochrome().makeToast(message);
                        dialog.dismiss();
                    },
                    getContext().getString(R.string.action_no),
                    (dialog, which) -> {
                        String message = getCancelledMessage();
                        if (message != null)
                            getMonochrome().makeToast(message);
                        dialog.dismiss();
                    });
        } else {
            getMonochrome().makeDialog(
                    getTitle(),
                    getMessage(),
                    getContext().getString(R.string.action_ok),
                    (dialog, which) -> dialog.dismiss(),
                    null,
                    null
            );
        }
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return false;
    }

    @Override
    public int getLight() {
        return 3;
    }
}
