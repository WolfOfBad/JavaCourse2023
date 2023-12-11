package edu.hw7.CacheService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class DataBaseMultiThread implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, List<Person>> names = new HashMap<>();
    private final Map<String, List<Person>> addresses = new HashMap<>();
    private final Map<String, List<Person>> phones = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        if (person.name() != null
            && person.phoneNumber() != null
            && person.address() != null) {
            persons.put(person.id(), person);
            addValueToMap(names, person.name(), person);
            addValueToMap(addresses, person.address(), person);
            addValueToMap(phones, person.phoneNumber(), person);
        }
    }

    @Override
    public synchronized void delete(Person person) {
        persons.computeIfPresent(person.id(), (key, value) -> {
            names.get(person.name()).remove(person);
            addresses.get(person.address()).remove(person);
            phones.get(person.phoneNumber()).remove(person);
            return person;
        });
        persons.remove(person.id());
    }

    @Override
    public synchronized @Nullable List<Person> findByName(String name) {
        if (!names.containsKey(name) || names.get(name) == null || names.get(name).isEmpty()) {
            return null;
        }
        return List.copyOf(names.get(name));
    }

    @Override
    public synchronized @Nullable List<Person> findByAddress(String address) {
        if (!addresses.containsKey(address) || addresses.get(address) == null || addresses.get(address).isEmpty()) {
            return null;
        }
        return List.copyOf(addresses.get(address));
    }

    @Override
    public synchronized @Nullable List<Person> findByPhone(String phone) {
        if (!phones.containsKey(phone) || phones.get(phone) == null || phones.get(phone).isEmpty()) {
            return null;
        }
        return List.copyOf(phones.get(phone));
    }

    private void addValueToMap(Map<String, List<Person>> map, String key, Person value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }
}
