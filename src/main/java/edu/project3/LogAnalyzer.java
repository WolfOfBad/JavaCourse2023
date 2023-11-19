package edu.project3;

import edu.project3.Output.StatisticsWriter;
import edu.project3.Parsers.ArgumentsParser.ArgumentsParser;
import edu.project3.Parsers.ArgumentsParser.UserArguments;
import edu.project3.Parsers.LogParser.LogParser;
import edu.project3.Parsers.LogParser.LogRecord;
import edu.project3.Statistics.LogStatistics;
import edu.project3.Statistics.Records.LogReport;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

public class LogAnalyzer {
    private final ArgumentsParser argumentsParser = new ArgumentsParser(HttpClient.newHttpClient());
    private final LogParser logParser = new LogParser();
    private final LogStatistics logStatistics = new LogStatistics();

    public void analyze(String[] args) {
        try {
            UserArguments arguments = argumentsParser.parseArgs(args);

            List<LogRecord> logs = logParser.parse(arguments);

            LogReport report = logStatistics.getLogReport(logs);

            StatisticsWriter writer = arguments.writer();
            writer.writeStatistics(report, arguments.output());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
