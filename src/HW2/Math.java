package HW2;

public class Math {
    public static int sumArr(String[][] array) throws MyArraySizeException {
        int size = 4;
        int summa = 0;
        if (array.length == size){
            for (int i = 0; i < size; i++) {
                if (array[i].length != size) throw new MyArraySizeException("" + i + " array invalid length");
            }
        } else {
            throw new MyArraySizeException("main array invalid length");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    summa = summa + Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return summa;
    }
}
