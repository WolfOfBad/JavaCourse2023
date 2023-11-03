package edu.hw4.Validation;

import edu.hw4.Animal;

public interface Validator {
    ValidationError checkAnimal(Animal animal);
}
