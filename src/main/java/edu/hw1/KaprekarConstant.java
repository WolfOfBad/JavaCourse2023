package edu.hw1;

import java.util.Arrays;

public final class KaprekarConstant {
    private KaprekarConstant() {
    }

    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int NUMBER_LENGTH = 4;
    private static final int SYSTEM_RADIX = 10;
    private static final int UPPER_VALUE = 9999;
    private static final int LOWER_VALUE = 1001;

    public static int countK(int number) {
        if (number < LOWER_VALUE || number > UPPER_VALUE) {
            throw new IllegalArgumentException("Number must be in interval from 1001 to 9999");
        }
        return countSteps(number);
    }

    private static int countSteps(int number) {
        if (number == KAPREKAR_CONSTANT) {
            return 0;
        }

        int[] digits = new int[NUMBER_LENGTH];
        int num = number;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            digits[i] = num % SYSTEM_RADIX;
            num /= SYSTEM_RADIX;
        }
        digits = Arrays.stream(digits).sorted().toArray();

        int min = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            min = min * SYSTEM_RADIX + digits[i];
        }
        int max = 0;
        for (int i = NUMBER_LENGTH - 1; i >= 0; i--) {
            max = max * SYSTEM_RADIX + digits[i];
        }

        return countK(max - min) + 1;
    }
}
