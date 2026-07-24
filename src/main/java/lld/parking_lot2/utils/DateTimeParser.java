package lld.parking_lot2.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeParser {
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("d MMM h:mm a uuuu", Locale.ENGLISH);
    private DateTimeParser() { }
    public static LocalDateTime parse(String value) { return LocalDateTime.parse(value, FORMAT); }
}
