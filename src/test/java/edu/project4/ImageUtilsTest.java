package edu.project4;

import edu.project4.records.Color;
import edu.project4.records.ImageBorders;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {
    private static Arguments[] args() {
        return new Arguments[] {
            Arguments.of(ImageFormat.PNG),
            Arguments.of(ImageFormat.JPEG),
            Arguments.of(ImageFormat.BMP)
        };
    }

    @ParameterizedTest
    @MethodSource("args")
    public void saveTest(ImageFormat format, @TempDir(cleanup = CleanupMode.ALWAYS) Path dir) {
        PixelMap pixelMap = new PixelMap(
            Map.of(new Point(0, 0), new Pixel(new Color(255, 255, 255), 1)),
            new ImageBorders(1, 1)
        );

        Path file = Path.of(dir.toString(), "img." + format.toString().toLowerCase());
        ImageUtils.save(pixelMap, file, format);

        assertThat(file).exists();
    }

}
