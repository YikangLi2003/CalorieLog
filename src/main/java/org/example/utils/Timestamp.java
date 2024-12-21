package org.example.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {
    private static final Timestamp INSTANCE = new Timestamp();

    private final DateTimeFormatter FORMATTER;

    private Timestamp() {
        FORMATTER = DateTimeFormatter.ofPattern(
                PropertyAccessor.getInstance().getProperty("timestamp.pattern")
        );
    }

    public static Timestamp getInstance() {
        return INSTANCE;
    }

    public String get() {
        return ZonedDateTime.now().format(FORMATTER);
    }
}
