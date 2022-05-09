package com.planning.tools.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author yxc
 * @date 2021/1/18 11:03
 */
public class LocalDateTools {

    public static void main(String[] args) {
        // 获取当前时间（毫秒）
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // 当天的 0点 - 24点（秒）
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8")));

        // 当天的 0点 - 24点（毫秒）
        System.out.println(new Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        System.out.println(new Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8")) * 1000));

        // 格式化时间
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 当前时间往前推 5 分钟
        System.out.println(LocalDateTime.now().minusMinutes(5).toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }


    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void showLocalDate() {
        // An instance of current date
        LocalDate localDate = LocalDate.now();

        // representing a specific day
        LocalDate.of(2015, 02, 20);
        LocalDate.parse("2015-02-20");

        // adds one day
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        // substracts one month
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        // representing the ordinal value of the week/month
        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();

        // test if a date occurs in a leap year
        boolean leapYear = LocalDate.now().isLeapYear();

        // before or after another dat
        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));

        // represents the beginning of the day (2016-06-12T00:00)
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();

        // represents the beginning of the month (2016-06-01)
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
    }

    public void showLocalTime() {
        // An instance of current LocalTime
        LocalTime now = LocalTime.now();

        // representing 06:30 AM by parsing a string representation
        // LocalTime sixThirty = LocalTime.parse("06:30");
        LocalTime sixThirty = LocalTime.of(6, 30);

        // adds an hour
        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);

        // get specific units of time like hour
        int six = LocalTime.parse("06:30").getHour();

        // compares two LocalTime
        boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));

        // represents 23:59:59.99
        LocalTime maxTime = LocalTime.MAX;
    }

    public void showLocalDateTime(){
        // An instance of LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();

        // representing a special day
        LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        LocalDateTime.parse("2015-02-20T06:30:00");

        // addition and subtraction of specific units
        localDateTime.plusDays(1);
        localDateTime.minusHours(2);

        // return the month
        localDateTime.getMonth();

        // 当天的 0点 - 24点
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8")) * 1000);
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8")) * 1000);
        System.out.println(new Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli()));

        // 格式化日期
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDateViaSqlDate1(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public LocalDateTime convertToLocalDateViaSqlDate2(Date dateToConvert) {
        return new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
