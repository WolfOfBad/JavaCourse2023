package edu.hw6;

import edu.hw6.DirectoryFilter.AbstractFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.DirectoryFilter.Filters.checkSize;
import static edu.hw6.DirectoryFilter.Filters.globMatches;
import static edu.hw6.DirectoryFilter.Filters.isReadable;
import static edu.hw6.DirectoryFilter.Filters.isRegularFile;
import static edu.hw6.DirectoryFilter.Filters.magicNumber;
import static edu.hw6.DirectoryFilter.Filters.regexContains;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DirectoryFilterTest {
    private final Path directory = Path.of("./src/test/resources/hw6/DirectoryFilterTest/");
    private final Path pngFile = Path.of("./src/test/resources/hw6/DirectoryFilterTest/gosling=).png");
    private final Path dirFile = Path.of("./src/test/resources/hw6/DirectoryFilterTest/directory");
    private final Path textFile1 = Path.of("./src/test/resources/hw6/DirectoryFilterTest/text file.txt");
    private final Path textFile2 = Path.of("./src/test/resources/hw6/DirectoryFilterTest/directory/text file — копия.txt");
    private final Path docxFile = Path.of("./src/test/resources/hw6/DirectoryFilterTest/word=).docx");

    private boolean compareStreamAndSet(DirectoryStream<Path> stream, Set<Path> set) {
        var iterator = stream.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            if (!set.contains(iterator.next())) {
                return false;
            }
        }
        return count == set.size();
    }

    @Test
    @DisplayName("isReadable")
    public void isReadableTest() throws IOException {
        AbstractFilter filter = isReadable;
        Set<Path> correctFiles = Set.of(pngFile, dirFile, textFile1, docxFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("regularFile")
    public void regularFileTest() throws IOException {
        AbstractFilter filter = isRegularFile;
        Set<Path> correctFiles = Set.of(pngFile, textFile1, docxFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("checkSize")
    public void checkSizeTest() throws IOException {
        AbstractFilter filter = checkSize(size -> size >= 1_000_000);
        Set<Path> correctFiles = Set.of(pngFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("magicNumber")
    public void magicNumberTest() throws IOException {
        AbstractFilter filter = magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G');
        Set<Path> correctFiles = Set.of(pngFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("globMatches")
    public void globMatchesTest() throws IOException {
        AbstractFilter filter = globMatches("*.txt");
        Set<Path> correctFiles = Set.of(textFile1);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("regexContains")
    public void regexContainsTest() throws IOException {
        AbstractFilter filter = regexContains("=\\)");
        Set<Path> correctFiles = Set.of(pngFile, docxFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }

    @Test
    @DisplayName("Chain of filters")
    public void chainTest() throws IOException {
        AbstractFilter filter = isRegularFile
            .and(isReadable)
            .and(checkSize(size -> size >= 1_000_000))
            .or(globMatches("*.txt"))
            .negate();
        Set<Path> correctFiles = Set.of(dirFile, docxFile);

        try (var files = Files.newDirectoryStream(directory, filter)) {
            assertThat(compareStreamAndSet(files, correctFiles)).isTrue();
        }
    }
}
