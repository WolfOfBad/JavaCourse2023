package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ManyIncrementThreads {
    private ManyIncrementThreads() {
    }

    public static int incrementThreads(int value, int threadsCount) {
        List<Thread> threads = new ArrayList<>(threadsCount);
        AtomicInteger integer = new AtomicInteger();
        integer.set(value);
        for (int i = 0; i < threadsCount; i++) {
            threads.add(new Thread(integer::incrementAndGet));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return integer.get();
    }
}
