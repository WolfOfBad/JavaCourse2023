package edu.hw3.ContactList;

import org.jetbrains.annotations.NotNull;

public record Contact(String surname, String name) implements Comparable<Contact> {
    public Contact(String surname) {
        this(surname, "");
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (this.surname.compareTo(o.surname) != 0) {
            return this.surname.compareTo(o.surname);
        }
        return this.name.compareTo(o.name);
    }
}
