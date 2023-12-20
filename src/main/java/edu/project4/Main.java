package edu.project4;

import edu.project4.records.Config;
import java.nio.file.Path;
import java.time.LocalDateTime;

public final class Main {
    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        Config config = new ConfigBuilder()
            .imageSize(2560, 1440)
            .transformations(5, null)
            .format(ImageFormat.PNG)
            .generationBorders(-1.77, 1.77, -1.0, 1.0)
            .samples(3_000_000)
            .iterations(30)
            .output(Path.of("./"), LocalDateTime.now().toString())
            .threads(Runtime.getRuntime().availableProcessors())
            .symmetry(1)
            .correction(2.2)
            .build();

        ImageGenerator generator = new ImageGenerator();
        generator.generate(config);
    }
}
