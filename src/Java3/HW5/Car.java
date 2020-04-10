package Java3.HW5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private volatile static boolean wasWinner = false;
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    private CountDownLatch signalYellow;
    private CyclicBarrier signalGreen;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    private static synchronized boolean finish(){
        if (!wasWinner) {
            wasWinner = true;
            return wasWinner;
        } else {
            return false;
        }
    }

    public Car(Race race, int speed, CountDownLatch signalYellow, CyclicBarrier signalGreen) {
        this.race = race;
        this.speed = speed;
        this.signalYellow = signalYellow;
        this.signalGreen = signalGreen;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            signalYellow.countDown();
            signalYellow.await();
            signalGreen.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (finish()) {
            System.out.println(this.name + " WIN");
        }
        try {
            signalGreen.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
