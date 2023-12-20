package edu.hw9.PathFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileTask extends RecursiveTask<List<File>> {
    private final File file;
    private final Predicate<File> predicate;

    public FileTask(File file, Predicate<File> predicate) {
        this.file = file;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        if (predicate.test(file)) {
            result.add(file);
        }

        File[] files = file.listFiles();
        if (files != null) {
            List<FileTask> tasks = new ArrayList<>();

            for (File f : files) {
                FileTask task = new FileTask(f, predicate);
                task.fork();
                tasks.add(task);
            }

            for (FileTask task : tasks) {
                result.addAll(task.join());
            }
        }

        return result;
    }
}
