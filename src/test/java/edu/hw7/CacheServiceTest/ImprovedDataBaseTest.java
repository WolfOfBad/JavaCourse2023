package edu.hw7.CacheServiceTest;

import edu.hw7.CacheService.ImprovedDataBaseMultiThread;
import edu.hw7.CacheService.Person;
import edu.hw7.CacheService.PersonDatabase;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ImprovedDataBaseTest {
    @Test
    @DisplayName("Проверка посика пользователя по имени")
    public void findByNameTest() {
        Person person = new Person(
            1,
            "a",
            "b",
            "c"
        );

        PersonDatabase db = new ImprovedDataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByName("a");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    @Test
    @DisplayName("Проверка посика пользователя по адресу")
    public void findByAddressTest() {
        Person person = new Person(
            1,
            "a",
            "b",
            "c"
        );

        PersonDatabase db = new ImprovedDataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByAddress("b");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    @Test
    @DisplayName("Проверка посика пользователя по телефону")
    public void findByPhoneTest() {
        Person person = new Person(
            1,
            "a",
            "b",
            "c"
        );

        PersonDatabase db = new ImprovedDataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByPhone("c");

        assertThat(found).size().isEqualTo(1);
        assertThat(found.get(0)).isEqualTo(person);
    }

    private static Arguments[] persons() {
        return new Arguments[] {
            Arguments.of(new Person(1, "a", "b", null)),
            Arguments.of(new Person(1, "a", null, "c")),
            Arguments.of(new Person(1, null, "b", "c")),
        };
    }

    @ParameterizedTest
    @MethodSource("persons")
    @DisplayName("Проверка, что пользователя без полей нельзя найти")
    public void notFoundTest(Person person) {
        PersonDatabase db = new ImprovedDataBaseMultiThread();
        db.add(person);

        List<Person> found = db.findByPhone("c");

        assertThat(found).isNull();
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

        PersonDatabase db = new ImprovedDataBaseMultiThread();
        db.add(person);

        List<Person> beforeDeletion = db.findByName("a");
        db.delete(person);
        List<Person> afterDeletion = db.findByName("a");

        assertThat(beforeDeletion).size().isEqualTo(1);
        assertThat(afterDeletion).isNull();

    }

}
