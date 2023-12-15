package edu.project4;

import edu.project4.records.Config;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageGeneratorTest {
    @Test
    public void generationTest(@TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        Config config = new ConfigBuilder()
            .imageSize(10, 10)
            .transformations(5, null)
            .format(ImageFormat.PNG)
            .generationBorders(-1.77, 1.77, -1.0, 1.0)
            .samples(100)
            .iterations(30)
            .output(dir, "img")
            .threads(Runtime.getRuntime().availableProcessors())
            .symmetry(1)
            .correction(2.2)
            .build();

        ImageGenerator generator = new ImageGenerator();
        generator.generate(config);

        assertThat(Path.of(dir.toString(), "img.png")).exists();
    }
}
