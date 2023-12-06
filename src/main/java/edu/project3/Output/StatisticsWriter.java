package edu.project3.Output;

import edu.project3.Statistics.Records.LogReport;
import java.io.IOException;
import java.nio.file.Path;

public interface StatisticsWriter {
    void writeStatistics(LogReport statistics, Path file) throws IOException;
}
