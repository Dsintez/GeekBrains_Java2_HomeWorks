package HW1;

public class Wall implements IObstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public EObstacle getType() {
        return EObstacle.WALL;
    }

    @Override
    public int getValue() {
        return height;
    }

    @Override
    public String toString() {
        return "Wall - " +
                "height=" + height;
    }
}
