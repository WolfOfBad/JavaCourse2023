package edu.project4.render;

import edu.project4.records.Color;
import edu.project4.records.ColoredTransformation;
import edu.project4.records.GenerationBorders;
import edu.project4.records.ImageBorders;
import edu.project4.records.Pixel;
import edu.project4.records.PixelMap;
import edu.project4.records.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MultiThreadRenderer implements Renderer {
    private final int threadCount;

    public MultiThreadRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public PixelMap render(
        ImageBorders imageBorders,
        GenerationBorders generationBorders,
        List<ColoredTransformation> transformations,
        int samples,
        int iterations
    ) {
        List<Callable<PixelMap>> tasks = new ArrayList<>();
        int remains = samples % threadCount;

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            tasks.add(() -> new SingleThreadRenderer().render(
                imageBorders,
                generationBorders,
                transformations,
                samples / threadCount + ((finalI < remains) ? 1 : 0),
                iterations
            ));
        }

        try (ExecutorService pool = Executors.newFixedThreadPool(threadCount)) {
            List<Future<PixelMap>> futures = pool.invokeAll(tasks);

            List<PixelMap> result = new ArrayList<>();
            for (var future : futures) {
                result.add(future.get());
            }

            return new PixelMap(merge(result), imageBorders);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Point, Pixel> merge(List<PixelMap> merging) {
        return merging.stream()
            .map(PixelMap::pixelMap)
            .flatMap(pixelMap -> pixelMap.entrySet().stream())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                mergePixels()
            ));
    }

    private static BinaryOperator<Pixel> mergePixels() {
        return (first, second) -> new Pixel(
            new Color(
                (first.color().r() + second.color().r()) / 2,
                (first.color().g() + second.color().g()) / 2,
                (first.color().b() + second.color().b()) / 2
            ),
            first.hitCount() + second.hitCount()
        );
    }

}
