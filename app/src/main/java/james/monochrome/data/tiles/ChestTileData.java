package james.monochrome.data.tiles;

import android.content.Context;
import android.content.DialogInterface;

import james.monochrome.data.PositionData;
import james.monochrome.dialogs.ChestDialog;
import james.monochrome.utils.TileUtils;

public class ChestTileData extends TileData {

    public ChestTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_CHEST, position);
    }

    @Override
    public void onTouch() {
        setTile(TileUtils.TILE_CHEST_OPEN);

        ChestDialog dialog = new ChestDialog(getContext());
        dialog.addOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                setTile(TileUtils.TILE_CHEST);
            }
        });
        dialog.show();
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
