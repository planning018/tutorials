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

    /**
     * 获取指定时间
     *      1. 获取当前时间（毫秒）
     *      2. 当天的 0点 - 24点（秒）
     *      3. 当天的 0点 - 24点（毫秒）
     */
    public static void fetchTargetTime(){
        // 获取当前时间（毫秒）
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        // 当天的 0点 - 24点（秒）
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8")));

        // 当天的 0点 - 24点（毫秒）
        System.out.println(new Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        System.out.println(new Date(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toEpochSecond(ZoneOffset.of("+8")) * 1000));

        // 当前时间的小时 开始点-结束点
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));
        System.out.println(LocalDateTime.now().plusHours(1).truncatedTo(ChronoUnit.HOURS));
    }

    public static void main(String[] args) {
        fetchTargetTime();
    }

    /**
     * 时间格式化
     */
    public void formatTime(){
        // 当前时间格式化
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    /**
     * 往前/往后 操作时间
     */
    public void plusOrMinusDateTime(){
        // 当前时间往前推 5 分钟
        System.out.println(LocalDateTime.now().minusMinutes(5).toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    /**
     *  Date 和 LocalDate、LocalDateTime 之间的相互转换
     */
    public void convertDateAndLocalDate(){
        Date date = new Date();
        // convert date to LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // convert date ToLocalDate via millisecond
        Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        // convert date To LocalDate Via SqlDate
        new java.sql.Date(date.getTime()).toLocalDate();

        // convert Date to localDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // convert Date To LocalDateTime via millisecond
        Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        // convert Date To LocalDateTime via sqlDate
        new java.sql.Timestamp(date.getTime()).toLocalDateTime();

        // convert LocalDate To Date
        java.util.Date date1 = java.sql.Date.valueOf(localDate);
        // convert LocalDate To Date Via Instant
        java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        // convert LocalDateTime To Date Via Sql Timestamp
        java.sql.Timestamp.valueOf(localDateTime);
        java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate 的常见用法
     */
    public void showLocalDateUsage() {
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

    /**
     * LocalTime 的常见用法
     */
    public void showLocalTimeUsage() {
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

    /**
     * LocalDateTime 的常见用法
     */
    public void showLocalDateTimeUsage(){
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

}
