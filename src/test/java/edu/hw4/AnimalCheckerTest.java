package edu.hw4;

import edu.hw4.Validation.AnimalChecker;
import edu.hw4.Validation.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnimalCheckerTest {
    @Test
    @DisplayName("Тест проверки записи животного")
    public void checkerTest() {
        Animal animal = new Animal("", Animal.Type.DOG, Animal.Sex.M, -1, -1, -1, true);
        List<ValidationError> expected = List.of(
            new ValidationError("Age is negative", "age"),
            new ValidationError("Height is negative", "height"),
            new ValidationError("Name is empty", "name"),
            new ValidationError("Weight is negative", "weight")
        );

        Set<ValidationError> errors = AnimalChecker.checkAnimal(animal);

        assertThat(errors
            .stream()
            .toList())
            .isEqualTo(expected);
    }
}
