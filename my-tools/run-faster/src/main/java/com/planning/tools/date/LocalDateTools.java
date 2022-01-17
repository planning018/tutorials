package com.planning.tools.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
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


    public static String getNow(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test(){
        System.out.println(getNow());
    }
}
