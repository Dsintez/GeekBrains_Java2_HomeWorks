package Java3.HW5;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    final static CountDownLatch signalYellow = new CountDownLatch(CARS_COUNT);
    final static CyclicBarrier signalGreen = new CyclicBarrier(CARS_COUNT+1);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        //ExecutorService executorService;
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), signalYellow, signalGreen);
        }
        //executorService = Executors.newFixedThreadPool(cars.length);
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        signalYellow.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        signalGreen.await();
        signalGreen.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

