package edu.hw8.CustomThreadPool;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
