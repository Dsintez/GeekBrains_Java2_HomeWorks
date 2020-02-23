package HW1;

public class Robot implements IActions {

    private static int ID = 0;
    private int maxJump;
    private int maxRun;
    private boolean retired;

    private final String name;

    public Robot(String name) {
        this.maxJump = (int) (Math.random() * 70);
        this.maxRun = (int) (Math.random() * 130);
        this.name = name + " " + ID;
        ID++;
        retired = false;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isRetired() {
        return retired;
    }

    @Override
    public boolean jump(int height) {
        if (maxJump >= height) {
            System.out.println("Перепрыгнул");
        } else {
            System.out.println("Не перепрыгнул");
            retired = true;
        }
        return maxJump >= height;
    }

    @Override
    public boolean running(int distance) {
        if (maxRun >= distance) {
            System.out.println("Пробежал");
        } else {
            System.out.println("Не пробежал");
            retired = true;
        }
        return maxRun >= distance;
    }
}
