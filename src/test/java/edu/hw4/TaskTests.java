package edu.hw4;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import edu.hw4.Validation.AnimalChecker;
import edu.hw4.Validation.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TaskTests {

    @Test
    @DisplayName("Задача 1")
    public void task1Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 2, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 3, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 2, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 1, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.sortHeight(animals);

        assertThat(result.get(0).height()).isEqualTo(1);
        assertThat(result.get(1).height()).isEqualTo(2);
        assertThat(result.get(2).height()).isEqualTo(2);
        assertThat(result.get(3).height()).isEqualTo(3);
    }

    @Test
    @DisplayName("Задача 2")
    public void task2Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 2, true),
            new Animal("b", Animal.Type.DOG, Animal.Sex.M, 0, 0, 3, true),
            new Animal("d", Animal.Type.DOG, Animal.Sex.M, 0, 0, 2, true),
            new Animal("e", Animal.Type.DOG, Animal.Sex.M, 0, 0, 1, true)
        );
        int count = 3;

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.sortWeightWithLimit(animals, count);

        assertThat(result.size()).isEqualTo(count);
        assertThat(result.get(0).weight()).isEqualTo(3);
        assertThat(result.get(1).weight()).isEqualTo(2);
        assertThat(result.get(2).weight()).isEqualTo(2);
    }

    @Test
    @DisplayName("Задача 3")
    public void task3Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 2, true),
            new Animal("b", Animal.Type.DOG, Animal.Sex.M, 0, 0, 3, true),
            new Animal("d", Animal.Type.CAT, Animal.Sex.M, 0, 0, 2, true),
            new Animal("e", Animal.Type.FISH, Animal.Sex.M, 0, 0, 1, true)
        );

        TaskSolver obj = new TaskSolver();
        Map<Animal.Type, Integer> result = obj.countTypeObjects(animals);

        assertThat(result.get(Animal.Type.DOG)).isEqualTo(2);
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(1);
        assertThat(result.get(Animal.Type.FISH)).isEqualTo(1);
        assertThat(result.containsKey(Animal.Type.BIRD)).isFalse();
        assertThat(result.containsKey(Animal.Type.SPIDER)).isFalse();
    }

    @Test
    @DisplayName("Задача 4")
    public void task4Test() {
        List<Animal> animals = List.of(
            new Animal("aa", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("aaa", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        Animal result = obj.longestName(animals);

        assertThat(result.name()).isEqualTo("aaa");
    }

    private static Arguments[] task5args() {
        return new Arguments[] {
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                new Animal("a", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true)
            ), Animal.Sex.M),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                new Animal("a", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
                new Animal("a", Animal.Type.CAT, Animal.Sex.F, 0, 0, 0, true)
            ), Animal.Sex.F),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                new Animal("a", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true)
            ), null),

        };
    }

    @ParameterizedTest
    @MethodSource("task5args")
    @DisplayName("Задача 5")
    public void task5Test(List<Animal> animals, Animal.Sex expected) {
        TaskSolver obj = new TaskSolver();
        Animal.Sex result = obj.largerSexAmount(animals);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Задача 6")
    public void task6Test() {
        List<Animal> animals = List.of(
            new Animal("dog1", Animal.Type.DOG, Animal.Sex.M, 0, 0, 1, true),
            new Animal("dog2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 2, true),
            new Animal("cat", Animal.Type.CAT, Animal.Sex.M, 0, 0, 1, true),
            new Animal("bird", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 1, true)
        );

        TaskSolver obj = new TaskSolver();
        Map<Animal.Type, Animal> result = obj.mostHeavyAnimals(animals);

        assertThat(result.get(Animal.Type.DOG).name()).isEqualTo("dog2");
        assertThat(result.get(Animal.Type.CAT).name()).isEqualTo("cat");
        assertThat(result.get(Animal.Type.BIRD).name()).isEqualTo("bird");
        assertThat(result.get(Animal.Type.FISH)).isNull();
        assertThat(result.get(Animal.Type.SPIDER)).isNull();
    }

    @Test
    @DisplayName("Задача 7")
    public void task7Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 1, 0, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 3, 0, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 2, 0, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 4, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        Animal res0 = obj.oldAnimalByIndex(animals, 0);
        Animal res1 = obj.oldAnimalByIndex(animals, 1);
        Animal res2 = obj.oldAnimalByIndex(animals, 2);
        Animal res3 = obj.oldAnimalByIndex(animals, 3);
        Animal res4 = obj.oldAnimalByIndex(animals, 4);

        assertThat(res0.age()).isEqualTo(4);
        assertThat(res1.age()).isEqualTo(3);
        assertThat(res2.age()).isEqualTo(2);
        assertThat(res3.age()).isEqualTo(1);
        assertThat(res4).isNull();
    }

    @Test
    @DisplayName("Задача 8")
    public void task8Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 50, 3, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 50, 2, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 4, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 50, 1, true)
        );

        TaskSolver obj = new TaskSolver();
        Optional<Animal> result = obj.heaviestAnimalShorterHeight(animals, 51);
        Optional<Animal> nullResult = obj.heaviestAnimalShorterHeight(animals, 50);

        assertThat(result.get().weight()).isEqualTo(3);
        assertThat(nullResult.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Задача 9")
    public void task9Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a", Animal.Type.CAT, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a", Animal.Type.BIRD, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        int result = obj.sumPaws(animals);

        assertThat(result).isEqualTo(18);
    }

    @Test
    @DisplayName("Задача 10")
    public void task10Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 4, 0, 0, true),
            new Animal("b", Animal.Type.DOG, Animal.Sex.M, 3, 0, 0, true),
            new Animal("c", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, true),
            new Animal("d", Animal.Type.SPIDER, Animal.Sex.M, 7, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.pawsNotEqualsToAge(animals);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).name()).isEqualTo("b");
        assertThat(result.get(1).name()).isEqualTo("d");
    }

    @Test
    @DisplayName("Задача 11")
    public void task11Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 0, true),
            new Animal("b", Animal.Type.DOG, Animal.Sex.M, 0, 101, 0, true),
            new Animal("c", Animal.Type.DOG, Animal.Sex.M, 0, 200, 0, false),
            new Animal("d", Animal.Type.DOG, Animal.Sex.M, 0, 200, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.bitesHigher100Sm(animals);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).name()).isEqualTo("b");
        assertThat(result.get(1).name()).isEqualTo("d");
    }

    @Test
    @DisplayName("Задача 12")
    public void task12Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 1, 2, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 1, 1, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 2, 1, true)
        );

        TaskSolver obj = new TaskSolver();
        int result = obj.countWeightMoreHeight(animals);

        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Задача 13")
    public void task13Test() {
        List<Animal> animals = List.of(
            new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("a b c", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.nameContainsSomeWords(animals);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).name()).isEqualTo("a a");
        assertThat(result.get(1).name()).isEqualTo("a b c");
    }

    private static Arguments[] task14args() {
        return new Arguments[] {
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 25, 0, true),
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 50, 0, true),
                new Animal("a", Animal.Type.CAT, Animal.Sex.F, 0, 100, 0, true)
            ), 50, false),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 100, 0, true),
                new Animal("a", Animal.Type.DOG, Animal.Sex.F, 0, 150, 0, true)
            ), 100, true)
        };
    }

    @ParameterizedTest
    @MethodSource("task14args")
    @DisplayName("Задача 14")
    public void task14Test(List<Animal> animals, int height, boolean expected) {
        TaskSolver obj = new TaskSolver();
        boolean result = obj.containsDogHigherK(animals, 100);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Задача 15")
    public void task15Test() {
        List<Animal> animals = List.of(
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 5, 0, 1, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 7, 0, 10, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 6, 0, 100, true),
            new Animal("a", Animal.Type.CAT, Animal.Sex.M, 10, 0, 1000, true),
            new Animal("a", Animal.Type.SPIDER, Animal.Sex.M, 10, 0, 1, true)
        );

        TaskSolver obj = new TaskSolver();
        Map<Animal.Type, Integer> result = obj.sumWeightCertainAge(animals, 6, 10);

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(Animal.Type.DOG)).isEqualTo(110);
        assertThat(result.get(Animal.Type.CAT)).isEqualTo(1000);
        assertThat(result.get(Animal.Type.SPIDER)).isEqualTo(1);
    }

    @Test
    @DisplayName("Задача 16")
    public void task16Test() {
        List<Animal> animals = List.of(
            new Animal("aa", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            new Animal("b", Animal.Type.FISH, Animal.Sex.M, 0, 0, 0, true),
            new Animal("c", Animal.Type.DOG, Animal.Sex.F, 0, 0, 0, true),
            new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        List<Animal> result = obj.sortedListTypeSexName(animals);

        assertThat(result.get(0).name()).isEqualTo("a");
        assertThat(result.get(1).name()).isEqualTo("aa");
        assertThat(result.get(2).name()).isEqualTo("c");
        assertThat(result.get(3).name()).isEqualTo("b");
    }

    private static Arguments[] task17args() {
        return new Arguments[] {
            Arguments.of(Collections.emptyList(), false),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
                new Animal("a", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
            ), false),
            Arguments.of(List.of(
                new Animal("a", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, false),
                new Animal("a", Animal.Type.SPIDER, Animal.Sex.F, 0, 0, 0, true)
            ), true)
        };
    }

    @ParameterizedTest
    @MethodSource("task17args")
    @DisplayName("Задача 17")
    public void task17Test(List<Animal> animals, boolean expected) {
        TaskSolver obj = new TaskSolver();
        boolean result = obj.spidersBitesOftenThanDogs(animals);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Задача 18")
    public void task18Test() {
        Animal fish1 = new Animal("f1", Animal.Type.FISH, Animal.Sex.M, 0, 0, 1, true);
        Animal fish2 = new Animal("f2", Animal.Type.FISH, Animal.Sex.M, 0, 0, 2, true);
        Animal fish3 = new Animal("f3", Animal.Type.FISH, Animal.Sex.M, 0, 0, 2, true);
        Animal fish4 = new Animal("f4", Animal.Type.FISH, Animal.Sex.M, 0, 0, 3, true);
        List<List<Animal>> lists = List.of(
            List.of(
                fish1,
                fish2,
                fish3,
                fish4
            ),
            List.of(
                fish1,
                fish2
            ),
            List.of(
                fish1,
                fish1,
                fish2
            ),
            List.of(
                new Animal("f4", Animal.Type.DOG, Animal.Sex.M, 0, 0, 3, true)
            )
        );

        TaskSolver obj = new TaskSolver();
        Animal result = obj.mostHeavyFishInSomeLists(lists);

        assertThat(result).isSameAs(fish2);
    }

    @Test
    @DisplayName("Задача 19")
    public void test19() {
        List<Animal> animals = List.of(
            // ошибки в возрасте, росте и весе
            new Animal("test1", Animal.Type.DOG, Animal.Sex.M, -1, -1, -1, true),
            // нет ошибок
            new Animal("test2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            // ошибка в имени
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        Map<String, Set<ValidationError>> result = obj.checkAnimals(animals);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result
            .get("test1")
            .stream()
            .toList())
            .isEqualTo(List.of(
                new ValidationError("Age is negative", "age"),
                new ValidationError("Height is negative", "height"),
                new ValidationError("Weight is negative", "weight")
            ));
        assertThat(result.get("")
            .stream()
            .toList())
            .isEqualTo(List.of(
                new ValidationError("Name is empty", "name")
            ));

    }

    @Test
    @DisplayName("Задача 20")
    public void test20() {
        List<Animal> animals = List.of(
            // ошибки в возрасте, росте и весе
            new Animal("test1", Animal.Type.DOG, Animal.Sex.M, -1, -1, -1, true),
            // нет ошибок
            new Animal("test2", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true),
            // ошибка в имени
            new Animal("", Animal.Type.DOG, Animal.Sex.M, 0, 0, 0, true)
        );

        TaskSolver obj = new TaskSolver();
        Map<String, String> result = obj.checkAnimalReadable(animals);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get("test1")).isEqualTo("{age, height, weight}");
        assertThat(result.get("")).isEqualTo("{name}");
    }
}
