package Java3.HW6;

import java.util.Arrays;

public class FortyFour {
    /*public static void main(String[] args) {
        int tests[][] = {
                {1, 2, 3, 4, 5, 6, 7},
                {0, 0, 0, 0, 0, 0, 0},
                {3, 2, 23, 32, 54, 345, 4},
                {4, 2, 4, 4, 9, 2}
        };
        for (int[] test : tests) {
            try {
                System.out.println(Arrays.toString(postLastFour(test)));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());;
            }

        }
    }*/

    public int[] postLastFour(int[] array) {
        int four = isHaveFour(array);
        if (four == -1) {
            throw new RuntimeException("Нет четверки");
        }
        four++;
        int[] postFour = new int[array.length - four];
        for (int i = 0; i < postFour.length; i++) {
            postFour[i] = array[i + four];
        }
        return postFour;
    }

    private int isHaveFour(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] == 4) {
                return i;
            }
        }
        return -1;
    }
}
