package com.planning.tools.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JDK定时任务
 * @Author: planning
 * @Date: 2017/3/5
 */
public class TimeTasker {
    public static void main(String[] args) {
        //Test
        time4();

    }

    //第一种方法：设置指定的任务task在指定的时间time执行
    public static void time1(){
        Timer time = new Timer();
        time.schedule(new TimerTask(){

            @Override
            public void run() {
                System.out.println("============设定要执行的任务==============");
            }

        },2000);  //设定指定的时间time，此处为2000毫秒
    }

    //第二种方法：设置指定的任务task在指定延迟delay后进行固定延迟period的执行
    public static void time2(){
        Timer time = new Timer();
        time.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("============设定要执行的任务==============");

            }
        }, 2000, 2000);
    }

    //第三种方法：设置指定的任务task在指定延迟delay后进行固定频率period的执行
    public static void time3(){
        Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                System.out.println("============设定要执行的任务==============");
            }
        }, 1000, 2000);
    }

    //第四种方法：设置指定的任务task在指定的时间firstTime后开始进行固定速率period的执行
    public static void time4(){
        Timer time = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23); //控制时
        calendar.set(Calendar.MINUTE, 55);  //控制分
        calendar.set(Calendar.SECOND, 0);  //控制秒

        Date date = calendar.getTime(); //得到执行指定的任务的时间，此处为晚上23:55:00

        time.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                System.out.println("============设定要执行的任务==============");

            }
        }, date, 2000);
    }

}