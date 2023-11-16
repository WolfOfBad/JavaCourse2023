package edu.hw6.DirectoryFilter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Filters {
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter isReadable = Files::isReadable;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter isRegularFile = Files::isRegularFile;

    private Filters() {
    }

    public static AbstractFilter checkSize(Predicate<Long> predicate) {
        return (Path path) -> predicate.test(Files.size(path));
    }

    public static AbstractFilter magicNumber(byte firstByte, byte... nextBytes) {
        return (Path path) -> {
            if (!Files.isRegularFile(path)) {
                return false;
            }

            byte[] fileBytes = Files.readAllBytes(path);
            if (firstByte != fileBytes[0]) {
                return false;
            }

            int i = 1;
            for (byte b : nextBytes) {
                if (fileBytes[i++] != b) {
                    return false;
                }
            }

            return true;
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return (Path path) -> path.getFileSystem()
            .getPathMatcher("glob:**/*" + glob)
            .matches(path);
    }

    public static AbstractFilter regexContains(String regex) {
        return (Path path) -> {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(path.getFileName()
                    .toString())
                .find();
        };
    }
}
