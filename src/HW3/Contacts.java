package HW3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Contacts {
    private Map<String, String> contacts = new HashMap<>();

    public void add(String name, String number) throws RuntimeException {
        char[] check = number.toCharArray();
        if (check.length == 11) {
            for (char c : check) {
                if (!isNumber(c)) {
                    throw new RuntimeException(number + " incorrect");
                }
            }
        } else if (check.length == 12) {
            if (check[0] != '+') {
                throw new RuntimeException(number + " incorrect");
            }
            for (int i = 1; i < check.length; i++) {
                if (!isNumber(check[i])) {
                    throw new RuntimeException(number + " incorrect");
                }
            }
        } else {
            throw new RuntimeException(number + " incorrect");
        }
        if (name.length() < 2) {
            throw new RuntimeException(name + " incorrect");
        }

        contacts.put(number, name);
    }

    public Set<String > get(String name){
        Set<String> numbers = new TreeSet<>();
        for (Map.Entry<String, String> element : contacts.entrySet()) {
            if (element.getValue().equals(name)){
                numbers.add(element.getKey());
            }
        }
        return numbers;
    }

    private boolean isNumber(char symbol) {
        switch (symbol) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57: {
                return true;
            }
        }
        return false;
    }
}
