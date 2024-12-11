package org.example.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

    public static String get() {
        return ZonedDateTime.now().format(FORMATTER);
    }
}
