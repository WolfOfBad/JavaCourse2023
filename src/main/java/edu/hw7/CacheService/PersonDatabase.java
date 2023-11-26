package edu.hw7.CacheService;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface PersonDatabase {
    void add(Person person);

    void delete(Person person);

    @Nullable
    List<Person> findByName(String name);

    @Nullable
    List<Person> findByAddress(String address);

    @Nullable
    List<Person> findByPhone(String phone);

}
