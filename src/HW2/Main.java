package HW2;

public class Main {

    public static void main(String[] args) {
        String[][] CorrectArr = {{"1","2","2","2"},
                                 {"1","2","2","2"},
                                 {"1","2","2","2"},
                                 {"1","2","2","2"}};

        String[][] InCorrectArr1 = {{"1","2","2"},
                                    {"1","2","2","2"},
                                    {"1","2","2","2"},
                                    {"1","2","2","2"}};

        String[][] InCorrectArr2 = {{"1","2","2","T"},
                                    {"1","2","2","2"},
                                    {"1","2","2","2"},
                                    {"1","2","2","2"}};

        sum(CorrectArr);
        sum(InCorrectArr1);
        sum(InCorrectArr2);
    }

    private static void sum(String[][] s) {
        try {
            System.out.println(Math.sumArr(s));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
