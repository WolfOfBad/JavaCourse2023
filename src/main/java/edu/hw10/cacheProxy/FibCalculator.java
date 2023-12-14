package edu.hw10.cacheProxy;

public interface FibCalculator {
    @Cache(persist = true)
    long calculate(long value);
}
