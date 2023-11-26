package edu.hw7.CacheService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ImprovedDataBaseMultiThread implements PersonDatabase {
    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<String, List<Person>> names = new HashMap<>();
    private final Map<String, List<Person>> addresses = new HashMap<>();
    private final Map<String, List<Person>> phones = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        try {
            lock.writeLock().lock();
            persons.put(person.id(), person);
            if (person.name() != null
                && person.phoneNumber() != null
                && person.address() != null) {
                addValueToMap(names, person.name(), person);
                addValueToMap(addresses, person.address(), person);
                addValueToMap(phones, person.phoneNumber(), person);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(Person person) {
        try {
            lock.writeLock().lock();
            persons.remove(person.id());
            if (names.containsKey(person.name())) {
                List<Person> personsList = names.get(person.name());
                personsList.remove(person);
                if (personsList.isEmpty()) {
                    names.put(person.name(), null);
                }
            }
            if (addresses.containsKey(person.address())) {
                List<Person> personsList = addresses.get(person.address());
                personsList.remove(person);
                if (personsList.isEmpty()) {
                    addresses.put(person.address(), null);
                }
            }
            if (phones.containsKey(person.phoneNumber())) {
                List<Person> personsList = phones.get(person.phoneNumber());
                personsList.remove(person);
                if (personsList.isEmpty()) {
                    phones.put(person.phoneNumber(), null);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByName(String name) {
        try {
            lock.readLock().lock();
            if (!names.containsKey(name) || names.get(name) == null) {
                return null;
            }
            return List.copyOf(names.get(name));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByAddress(String address) {
        try {
            lock.readLock().lock();
            if (!addresses.containsKey(address) || addresses.get(address) == null) {
                return null;
            }
            return List.copyOf(addresses.get(address));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable List<Person> findByPhone(String phone) {
        try {
            lock.readLock().lock();
            if (!phones.containsKey(phone) || phones.get(phone) == null) {
                return null;
            }
            return List.copyOf(phones.get(phone));
        } finally {
            lock.readLock().unlock();
        }
    }

    private void addValueToMap(Map<String, List<Person>> map, String key, Person value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }
}
