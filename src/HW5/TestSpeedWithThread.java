package HW5;

import java.util.Arrays;

public class TestSpeedWithThread {

    private static int size = 1_000_000;
    private static float[] array1 = new float[size];
    private static float[] array2 = new float[size];
    private static float[] array3 = new float[size];

    private static void init(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
    }

    public static void main(String[] args) {
        init(array1);
        array2 = Arrays.copyOf(array1, size);
        array3 = Arrays.copyOf(array1, size);

        method1(array1); //Метод выполняется в main
        method2(array2); //Метод выполняется с использованием двух вспомогательных потоков
        method4(array3); //Метод выполняется с использованием четырех вспомагательных потоко
        test(array1);
        test(array2);
        test(array3);
    }

    private static void method4(float[] array3) {
        long timeStart = System.currentTimeMillis();
        float[] firstPart = new float[array3.length / 4];
        float[] secondPart = new float[array3.length / 4];
        float[] thirdPart = new float[array3.length / 4];
        float[] fourthPart = new float[array3.length - firstPart.length - secondPart.length - thirdPart.length];

        System.arraycopy(array3, 0, firstPart, 0, firstPart.length);
        System.arraycopy(array3, firstPart.length, secondPart, 0, secondPart.length);
        System.arraycopy(array3, firstPart.length + secondPart.length, thirdPart, 0, thirdPart.length);
        System.arraycopy(array3, firstPart.length + secondPart.length + thirdPart.length, fourthPart, 0, firstPart.length);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(firstPart, 0);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(secondPart, 250_000);
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(thirdPart, 500_000);
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(fourthPart, 750_000);
            }
        });
        try {
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(firstPart, 0, array3, 0, firstPart.length);
        System.arraycopy(secondPart, 0, array3, firstPart.length, secondPart.length);
        System.arraycopy(thirdPart, 0, array3, firstPart.length + secondPart.length, thirdPart.length);
        System.arraycopy(fourthPart, 0, array3, firstPart.length + secondPart.length + thirdPart.length, fourthPart.length);
        System.out.println("Четыре потока - " + (System.currentTimeMillis() - timeStart));
    }

    private static void method2(float[] array2) {
        long timeStart = System.currentTimeMillis();
        float[] firstPart = new float[array2.length / 2];
        float[] secondPart = new float[array2.length - array2.length / 2];

        System.arraycopy(array2, 0, firstPart, 0, firstPart.length);
        System.arraycopy(array2, firstPart.length, secondPart, 0, secondPart.length);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(firstPart, 0);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                f1(secondPart, 500_000);
            }
        });
        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(firstPart, 0, array2, 0, firstPart.length);
        System.arraycopy(secondPart, 0, array2, firstPart.length, secondPart.length);
        System.out.println("Два потока - " + (System.currentTimeMillis() - timeStart));
    }

    private static void method1(float[] array1) {
        long timeStart = System.currentTimeMillis();
        f1(array1, 0);
        System.out.println("Один поток - " + (System.currentTimeMillis() - timeStart));
    }

    private static void test(float[] arr) {
        System.out.println(array1[0] + " " +
                arr[arr.length / 4] +
                ", " +
                arr[arr.length / 2] +
                ", " +
                arr[arr.length / 4 * 3] +
                ", " +
                arr[arr.length - 1]);
    }

    private static void f1(float[] arr, int multi) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + multi) / 5) * Math.cos(0.2f + (i + multi) / 5) * Math.cos(0.4f + (i + multi) / 2));
        }
    }
}
