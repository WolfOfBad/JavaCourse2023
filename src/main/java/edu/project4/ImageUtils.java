package edu.project4;

import edu.project4.records.Color;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private static final int RED = 16;
    private static final int GREEN = 8;

    private ImageUtils() {
    }

    public static void save(PixelMap pixelMap, Path path, ImageFormat format) {
        BufferedImage image = new BufferedImage(
            pixelMap.borders().width(),
            pixelMap.borders().height(),
            BufferedImage.TYPE_INT_RGB
        );

        setPixels(image, pixelMap);

        try {
            Path fullPath = getPathWithExtension(path, format);
            ImageIO.write(image, format.toString().toLowerCase(), fullPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setPixels(BufferedImage image, PixelMap map) {
        Pixel defaultPixel = new Pixel(new Color(0, 0, 0), 1);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = map.pixelMap().getOrDefault(new Point(x, y), defaultPixel);
                int rgb = (pixel.color().r() << RED) | (pixel.color().g() << GREEN) | pixel.color().b();
                image.setRGB(x, y, rgb);
            }
        }
    }

    private static Path getPathWithExtension(Path path, ImageFormat format) {
        String filename = path.getFileName().toString().split("\\.")[0];
        return path.resolveSibling(filename + "." + format.toString().toLowerCase());
    }

}
