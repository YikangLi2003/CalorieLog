package org.example.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    public enum Level {
        INFO, WARNING, ERROR, DEBUG
    }

    private final PrintWriter fileWriter;  // 文件输出流

    public Logger(String logFilename) {
        if (logFilename != null) {
            try {
                fileWriter = new PrintWriter(new FileWriter(logFilename, true), true); // 追加模式
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize log file writer: " + e.getMessage(), e);
            }
        } else {
            fileWriter = null;
        }
    }

    public void log(Level level, String message, Object... args) {
        String timestamp = Timestamp.get();
        String formattedMessage = (args.length > 0) ? String.format(message, args) : message;
        String logMessage = String.format("%s %s %s", timestamp, level, formattedMessage);

        System.out.println(logMessage);

        if (fileWriter != null) {
            fileWriter.println(logMessage);
        }
    }

    public void closeLogFile() {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
}
