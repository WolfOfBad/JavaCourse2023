package edu.project3.Statistics;

import edu.project3.Parsers.LogParser.LogRecord;
import edu.project3.Statistics.Records.GeneralInfo;
import edu.project3.Statistics.Records.HTTPCode;
import edu.project3.Statistics.Records.LogReport;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogStatistics {
    // чтобы не заполнять все поля рекордов через стримы много раз пробегая все логи,
    // сначала находит значения полей, а потом уже возвращает рекорды
    // поля для GeneralInfo
    private OffsetDateTime from;
    private OffsetDateTime to;
    private int requests;
    private long bytesSent;

    // поля для LogStatistics
    private Map<String, Integer> requestedResources;
    private Map<HTTPCode, Integer> codes;
    private Map<LocalDate, Integer> dayRequests;
    private Map<Integer, Integer> timeRequests;
    private List<Path> files;

    @SuppressWarnings("MagicNumber")
    public LogReport getLogReport(List<LogRecord> logs) {
        from = null;
        to = null;
        requests = 0;
        bytesSent = 0;

        requestedResources = new HashMap<>();
        codes = new HashMap<>();
        dayRequests = new HashMap<>();
        timeRequests = new HashMap<>();
        files = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            timeRequests.put(i, 0);
        }

        for (LogRecord log : logs) {
            processLog(log);
        }

        return new LogReport(
            getGeneralInfo(),
            requestedResources,
            codes,
            dayRequests,
            timeRequests
        );
    }

    private void processLog(LogRecord log) {
        requests++;
        bytesSent += log.bytesSent();

        if (from == null || log.time().isBefore(from)) {
            from = log.time();
        }
        if (to == null || log.time().isAfter(to)) {
            to = log.time();
        }

        if (!files.contains(log.file())) {
            files.add(log.file());
        }

        String resource = log.resource();
        if (!requestedResources.containsKey(log.resource())) {
            requestedResources.put(resource, 0);
        }
        requestedResources.replace(resource, requestedResources.get(resource) + 1);

        HTTPCode code = log.code();
        if (!codes.containsKey(code)) {
            codes.put(code, 0);
        }
        codes.replace(code, codes.get(code) + 1);

        LocalDate date = LocalDate.from(log.time());
        if (!dayRequests.containsKey(date)) {
            dayRequests.put(date, 0);
        }
        dayRequests.replace(date, dayRequests.get(date) + 1);

        int hour = LocalTime.from(log.time()).getHour();
        timeRequests.replace(hour, timeRequests.get(hour) + 1);
    }

    private GeneralInfo getGeneralInfo() {
        String filesStr;
        if (files.size() == 1) {
            filesStr = files.get(0).getFileName().toString();
        } else {
            filesStr = "(" + files.size() + ")";
        }

        return new GeneralInfo(
            filesStr,
            from,
            to,
            requests,
            requests != 0 ? bytesSent / requests : 0
        );
    }

}
