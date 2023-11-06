package edu.hw4.Validation;

import edu.hw4.Animal;
import java.util.HashSet;
import java.util.Set;

public final class AnimalChecker {
    private AnimalChecker() {
    }

    private static final Validator[] VALIDATORS = {
        new AgeValidator(),
        new HeightValidator(),
        new WeightValidator(),
        new NameValidator()
    };

    public static Set<ValidationError> checkAnimal(Animal animal) {
        Set<ValidationError> result = new HashSet<>();

        for (Validator validator : VALIDATORS) {
            ValidationError error = validator.checkAnimal(animal);
            if (error != null) {
                result.add(error);
            }
        }

        return result;
    }

}
