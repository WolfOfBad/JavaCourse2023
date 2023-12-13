package edu.hw7.CacheServiceTest;

import edu.hw7.CacheService.DataBaseMultiThread;
import edu.hw7.CacheService.Person;
import edu.hw7.CacheService.PersonBuilder;
import edu.hw7.CacheService.PersonDatabase;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseTest {
    @Test
    @DisplayName("Проверка посика пользователя по имени")
    public void findByNameTest() {
        Person person = PersonBuilder.getPerson("a", "b", "c");

        PersonDatabase db = new DataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByName("a");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    @Test
    @DisplayName("Проверка посика пользователя по адресу")
    public void findByAddressTest() {
        Person person = PersonBuilder.getPerson("a", "b", "c");

        PersonDatabase db = new DataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByAddress("b");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    @Test
    @DisplayName("Проверка посика пользователя по телефону")
    public void findByPhoneTest() {
        Person person = PersonBuilder.getPerson("a", "b", "c");

        PersonDatabase db = new DataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByPhone("c");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    @Test
    @DisplayName("Проверка, что пользователя без полей нельзя найти")
    public void notFoundTest() {
        PersonDatabase db = new DataBaseMultiThread();
        db.add(PersonBuilder.getPerson("a", "b", null));
        db.add(PersonBuilder.getPerson("a", null, "c"));
        db.add(PersonBuilder.getPerson(null, "b", "c"));

        List<Person> names = db.findByName("a");
        List<Person> addresses = db.findByAddress("b");
        List<Person> phones = db.findByPhone("c");

        assertThat(names).isNull();
        assertThat(addresses).isNull();
        assertThat(phones).isNull();
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    public void deleteTest() {
        Person person = new Person(
            1,
            "a",
            "b",
            "c"
        );

        PersonDatabase db = new DataBaseMultiThread();
        db.add(person);

        List<Person> beforeDeletion = db.findByName("a");
        db.delete(person);
        List<Person> afterDeletion = db.findByName("a");

        assertThat(beforeDeletion).size().isEqualTo(1);
        assertThat(afterDeletion).isNull();

    }
}
