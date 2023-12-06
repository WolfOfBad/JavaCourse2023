package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileWriterTest {
    @Test
    public void writeTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) throws IOException {
        Path file = Path.of(dir.toString(), "phrase.txt");

        FileWriter obj = new FileWriter();
        obj.writePhrase(file);

        assertThat(Files.readString(file)).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
