package com.test.demafayz.testapplication.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by demafayz on 26.08.16.
 */
public class DateUtil {

    public static final String BASE_DATE_FORMAT = "dd.MM.yyyy";
    public static final String UI_DATE_FORMAT = "dd.MMM.yyyy";

    public static long stringDateToLong(String date, String format) {
        long milliseconds = 0;
        try {
            String tmpFormat = null;
            if (format != null) {
                tmpFormat = format;
            } else {
                tmpFormat = BASE_DATE_FORMAT;
            }
            DateFormat formatter = new SimpleDateFormat(BASE_DATE_FORMAT);
            Date d = formatter.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return milliseconds;
        }
    }

    public static String longDateToString(long time, String format) {
        String tmpFormat = null;
        if (format != null) {
            tmpFormat = format;
        } else {
            tmpFormat = BASE_DATE_FORMAT;
        }
        String dateString = new SimpleDateFormat(tmpFormat).format(new Date(time));
        return dateString;
    }
}
