package edu.project3.Output;

import edu.project3.Statistics.Records.GeneralInfo;
import edu.project3.Statistics.Records.HTTPCode;
import edu.project3.Statistics.Records.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("MultipleStringLiterals")
public class ADOCWriter implements StatisticsWriter {
    @Override
    public void writeStatistics(LogReport statistics, Path file) throws IOException {
        Path output = Paths.get(file.toString(), "logStats.adoc");
        if (Files.notExists(output)) {
            Files.createFile(output);
        }
        Files.write(output, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        writeGeneralInfo(statistics.general(), output);
        writeRequestedResources(statistics.requestedResources(), output);
        writeCodes(statistics.codes(), output);
        writeDayRequests(statistics.dayRequests(), output);
        writeTimeRequests(statistics.timeRequests(), output);
    }

    private void writeGeneralInfo(GeneralInfo info, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("==== Общая информация");
        lines.add("[cols=2]");
        lines.add("|====");
        lines.add("|Метрика");
        lines.add("|Значение");
        lines.add("|Файлы");
        lines.add("|" + info.files());
        lines.add("|Начало логгирования");
        lines.add("|" + (info.from() != null ? info.from().toString() : "-"));
        lines.add("|Конец логгирования");
        lines.add("|" + (info.to() != null ? info.to().toString() : "-"));
        lines.add("|Количество запросов");
        lines.add("|" + info.requests());
        lines.add("|Средний размер ответа");
        lines.add("|" + info.averageResponse());
        lines.add("|====");

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeRequestedResources(Map<String, Integer> resources, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("==== Запрашиваемые ресурсы");
        lines.add("[cols=2]");
        lines.add("|====");
        lines.add("|Ресурс");
        lines.add("|Количество");

        resources.entrySet()
            .stream()
            .sorted((first, second) -> second.getValue().compareTo(first.getValue()))
            .forEach(entry -> {
                lines.add("|" + entry.getKey());
                lines.add("|" + entry.getValue());
            });
        lines.add("|====");

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeCodes(Map<HTTPCode, Integer> codes, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("==== Коды ответа");
        lines.add("[cols=3]");
        lines.add("|====");
        lines.add("|Код");
        lines.add("|Имя");
        lines.add("|Количество");

        codes.entrySet()
            .stream()
            .sorted((first, second) -> second.getValue().compareTo(first.getValue()))
            .forEach(entry -> {
                lines.add("|" + entry.getKey().code());
                lines.add("|" + entry.getKey().name());
                lines.add("|" + entry.getValue());
            });
        lines.add("|====");

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeDayRequests(Map<LocalDate, Integer> dates, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("==== Запросы по дням");
        lines.add("[cols=2]");
        lines.add("|====");
        lines.add("|День");
        lines.add("|Количество запросов");

        dates.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> {
                lines.add("|" + entry.getKey());
                lines.add("|" + entry.getValue());
            });
        lines.add("|====");

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeTimeRequests(Map<Integer, Integer> times, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("==== Запросы по часам");
        lines.add("[cols=2]");
        lines.add("|====");
        lines.add("|Час");
        lines.add("|Количество запросов");

        times.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> {
                lines.add("|" + entry.getKey());
                lines.add("|" + entry.getValue());
            });
        lines.add("|====");

        Files.write(file, lines, StandardOpenOption.APPEND);
    }
}
