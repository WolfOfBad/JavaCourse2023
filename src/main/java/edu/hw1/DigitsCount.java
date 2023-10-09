package edu.hw1;

public final class DigitsCount {
    private DigitsCount() {
    }

    private static final int SYSTEM_RADIX = 10;

    public static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        long num = number;
        while (num != 0) {
            num /= SYSTEM_RADIX;
            count++;
        }
        return count;
    }
}
