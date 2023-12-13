package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class FactorialCounter {
    private FactorialCounter() {
    }

    public static BigInteger getFactorial(long value) {
        return LongStream.rangeClosed(1, value)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
