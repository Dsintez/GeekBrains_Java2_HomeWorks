package HW3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Contacts {
    private Map<String, Set<String>> contactsByName = new HashMap<>();

    public void add(String name, String number) throws RuntimeException {
        validNumber(name, number);
        Set<String> phones = get(name);
        phones.add(number);
    }

    private void validNumber(String name, String number) {
        char[] check = number.toCharArray();
        if (check.length == 11) {
            for (char c : check) {
                if (!Character.isDigit(c)) {
                    throw new RuntimeException(number + " incorrect");
                }
            }
        } else if (check.length == 12) {
            if (check[0] != '+') {
                throw new RuntimeException(number + " incorrect");
            }
            for (int i = 1; i < check.length; i++) {
                if (!Character.isDigit(check[i])) {
                    throw new RuntimeException(number + " incorrect");
                }
            }
        } else {
            throw new RuntimeException(number + " incorrect");
        }
        if (name.length() < 2) {
            throw new RuntimeException(name + " incorrect");
        }
    }

    public Set<String > get(String name){
        Set<String> phones;
        phones = contactsByName.getOrDefault(name, new TreeSet<>());
        if (phones.isEmpty()){
            contactsByName.put(name, phones);
        }
        return phones;
    }
}
