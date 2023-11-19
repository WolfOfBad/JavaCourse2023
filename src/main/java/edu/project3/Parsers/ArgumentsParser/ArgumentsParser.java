package edu.project3.Parsers.ArgumentsParser;

import edu.project3.Output.ADOCWriter;
import edu.project3.Output.MarkdownWriter;
import edu.project3.Output.StatisticsWriter;
import edu.project3.Parsers.TimeParsers.TimeParsersChain;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArgumentsParser {
    private static final String PATH_ARG = "--path";
    private static final String FROM_ARG = "--from";
    private static final String TO_ARG = "--to";
    private static final String FORMAT_ARG = "--format";
    private static final String OUTPUT_ARG = "--output";
    private static final Set<String> ALLOWED_ARGS = Set.of(
        PATH_ARG,
        FROM_ARG,
        TO_ARG,
        FORMAT_ARG,
        OUTPUT_ARG
    );
    private final HttpClient httpClient;

    public ArgumentsParser(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public UserArguments parseArgs(String[] args) throws IOException, InterruptedException {
        Map<String, String> arguments = new HashMap<>();

        for (int i = 0; i < args.length; i += 2) {
            if (ALLOWED_ARGS.contains(args[i])) {
                arguments.put(args[i], args[i + 1]);
            } else {
                throw new IllegalArgumentException("Unexpected argument: " + args[i]);
            }
        }
        if (!arguments.containsKey(PATH_ARG)) {
            throw new IllegalArgumentException("Arguments must contain path to log files");
        }

        return new UserArguments(
            parseFiles(arguments.get(PATH_ARG)),
            parseFromDateTime(arguments.get(FROM_ARG)),
            parseToDateTime(arguments.get(TO_ARG)),
            parseFormat(arguments.get(FORMAT_ARG)),
            parseOutput(arguments.get(OUTPUT_ARG))
        );
    }

    private StatisticsWriter parseFormat(String format) {
        return switch (format) {
            case null -> new MarkdownWriter();
            case "markdown" -> new MarkdownWriter();
            case "adoc" -> new ADOCWriter();
            default -> throw new IllegalStateException("Unexpected format argument: " + format);
        };
    }

    private List<Path> parseFiles(String path)
        throws IOException, InterruptedException {
        if (path.contains("*")) {
            return openDirectoryWithGlob(path);
        } else if (path.contains("http")) {
            return getHTTPFile(path);
        } else {
            return openSimpleFile(path);
        }
    }

    private List<Path> openSimpleFile(String path) {
        return List.of(Paths.get(path));
    }

    private List<Path> openDirectoryWithGlob(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");
        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);
        Path root = Paths.get(path.substring(0, lastSlashIndex));
        String glob = "glob:" + path.substring(lastSlashIndex + 1);

        List<Path> result = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (pathMatcher.matches(file)) {
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return result;
    }

    private List<Path> getHTTPFile(String uri) throws IOException, InterruptedException {
        Path file = Files.createTempFile("httpLog", ".tmp");
        file.toFile().deleteOnExit();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Files.write(file, response.body().getBytes());

        return List.of(file);
    }

    private OffsetDateTime parseFromDateTime(String dateTime) {
        if (dateTime == null) {
            return OffsetDateTime.MIN;
        }

        OffsetDateTime result = TimeParsersChain.parse(dateTime);
        if (result == null) {
            return OffsetDateTime.MIN;
        }
        return result;
    }

    private OffsetDateTime parseToDateTime(String dateTime) {
        if (dateTime == null) {
            return OffsetDateTime.MAX;
        }

        OffsetDateTime result = TimeParsersChain.parse(dateTime);
        if (result == null) {
            return OffsetDateTime.MAX;
        }
        return result.plusDays(1);
    }

    private Path parseOutput(String file) {
        if (file == null) {
            return Paths.get("");
        }
        return Paths.get(file);
    }
}
