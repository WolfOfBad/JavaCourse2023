package edu.hw8.CustomThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool implements ThreadPool {
    private final PoolThread[] pool;
    private final LinkedBlockingQueue<Runnable> queue;

    private CustomThreadPool(int threads) {
        pool = new PoolThread[threads];
        for (int i = 0; i < threads; i++) {
            pool[i] = new PoolThread();
        }
        queue = new LinkedBlockingQueue<>();
    }

    public static CustomThreadPool create(int threads) {
        return new CustomThreadPool(threads);
    }

    @Override
    public void start() {
        for (PoolThread thread : pool) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            try {
                queue.put(runnable);
                queue.notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        for (PoolThread poolThread : pool) {
            poolThread.end();
        }
    }

    private class PoolThread extends Thread {
        private boolean isRunning = true;

        public void end() {
            isRunning = false;
        }

        @Override
        public void run() {
            Runnable task;

            while (isRunning) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    task = queue.poll();
                }
                try {
                    if (task != null) {
                        task.run();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
