package edu.hw6.DirectoryFilter;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter filter) {
        return (Path path) -> accept(path) && filter.accept(path);
    }

    default AbstractFilter or(AbstractFilter filter) {
        return (Path path) -> accept(path) || filter.accept(path);
    }

    default AbstractFilter negate() {
        return (Path path) -> !accept(path);
    }
}
