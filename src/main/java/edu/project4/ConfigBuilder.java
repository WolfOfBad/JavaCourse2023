package edu.project4;

import edu.project4.records.ColoredTransformation;
import edu.project4.records.Config;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageBorders;
import java.nio.file.Path;

@SuppressWarnings("MagicNumber")
public class ConfigBuilder {
    // default values
    private ImageBorders imageBorders = new ImageBorders(1000, 1000);
    private GenerationBorders generationBorders = new GenerationBorders(-1, 1, -1, 1);
    private int samples = 1_000_000;
    private int iterations = 30;
    private int randomAffineTransformations = 5;
    private ColoredTransformation[] presetTransformations = null;
    private ImageFormat format = ImageFormat.PNG;
    private boolean correction = false;
    private Path directory = Path.of("./");
    private String filename = "defaultConfig";
    private double gamma = 2.2;
    private int threads = 1;
    private int symmetry = 1;

    public ConfigBuilder imageSize(int width, int height) {
        this.imageBorders = new ImageBorders(height, width);
        return this;
    }

    public ConfigBuilder generationBorders(double xMin, double xMax, double yMin, double yMax) {
        this.generationBorders = new GenerationBorders(xMin, xMax, yMin, yMax);
        return this;
    }

    public ConfigBuilder samples(int samples) {
        this.samples = samples;
        return this;
    }

    public ConfigBuilder iterations(int iterations) {
        this.iterations = iterations;
        return this;
    }

    public ConfigBuilder transformations(int randomTransformations, ColoredTransformation[] presetTransformations) {
        this.randomAffineTransformations = randomTransformations;
        this.presetTransformations = presetTransformations;
        return this;
    }

    public ConfigBuilder format(ImageFormat format) {
        this.format = format;
        return this;
    }

    public ConfigBuilder output(Path directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        return this;
    }

    public ConfigBuilder correction(double gamma) {
        this.correction = true;
        this.gamma = gamma;
        return this;
    }

    public ConfigBuilder threads(int threads) {
        this.threads = threads;
        return this;
    }

    public ConfigBuilder symmetry(int symmetryCount) {
        this.symmetry = symmetryCount;
        return this;
    }

    public Config build() {
        return new Config(
            imageBorders,
            generationBorders,
            samples,
            iterations,
            randomAffineTransformations,
            presetTransformations,
            format,
            correction,
            directory,
            filename,
            gamma,
            threads,
            symmetry
        );
    }

}
