package edu.hw4.Validation;

import edu.hw4.Animal;

public class HeightValidator implements Validator {
    @Override
    public ValidationError checkAnimal(Animal animal) {
        if (animal.height() < 0) {
            return new ValidationError("Height is negative", "height");
        }

        return null;
    }
}
