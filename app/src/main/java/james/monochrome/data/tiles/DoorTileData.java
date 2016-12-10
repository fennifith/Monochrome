package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import james.monochrome.utils.TileUtils;

public class DoorTileData extends TileData {

    private String mapKey;

    public DoorTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_EMPTY, position);
        this.mapKey = MapUtils.getDoorMapKey(position);

        int[][] tile;
        switch (position.getMapKey()) {
            case MapUtils.KEY_MAP_DEFAULT:
                tile = TileUtils.TILE_DOOR_OUTSIDE;
                break;
            default:
                tile = TileUtils.TILE_DOOR_WALL;
                break;
        }

        if (Math.abs(4.5 - position.getTileX()) > Math.abs(4.5 - position.getTileY())) {
            if (position.getTileX() > 4.5)
                setTile(TileUtils.rotateTile(tile, 1));
            else setTile(TileUtils.rotateTile(tile, 3));
        } else {
            if (position.getTileY() > 4.5)
                setTile(TileUtils.rotateTile(tile, 2));
            else setTile(tile);
        }
    }

    @Override
    public void onTouch() {
        setMap(mapKey);
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
