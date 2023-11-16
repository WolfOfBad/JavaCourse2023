package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileCloner {
    private static final Pattern PATH_PATTERN = Pattern.compile("^([^.]+)\\.?([^.]*)$");

    public Path clonePath(Path path) throws IOException {
        Matcher matcher = PATH_PATTERN.matcher(path.getFileName().toString());
        if (!matcher.find()) {
            throw new IllegalArgumentException("Wrong file format");
        }

        String name = matcher.group(1);
        String extension = matcher.group(2).isEmpty() ? "" : "." + matcher.group(2);
        Path copiedPath = findName(path.getParent().toString(), name, extension);

        copyDirectory(path, copiedPath);

        return copiedPath;
    }

    private Path findName(String parent, String name, String extension) {
        Path copiedPath = Path.of(parent, name + " — копия" + extension);

        if (Files.exists(copiedPath)) {
            String copyPath = name + " — копия ";
            int counter = 2;
            copiedPath = Path.of(parent, copyPath + "(" + counter + ")" + extension);
            while (Files.exists(copiedPath)) {
                counter++;
                copiedPath = Path.of(parent, copyPath + "(" + counter + ")" + extension);
            }
        }

        return copiedPath;
    }

    private void copyDirectory(Path source, Path destination) throws IOException {
        try (Stream<Path> files = Files.walk(source)) {
            files.forEach(file -> {
                String copy = file.toString().replace(source.toString(), destination.toString());
                Path copyPath = Path.of(copy);
                try {
                    Files.copy(file, copyPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
