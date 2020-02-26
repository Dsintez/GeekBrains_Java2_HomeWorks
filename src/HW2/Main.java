package HW2;

public class Main {

    public static void main(String[] args) {
        String[][] s = {{"1","2","2","2"},
                        {"1","2","2","2"},
                        {"1","2","2","2"},
                        {"1","2","2","2"}};

        try {
            System.out.println(Math.sumArr(s));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
