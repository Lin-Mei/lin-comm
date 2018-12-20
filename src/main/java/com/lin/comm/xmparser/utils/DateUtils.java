package com.lin.comm.xmparser.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;
import java.util.Date;
public class DateUtils {
    public static Date strToDate(String value,String format){
        if(StringUtils.isEmpty(value))return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(value);
        return dateTime.toDate();
    }
    public static String dateToString(Date date,String format){
        if(date == null){
            return null;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
