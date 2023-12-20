package edu.project4;

import edu.project4.records.Color;
import edu.project4.records.ColoredTransformation;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageBorders;
import edu.project4.records.PixelMap;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.render.Renderer;
import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformations.RandomTransformationProvider;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RendererTest {
    @Test
    public void singleThreadTest() {
        Renderer renderer = new SingleThreadRenderer();

        PixelMap result = renderer.render(
            new ImageBorders(10, 10),
            new GenerationBorders(-1, 1, -1, 1),
            List.of(
                new ColoredTransformation(
                    RandomTransformationProvider.getRandom(),
                    Color.getRandom()
                )),
            10,
            10
        );

        assertThat(result).isNotNull();
    }

    @Test
    public void multiThreadTest() {
        Renderer renderer = new MultiThreadRenderer(Runtime.getRuntime().availableProcessors());

        PixelMap result = renderer.render(
            new ImageBorders(10, 10),
            new GenerationBorders(-1, 1, -1, 1),
            List.of(
                new ColoredTransformation(
                    RandomTransformationProvider.getRandom(),
                    Color.getRandom()
                )),
            10,
            10
        );

        assertThat(result).isNotNull();
    }

}
