package com.shortthirdman.sharedlibs.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtils {

    /**
     * Parse the date string value with date format
     *
     * @param dateValue
     * @param dateFormat
     * @return
     */
    public static LocalDate parseDate(String dateValue, String dateFormat) {
        if (dateFormat == null || dateFormat.isEmpty()) {
            return LocalDate.parse(dateValue, DateTimeFormatter.BASIC_ISO_DATE);
        } else {
            return LocalDate.parse(dateValue, DateTimeFormatter.ofPattern(dateFormat, Locale.ENGLISH));
        }
    }

    /**
     * Checks if the year is leap year or not
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if ((year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Converts LocalDate to Date
     * @param localDate the LocalDate to convert
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts LocalDateTime as Date
     * @param localDateTime the LocalDateTime to convert
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts Date to LocalDate
     * @param date the Date to convert
     * @return
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Converts Date to LocalDateTime
     * @param date the Date to convert
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * @param dateValue
     * @return
     */
    public static ZonedDateTime asZonedDateTime(String dateValue, String pattern) {
        String defaultPattern = (pattern == null) ? "yyyy-MM-dd HH:mm:ss z": pattern;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultPattern);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateValue, formatter);
        return zonedDateTime;
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
