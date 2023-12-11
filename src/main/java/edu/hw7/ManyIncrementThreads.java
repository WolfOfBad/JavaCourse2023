package edu.hw7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ManyIncrementThreads {
    private ManyIncrementThreads() {
    }

    public static int incrementThreads(int value, int add, int threadsCount) {
        AtomicInteger result = new AtomicInteger(value);
        CountDownLatch countDown = new CountDownLatch(add);
        int goal = value + add;
        Stream<Thread> threads = Stream.generate(() -> new Thread(() -> {
            while (countDown.getCount() != 0) {
                result.updateAndGet(x -> x != goal ? x + 1 : x);
                countDown.countDown();
            }
        })).limit(threadsCount);

        threads.forEach(Thread::start);
        try {
            countDown.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result.get();
    }
}
