package lk.sample.application.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_WITH_TIME.getCustomDateTimeFormat());
        return simpleDateFormat.format(date);
    }

    public static LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, DateTimeFormat pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern.getCustomDateTimeFormat()));
    }

    public static LocalDateTime getStartDate(LocalDateTime theDate, int months) {

        return theDate.minusMonths(months-1).withDayOfMonth(1).withHour(00).withMinute(00).withSecond(00);
    }

    public static String toStringValue(LocalDateTime ldt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(formatter);

    }
    public static double getDifferenceInMonths(Date startDate, Date endDate) {
        return 0;
    }

    public static String convertDate(Date date) {// Convert Date to String
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static double monthsBetween(LocalDateTime payDate, LocalDateTime previousPayDate) {
        Period diff = Period.between(
                LocalDate.parse(previousPayDate.toLocalDate().toString()),
                LocalDate.parse(payDate.toLocalDate().toString()));
        return diff.toTotalMonths();

    }


    public static String stringDateWithOutTime(LocalDateTime date) {// Fromat Date
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}
