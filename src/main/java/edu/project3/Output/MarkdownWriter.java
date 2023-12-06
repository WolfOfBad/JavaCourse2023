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
public class MarkdownWriter implements StatisticsWriter {
    @Override
    public void writeStatistics(LogReport statistics, Path file) throws IOException {
        Path output = Paths.get(file.toString(), "logStats.md");
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
        List<String> lines = List.of(
            "#### Общая информация",
            "|Метрика|Значение|",
            "|:------|-------:|",
            "|Файлы|" + info.files() + "|",
            "|Начало логгирования|" + (info.from() != null ? info.from().toString() : "-") + "|",
            "|Конец логгирования|" + (info.to() != null ? info.to().toString() : "-") + "|",
            "|Количество запросов|" + info.requests() + "|",
            "|Средний размер ответа|" + info.averageResponse() + "|"
        );

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeRequestedResources(Map<String, Integer> resources, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("#### Запрашиваемые ресурсы");
        lines.add("|Ресурс|Количество|");
        lines.add("|:-----|-------:|");

        resources.entrySet()
            .stream()
            .sorted((first, second) -> second.getValue().compareTo(first.getValue()))
            .forEach(entry -> lines.add("|" + entry.getKey() + "|" + entry.getValue() + "|"));

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeCodes(Map<HTTPCode, Integer> codes, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("#### Коды ответа");
        lines.add("|Код|Имя|Количество|");
        lines.add("|:--|:-:|---------:|");

        codes.entrySet()
            .stream()
            .sorted((first, second) -> second.getValue().compareTo(first.getValue()))
            .forEach(entry -> lines.add("|" + entry.getKey().code()
                + "|" + entry.getKey().name() + "|" + entry.getValue() + "|"));

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeDayRequests(Map<LocalDate, Integer> dates, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("#### Запросы по дням");
        lines.add("|День|Количество запросов|");
        lines.add("|:---|------------------:|");

        dates.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> lines.add("|" + entry.getKey() + "|" + entry.getValue() + "|"));

        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    private void writeTimeRequests(Map<Integer, Integer> times, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("#### Запросы по часам");
        lines.add("|Час|Количество запросов|");
        lines.add("|:--|------------------:|");

        times.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> lines.add("|" + entry.getKey() + "|" + entry.getValue() + "|"));

        Files.write(file, lines, StandardOpenOption.APPEND);
    }
}
