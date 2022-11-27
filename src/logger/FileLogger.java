package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FileLogger implements Logger {

    private static final String LOG_FILE_PATH = "./resources/log.txt";
    private final Class clazz;

    FileLogger(final Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void log(final LogLevel level, final String message) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // TODO Можно заменить MessageBuilder
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(level).append("] ")
                    .append("[").append(clazz).append("] ")
                    .append("[").append(LocalDateTime.now()).append("] ")
                    .append(message);

            printWriter.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(final String message) {
        log(LogLevel.DEBUG, message);
    }

    @Override
    public void info(final String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void warn(final String message) {
        log(LogLevel.WARN, message);
    }

    @Override
    public void error(final String message) {
        log(LogLevel.ERROR, message);
    }

    @Override
    public void fatal(final String message) {
        log(LogLevel.FATAL, message);
    }
}
