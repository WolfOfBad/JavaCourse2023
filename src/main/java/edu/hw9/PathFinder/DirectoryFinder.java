package edu.hw9.PathFinder;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class DirectoryFinder {
    private final ForkJoinPool pool = new ForkJoinPool();

    public List<File> findDirectories(File path, int count) {
        DirectoryTask directoryTask = new DirectoryTask(path, count);

        return pool.invoke(directoryTask);
    }


    public List<File> findFiles(File file, Predicate<File> predicate) {
        FileTask fileTask = new FileTask(file, predicate);

        return pool.invoke(fileTask);
    }

}
