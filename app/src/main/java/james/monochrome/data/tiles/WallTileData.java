package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class WallTileData extends TileData {

    public WallTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_EMPTY, position);

        if (Math.abs(4.5 - position.getTileX()) > Math.abs(4.5 - position.getTileY())) {
            if (position.getTileX() > 4.5)
                setTile(TileUtils.rotateTile(TileUtils.TILE_WALL, 1));
            else setTile(TileUtils.rotateTile(TileUtils.TILE_WALL, 3));
        } else if (Math.abs(4.5 - position.getTileX()) == Math.abs(4.5 - position.getTileY())) {
            if (position.getTileY() > 4.5) {
                if (position.getTileX() > 4.5)
                    setTile(TileUtils.rotateTile(TileUtils.TILE_WALL_CORNER, 2));
                else setTile(TileUtils.rotateTile(TileUtils.TILE_WALL_CORNER, 3));
            } else {
                if (position.getTileX() > 4.5)
                    setTile(TileUtils.rotateTile(TileUtils.TILE_WALL_CORNER, 1));
                else setTile(TileUtils.TILE_WALL_CORNER);
            }
        } else {
            if (position.getTileY() > 4.5)
                setTile(TileUtils.rotateTile(TileUtils.TILE_WALL, 2));
            else setTile(TileUtils.TILE_WALL);
        }
    }

    @Override
    public void onTouch() {
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
