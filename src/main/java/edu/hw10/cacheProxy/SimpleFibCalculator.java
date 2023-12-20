package edu.hw10.cacheProxy;

public class SimpleFibCalculator implements FibCalculator {
    @Override
    public long calculate(long value) {
        if (value == 0) {
            return 0;
        }

        long first = 1;
        long second = 1;
        for (int i = 2; i < value; i++) {
            long temp = second;
            second = first + second;
            first = temp;
        }
        return second;
    }
}
