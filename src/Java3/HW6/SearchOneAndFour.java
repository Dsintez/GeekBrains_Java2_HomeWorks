package Java3.HW6;

public class SearchOneAndFour {
    public boolean search(int[] array) {
        if (array.length < 2) return false;
        boolean one = false;
        boolean four = false;

        for (int i = 0; i < array.length; i++) {
            if (one && four) break;
            if (array[i] == 1) {
                one = true;
            }
            if (array[i] == 4) {
                four = true;
            }
        }
        return one && four;
    }
}
