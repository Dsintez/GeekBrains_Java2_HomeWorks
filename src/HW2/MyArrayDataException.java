package HW2;

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException(int row, int col) {
        super("For array[" + row + "][" + col + "] invalid data");
    }
}
