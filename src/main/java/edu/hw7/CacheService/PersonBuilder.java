package edu.hw7.CacheService;

public final class PersonBuilder {
    private static int currentID = 1;

    private PersonBuilder() {
    }

    public static Person getPerson(String name, String address, String phone) {
        return new Person(
            currentID++,
            name,
            address,
            phone
        );
    }
}
