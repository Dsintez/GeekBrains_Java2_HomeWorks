package HW3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Contacts contacts = new Contacts();

    public static void main(String[] args) {
        List<String> words = new ArrayList<String>();
        {
            words.add("rare");
            words.add("permanent");
            words.add("sweat");
            words.add("smart");
            words.add("feast");
            words.add("pleasure");
            words.add("conglomerate");
            words.add("polish");
            words.add("dairy");
            words.add("sailor");
            words.add("smart");
            words.add("feast");
            words.add("pleasure");
            words.add("rare");
            words.add("permanent");
        } //Добовляем строки

        String task1_2 = "";
        String task1_3 = "";
        words.sort(String::compareTo);
        //Первое задание
        int repeat = 1;
        String previous = words.get(0);
        for (int i = 1; i < words.size(); i++) {
            if (previous.equals(words.get(i))) {
                repeat++;
            } else {
                task1_2 += previous + "\n";
                task1_3 += "Слово " + previous + " встречается " + repeat + "\n";
                previous = words.get(i);
                repeat = 1;
            }
        }
        task1_2 += previous + "\n";
        task1_3 += "Слово " + previous + " встречается " + repeat + "\n";

        System.out.println(task1_2 + "----------\n" + task1_3);

        //Второе задание
        add("Дмитрий", "+78005553535");
        add("Дмитрий", "88005553535");
        add("Дмитрий", "+79525252525");
        add("Дмитрий", "89525252525");
        add("Олег", "Not yet"); //incorrect
        add("Вася", "+70"); //incorrect
        add("Вася", "80"); //incorrect
        add("Олег", "+74950000000");
        add("Олег", "84950000000");
        add("Вася", "84930000000");
        add("Вася", "+72002020202");

        print("Дмитрий");
        print("Олег");
        print("Вася");
        //Второе задание
    }

    private static void print(String name) {
        System.out.println("----------\n" + name);
        for (String s : contacts.get("Дмитрий")) {
            System.out.println(s);
        }
    }

    private static void add(String name, String number) {
        try {
            contacts.add(name, number);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

