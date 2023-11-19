package edu.project3.Statistics.Records;

import java.time.LocalDate;
import java.util.Map;

public record LogReport(
    GeneralInfo general,
    Map<String, Integer> requestedResources,
    Map<HTTPCode, Integer> codes,
    Map<LocalDate, Integer> dayRequests,
    Map<Integer, Integer> timeRequests
) {
}
