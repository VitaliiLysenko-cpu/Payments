package com.lysenko.payments;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static java.sql.Date getTodayInTwoYears() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 2);
        return new java.sql.Date(calendar.getTime().getTime());
    }
}
