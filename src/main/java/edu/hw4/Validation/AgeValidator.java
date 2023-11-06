package edu.hw4.Validation;

import edu.hw4.Animal;

public class AgeValidator implements Validator {
    @Override
    public ValidationError checkAnimal(Animal animal) {
        if (animal.age() < 0) {
            return new ValidationError("Age is negative", "age");
        }

        return null;
    }
}
