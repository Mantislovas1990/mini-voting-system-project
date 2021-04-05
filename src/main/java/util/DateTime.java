package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private DateTime() {}

    public static String date() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(format);
    }
}
