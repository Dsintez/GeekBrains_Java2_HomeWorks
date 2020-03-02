package HW2;

public class Math {

    private final static int SIZE = 4;
    public static int sumArr(String[][] array) throws MyArraySizeException {
        checkArraySize(array);
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    private static void checkArraySize(String[][] array) {
        if (array.length == SIZE){
            for (int i = 0; i < SIZE; i++) {
                if (array[i].length != SIZE) throw new MyArraySizeException("" + i + " array invalid length");
            }
        } else {
            throw new MyArraySizeException("main array invalid length");
        }
    }
}
