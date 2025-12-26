package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static LocalDate today() {
        return LocalDate.now();
    }

    public static String now() {
        return LocalDateTime.now().format(formatter);
    }
    public static boolean isSameDay(LocalDate date1, LocalDate date2) {
        return date1!= null && date1.equals(date2);
    }
}
