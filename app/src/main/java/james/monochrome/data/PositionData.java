package james.monochrome.data;

public class PositionData {

    private int x, y;

    public PositionData(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PositionData(int sceneX, int sceneY, int tileX, int tileY) {
        x = (sceneX * 10) + tileX;
        y = (sceneY * 10) + tileY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
}
