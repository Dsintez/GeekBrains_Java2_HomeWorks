package lesson5;

public class ThreadExample {
    static int count = 10;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        }, "T1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        }, "T2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }

    private static void print(){
        while (count > 0){
            System.out.println(Thread.currentThread().getName() + ": " + count--);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
