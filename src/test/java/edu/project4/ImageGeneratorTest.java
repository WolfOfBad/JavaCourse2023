package edu.project4;

import edu.project4.records.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageGeneratorTest {
    @Test
    public void generationTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        Config config = new Config(
            10,
            10,
            -1.77,
            1.77,
            -1.0,
            1.0,
            10,
            5,
            1,
            null,
            ImageFormat.PNG,
            true,
            dir.toString(),
            "img",
            2.2,
            Runtime.getRuntime().availableProcessors(),
            1
        );

        ImageGenerator generator = new ImageGenerator();
        generator.generate(config);

        assertThat(Path.of(dir.toString(), "img.png")).exists();
    }
}
