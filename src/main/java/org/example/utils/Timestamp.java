package org.example.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Timestamp {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(PropertyAccessor.getInstance().getProperty("utils.timestamp.pattern"));

    public static String get() {
        return ZonedDateTime.now().format(FORMATTER);
    }
}
