package edu.project1;

import java.util.List;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.test.appender.ListAppender;

public class LoggerStringsGetter {
    ListAppender appender;
    Logger logger;

    public LoggerStringsGetter(Class<?> cls) {
        LoggerContext loggerContext = LoggerContext.getContext(false);
        logger = (Logger) loggerContext.getLogger(cls);
        appender = new ListAppender("List");
        appender.start();
        loggerContext.getConfiguration().addLoggerAppender(logger, appender);
    }

    public List<String> getStrings() {
        return appender
            .getEvents()
            .stream()
            .map(event -> event.getMessage().getFormattedMessage())
            .toList();
    }

    public void endReading() {
        appender.stop();
        logger.removeAppender(appender);
    }
}
