package edu.project4;

import edu.project4.records.Config;
import java.time.LocalDateTime;

public final class Main {
    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        Config config = new Config(
            1440,
            2560,
            -1.77,
            1.77,
            -1.0,
            1.0,
            2_000_000,
            30,
            4,
            null,
            ImageFormat.PNG,
            true,
            "./",
            LocalDateTime.now().toString().replace(":", "-"),
            2.2,
            Runtime.getRuntime().availableProcessors(),
            1
        );

        ImageGenerator generator = new ImageGenerator();
        generator.generate(config);
    }
}
