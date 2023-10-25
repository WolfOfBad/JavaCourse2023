package edu.hw3.ContactList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactsParser {

    public List<Contact> parseContact(List<String> names, SortingOrder order) {
        if (names == null || names.isEmpty()) {
            return new ArrayList<>();
        }

        List<Contact> result = new ArrayList<>();
        for (String name : names) {
            String[] split = name.split(" ");
            if (split.length >= 2) {
                result.add(new Contact(split[1], split[0]));
            } else if (split.length == 1) {
                result.add(new Contact(split[0]));
            }
        }

        Comparator<Contact> comparator = Contact::compareTo;
        switch (order) {
            case ASC -> result.sort(comparator);
            case DESC -> result.sort(comparator.reversed());
            default -> {
            }
        }

        return result;
    }
}
