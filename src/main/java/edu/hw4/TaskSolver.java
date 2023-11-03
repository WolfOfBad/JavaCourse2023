package edu.hw4;

import edu.hw4.Validation.AnimalChecker;
import edu.hw4.Validation.ValidationError;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class TaskSolver {
    /** Task 1 */
    public List<Animal> sortHeight(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::height))
            .toList();
    }

    /** Task 2 */
    public List<Animal> sortWeightWithLimit(@NotNull List<Animal> animals, int count) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::weight)
                .reversed())
            .limit(count)
            .toList();
    }

    /** Task 3 */
    public Map<Animal.Type, Integer> countTypeObjects(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .toMap(Animal::type, sum -> 1, Integer::sum));
    }

    /** Task 4 */
    public Animal longestName(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::name)
                .reversed())
            .toList()
            .get(0);
    }

    /** Task 5 */
    public Animal.Sex largerSexAmount(@NotNull List<Animal> animals) {
        int males = (int) animals
            .stream()
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();
        int females = animals.size() - males;

        if (males > females) {
            return Animal.Sex.M;
        } else if (females > males) {
            return Animal.Sex.F;
        } else {
            return null;
        }
    }

    /** Task 6 */
    public Map<Animal.Type, Animal> mostHeavyAnimals(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .toMap(Animal::type, animal -> animal, (animal1, animal2) -> {
                if (animal1.weight() > animal2.weight()) {
                    return animal1;
                }
                return animal2;
            }));
    }

    /**
     * Task 7
     * <p>Животные считаются с 0</p>
     */
    public Animal oldAnimalByIndex(@NotNull List<Animal> animals, int index) {
        List<Animal> sorted = animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::age)
                .reversed())
            .toList();

        if (index < sorted.size()) {
            return sorted.get(index);
        } else {
            return null;
        }
    }

    /**
     * Task 8
     * <p>Производится строгое сравнение</p>
     */
    public Optional<Animal> heaviestAnimalShorterHeight(@NotNull List<Animal> animals, int height) {
        List<Animal> sorted = animals
            .stream()
            .filter(animal -> animal.height() < height)
            .sorted(Comparator
                .comparing(Animal::weight)
                .reversed())
            .toList();

        if (!sorted.isEmpty()) {
            return Optional.ofNullable(sorted.get(0));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Task 9
     */
    public int sumPaws(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    /**
     * Task 10
     */
    public List<Animal> pawsNotEqualsToAge(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /**
     * Task 11
     */
    public List<Animal> bitesHigher100Sm(@NotNull List<Animal> animals) {
        final int maxHeight = 100;
        return animals
            .stream()
            .filter(animal -> animal.bites() && animal.height() > maxHeight)
            .toList();
    }

    /**
     * Task 12
     */
    public int countWeightMoreHeight(@NotNull List<Animal> animals) {
        return (int) animals
            .stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    /**
     * Task 13
     */
    public List<Animal> nameContainsSomeWords(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .filter(animal -> animal.name().split(" ").length > 1)
            .toList();
    }

    /**
     * Task 14
     */
    public boolean containsDogHigherK(@NotNull List<Animal> animals, int height) {
        return animals
            .stream()
            .anyMatch(animal -> animal.height() > height && animal.type() == Animal.Type.DOG);
    }

    /**
     * Task 15
     */
    public Map<Animal.Type, Integer> sumWeightCertainAge(@NotNull List<Animal> animals, int begin, int end) {
        return animals
            .stream()
            .filter(animal -> begin <= animal.age() && animal.age() <= end)
            .collect(Collectors
                .toMap(Animal::type, Animal::weight, Integer::sum));
    }

    /**
     * Task 16
     */
    public List<Animal> sortedListTypeSexName(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    /**
     * Task 17
     */
    public boolean spidersBitesOftenThanDogs(@NotNull List<Animal> animals) {
        int spiders = (int) animals
            .stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.SPIDER)
            .count();
        int dogs = (int) animals
            .stream()
            .filter(animal -> animal.bites() && animal.type() == Animal.Type.DOG)
            .count();
        return spiders > dogs;
    }

    /**
     * Task 18
     */
    public Animal mostHeavyFishInSomeLists(@NotNull List<List<Animal>> lists) {
        List<Animal> fishes = lists
            .stream()
            .filter(list -> list
                .stream()
                .anyMatch(animal -> animal.type() == Animal.Type.FISH))
            .map(list -> list
                .stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .sorted(Comparator
                    .comparing(Animal::weight)
                    .reversed())
                .toList()
                .get(0))
            .collect(Collectors
                .toMap(animal -> animal, sum -> 1, Integer::sum))
            .entrySet()
            .stream()
            .filter(animal -> animal.getValue() > 1)
            .map(Map.Entry::getKey)
            .sorted(Comparator.comparing(Animal::weight)
                .reversed())
            .toList();

        if (fishes.isEmpty()) {
            return null;
        }
        return fishes.get(0);
    }

    /**
     * Task 19
     */
    public Map<String, Set<ValidationError>> checkAnimals(@NotNull List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .toMap(Animal::name, AnimalChecker::checkAnimal))
            .entrySet()
            .stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Task 20
     */
    public Map<String, String> checkAnimalReadable(@NotNull List<Animal> animals) {
        return checkAnimals(animals)
            .entrySet()
            .stream()
            .collect(Collectors
                .toMap(Map.Entry::getKey, entry -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    for (ValidationError error : entry.getValue()) {
                        sb.append(error.field()).append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("}");
                    return sb.toString();
                }));
    }
}
