package Java3.HW4;

public class Main {
    private static volatile char symbol = 'A';

    public static void main(String[] args) throws InterruptedException {
        Thread thread1;
        Thread thread2;
        Thread thread3;
        Object monitor = new Object();

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                soutChar(monitor, 'A', 'B');
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                soutChar(monitor, 'B', 'C');
            }
        });

        thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                soutChar(monitor, 'C', 'A');
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void soutChar(Object monitor, char a, char b) {
        for (int i = 0; i < 5; i++) {
            synchronized (monitor) {
                try {
                    while (symbol != a) monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(symbol);
                symbol = b;
                monitor.notifyAll();
            }
        }
    }
}
