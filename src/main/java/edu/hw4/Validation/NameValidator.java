package edu.hw4.Validation;

import edu.hw4.Animal;

public class NameValidator implements Validator {
    @Override
    public ValidationError checkAnimal(Animal animal) {
        if (animal.name().isEmpty()) {
            return new ValidationError("Name is empty", "name");
        }

        return null;
    }
}
