package edu.project4;

import edu.project4.postprocessing.GammaCorrector;
import edu.project4.postprocessing.SymmetryProcessor;
import edu.project4.records.Color;
import edu.project4.records.ImageBorders;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PostProcessingTest {
    @Test
    public void gammaTest() {
        // не использую Map.of, потому что в методе изменяется переданная мапа
        Map<Point, Pixel> map = new HashMap<>();
        map.put(new Point(0, 0), new Pixel(new Color(255, 255, 255), 10));
        map.put(new Point(1, 0), new Pixel(new Color(100, 100, 100), 5));
        PixelMap pixelMap = new PixelMap(
            map,
            new ImageBorders(1, 2)
        );

        pixelMap = new GammaCorrector(2.2).process(pixelMap);

        assertThat(pixelMap.pixelMap()).containsExactlyInAnyOrderEntriesOf(
            Map.ofEntries(
                Map.entry(new Point(0, 0), new Pixel(new Color(255, 255, 255), 10)),
                Map.entry(new Point(1, 0), new Pixel(new Color(85, 85, 85), 5))
            )
        );
    }

    @Test
    public void symmetryTest() {
        PixelMap map = new PixelMap(
            Map.of(
                new Point(0, 0), new Pixel(new Color(255, 255, 255), 1),
                new Point(1, 1), new Pixel(new Color(255, 255, 255), 1)
            ),
            new ImageBorders(3, 3)
        );

        map = new SymmetryProcessor(2).process(map);

        assertThat(map.pixelMap()).containsExactlyInAnyOrderEntriesOf(
            Map.ofEntries(
                Map.entry(new Point(0, 0), new Pixel(new Color(255, 255, 255), 1)),
                Map.entry(new Point(1, 1), new Pixel(new Color(255, 255, 255), 2)),
                Map.entry(new Point(2, 2), new Pixel(new Color(255, 255, 255), 1))
            )
        );
    }
}
