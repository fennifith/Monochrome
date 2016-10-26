package james.monochrome.data.characters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.StaticUtils;

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
            StaticUtils.makeDialog(
                    getContext(),
                    getTitle(),
                    getMessage(),
                    getContext().getString(R.string.action_yes),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onAccept();

                            String message = getAcceptedMessage();
                            if (message != null)
                                StaticUtils.makeToast(getContext(), message).show();
                            dialog.dismiss();
                        }
                    },
                    getContext().getString(R.string.action_no),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String message = getCancelledMessage();
                            if (message != null) StaticUtils.makeToast(getContext(), message);
                            dialog.dismiss();
                        }
                    }
            ).show();
        } else StaticUtils.makeToast(getContext(), getMessage()).show();
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

}
