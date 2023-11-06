package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AverageUserTime {
    private long incorrectSessions;

    public Duration getAverageUserTime(List<String> sessions) {
        Duration result = Duration.ZERO;
        incorrectSessions = 0;
        for (String session : sessions) {
            result = result.plus(parseString(session));
        }
        result = result.dividedBy(sessions.size() - incorrectSessions);

        return result;
    }

    @SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
    private Duration parseString(String session) {
        // 2022-03-12, 20:20 - 2022-03-12, 23:50
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2}) - "
            + "(\\d{4}-\\d{2}-\\d{2}), (\\d{2}:\\d{2})$");
        Matcher matcher = pattern.matcher(session);
        if (matcher.find()) {
            LocalDateTime start = LocalDateTime.parse(matcher.group(1) + "T" + matcher.group(2) + ":00");
            LocalDateTime end = LocalDateTime.parse(matcher.group(3) + "T" + matcher.group(4) + ":00");
            return Duration.between(start, end);
        }
        // skip incorrect string
        incorrectSessions++;
        return Duration.ZERO;
    }
}
