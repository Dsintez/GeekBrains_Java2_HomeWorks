package HW1;

public class RunningTrack implements IObstacle {

    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    @Override
    public EObstacle getType() {
        return EObstacle.RUNNING_TRACK;
    }

    @Override
    public int getValue() {
        return length;
    }

    @Override
    public String toString() {
        return "RunningTrack - " +
                "length=" + length;
    }
}
