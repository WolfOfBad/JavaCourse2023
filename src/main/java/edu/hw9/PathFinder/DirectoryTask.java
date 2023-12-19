package edu.hw9.PathFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectoryTask extends RecursiveTask<List<File>> {
    private final File file;
    private final int count;

    public DirectoryTask(File file, int count) {
        this.file = file;
        this.count = count;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            if (files.length >= count) {
                result.add(file);
            }

            List<DirectoryTask> tasks = new ArrayList<>();
            for (File f : files) {
                DirectoryTask task = new DirectoryTask(f, count);
                task.fork();
                tasks.add(task);
            }

            for (DirectoryTask task : tasks) {
                result.addAll(task.join());
            }
        }
        return result;
    }
}
