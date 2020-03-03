package HW3;

import java.util.*;

public class Main {
    private static Contacts contacts = new Contacts();
    private static String[] randomWords = {
            "glow",
            "runner",
            "flexible",
            "differ",
            "glow",
            "glow",
            "flexible",
            "drain",
            "bring",
            "flexible",
            "glow",
            "ally",
            "flexible",
            "countryside",
            "category",
            "wolf",
            "wolf"
    };

    public static void main(String[] args) {
        Map<String, Integer> words = new TreeMap<>();

        for (String word : randomWords) {
            words.merge(word, 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> wordPlus : words.entrySet()) {
            System.out.println("Слово " + wordPlus.getKey() + " встречается " + wordPlus.getValue() + ";");
        }

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

