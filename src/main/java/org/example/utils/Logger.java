package org.example.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public final static Logger INSTANCE = new Logger();

    public enum Level {
        INFO, WARNING, ERROR, DEBUG
    }

    private final PrintWriter printWriter;  // 文件输出流

    private Logger() {
        if (Boolean.parseBoolean(PropertyAccessor.getInstance().getProperty("log.to.file"))) {
            try {
                printWriter = new PrintWriter(
                        new FileWriter(
                                PropertyAccessor.getInstance().getProperty("log.filename"), true
                        ), true
                ); // 追加模式
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize log file writer: " + e.getMessage(), e);
            }
        } else {
            printWriter = null;
        }
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(Level level, String message, Object... args) {
        String timestamp = Timestamp.getInstance().get();
        String formattedMessage = (args.length > 0) ? String.format(message, args) : message;
        String logMessage = String.format("%s %s %s", timestamp, level, formattedMessage);

        System.out.println(logMessage);

        if (printWriter != null) {
            printWriter.println(logMessage);
        }
    }

    public void closeLogFile() {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
