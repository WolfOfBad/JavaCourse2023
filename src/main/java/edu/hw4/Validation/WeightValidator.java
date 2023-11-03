package edu.hw4.Validation;

import edu.hw4.Animal;

public class WeightValidator implements Validator {
    @Override
    public ValidationError checkAnimal(Animal animal) {
        if (animal.weight() < 0) {
            return new ValidationError("Weight is negative", "weight");
        }

        return null;
    }
}
