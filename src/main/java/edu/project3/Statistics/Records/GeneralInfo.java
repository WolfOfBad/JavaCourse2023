package edu.project3.Statistics.Records;

import java.time.OffsetDateTime;

public record GeneralInfo(
    String files,
    OffsetDateTime from,
    OffsetDateTime to,
    int requests,
    long averageResponse
) {
}
