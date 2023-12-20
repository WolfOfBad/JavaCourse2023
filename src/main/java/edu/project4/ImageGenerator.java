package edu.project4;

import edu.project4.postprocessing.GammaCorrector;
import edu.project4.postprocessing.SymmetryProcessor;
import edu.project4.records.ColoredTransformation;
import edu.project4.records.Config;
import edu.project4.records.PixelMap;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.render.Renderer;
import edu.project4.render.SingleThreadRenderer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageGenerator {
    public void generate(Config config) {
        List<ColoredTransformation> transformations = getTransformationList(
            config.randomAffineTransformations(),
            config.presetTransformations()
        );

        PixelMap map;
        Renderer renderer;

        if (config.threads() <= 1) {
            renderer = new SingleThreadRenderer();
        } else {
            renderer = new MultiThreadRenderer(config.threads());
        }

        map = renderer.render(
            config.imageBorders(),
            config.generationBorders(),
            transformations,
            config.samples(),
            config.iterations()
        );

        if (config.symmetry() > 1) {
            map = new SymmetryProcessor(config.symmetry()).process(map);
        }

        if (config.correction()) {
            map = new GammaCorrector(config.gamma()).process(map);
        }

        ImageUtils.save(map, Path.of(config.directory().toString(), config.filename()), config.format());
    }

    private List<ColoredTransformation> getTransformationList(
        int randomCount,
        ColoredTransformation[] presetTransformations
    ) {
        List<ColoredTransformation> transformations = new ArrayList<>();

        for (int i = 0; i < randomCount; i++) {
            transformations.add(ColoredTransformation.getRandom());
        }

        if (presetTransformations != null) {
            transformations.addAll(Arrays.asList(presetTransformations));
        }

        return transformations;
    }

}
