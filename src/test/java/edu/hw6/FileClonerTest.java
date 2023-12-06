package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileClonerTest {
    @Test
    public void cloneFileTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path file = Path.of(dir.toString(), "cloner test.txt");
        Files.write(file, "test string".getBytes());

        FileCloner cloner = new FileCloner();
        Path copy1 = cloner.clonePath(file);
        Path copy2 = cloner.clonePath(file);
        Path copy3 = cloner.clonePath(file);

        String copiedString1 = Files.readString(copy1);
        String copiedString2 = Files.readString(copy2);
        String copiedString3 = Files.readString(copy3);

        assertThat(copy1.getFileName().toString()).isEqualTo("cloner test — копия.txt");
        assertThat(copy2.getFileName().toString()).isEqualTo("cloner test — копия (2).txt");
        assertThat(copy3.getFileName().toString()).isEqualTo("cloner test — копия (3).txt");
        assertThat(copiedString1).isEqualTo("test string");
        assertThat(copiedString2).isEqualTo("test string");
        assertThat(copiedString3).isEqualTo("test string");

        Files.delete(copy1);
        Files.delete(copy2);
        Files.delete(copy3);
    }
}
