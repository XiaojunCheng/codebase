package com.codebase.framework.jodatime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * created by cheng.xiaojun.seu@gmail.com on 17/3/3.
 */
public class Main {

    public static void main(String[] args) {
        Date jdkDate = new Date();
        DateTime dateTime = new DateTime(jdkDate);
        System.out.println("year: " + dateTime.getYear());
        System.out.println("monthOfYear: " + dateTime.getMonthOfYear());
        System.out.println("dayOfYear: " + dateTime.getDayOfYear());
        System.out.println("dayOfMonth: " + dateTime.getDayOfMonth());
        System.out.println("dayOfWeek: " + dateTime.getDayOfWeek());
        System.out.println("hourOfDay: " + dateTime.getHourOfDay());
        System.out.println("minuteOfDay: " + dateTime.getMinuteOfDay());
        System.out.println("minuteOfHour: " + dateTime.getMinuteOfHour());
        System.out.println("secondOfDay: " + dateTime.getSecondOfDay());
        System.out.println("secondOfMinute: " + dateTime.getSecondOfMinute());

        DateTime dateTime2 = new DateTime(System.currentTimeMillis());
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");
        System.out.println(dateTime2.toString(dateTimeFormatter));
    }

}
