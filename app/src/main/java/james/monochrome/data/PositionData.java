package james.monochrome.data;

import james.monochrome.utils.MapUtils;

public class PositionData {

    private String mapKey;
    private int x, y;

    public PositionData(String mapKey, int x, int y) {
        this.mapKey = mapKey;
        this.x = x;
        this.y = y;
    }

    public PositionData(String mapKey, int sceneX, int sceneY, int tileX, int tileY) {
        this.mapKey = mapKey;
        x = (sceneX * 10) + tileX;
        y = (sceneY * 10) + tileY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getMapKey() {
        return mapKey;
    }

    public int getSceneX() {
        return x / 10;
    }

    public int getSceneY() {
        return y / 10;
    }

    public int getTileX() {
        return x % 10;
    }

    public int getTileY() {
        return y % 10;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || (obj instanceof PositionData && MapUtils.getTileId(this).equals(MapUtils.getTileId((PositionData) obj)));
    }
}
