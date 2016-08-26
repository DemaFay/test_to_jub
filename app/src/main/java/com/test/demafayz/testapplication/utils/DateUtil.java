package com.test.demafayz.testapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by demafayz on 26.08.16.
 */
public class DateUtil {

    public static final String BASE_DATE_FORMAT = "dd.MM.yyyy";

    public static long stringDateToLong(String date, String format) {
        long milliseconds = 0;
        try {
            String tmpFormat = null;
            if (format != null) {
                tmpFormat = format;
            } else {
                tmpFormat = BASE_DATE_FORMAT;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(tmpFormat);
            Date d = dateFormat.parse(tmpFormat);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return milliseconds;
        }
    }
}
