package com.lin.comm.utils;

import com.lin.comm.utils.exception.DateParseException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 * 格式化功能:可以根据指定的格式格式化日期，不指定的情况按默认格式yyyy-MM-dd HH:mm:ss格式化
 * 日期解析功能：根据日期字符串解析成Date对象，可以指定格式，不指定的情况根据formarts中配置的格式进行自动匹配
 * 格式进行解析，匹配失败抛出异常
 * @Author meilin
 * @Date 2018/9/1911:21
 * @Version 1.0
 **/
public class DateUtils {
    public final static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String dateFormat="yyyy-MM-dd";
    public final static String iso8610Format="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String iso8610SSSZFormat="yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public final static String defultRegx="^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$";

    private static final List<MyFormat> formarts = new ArrayList<>();
    static{
        formarts.add(new MyFormat("yyyy-MM","^\\d{4}-\\d{1,2}$"));
        formarts.add(new MyFormat("yyyy-MM-dd","^\\d{4}-\\d{1,2}-\\d{1,2}$"));
        formarts.add(new MyFormat("yyyy-MM-dd hh:mm","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"));
        formarts.add(new MyFormat("yyyy-MM-dd hh:mm:ss","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"));
        formarts.add(new MyFormat("yyyy-MM-dd HH:mm:ss.SSS","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}$"));
        formarts.add(new MyFormat("yyyy-MM-dd'T'HH:mm:ss'Z'","^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}Z$"));
        formarts.add(new MyFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'","^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}Z$"));
        formarts.add(new MyFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ","^\\d{4}-\\d{1,2}-\\d{1,2}T{1}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{3}\\+\\d{4}$"));
    }

    public static Date strToDate(String value){
        if(StringUtils.isBlank(value))return null;
        if(value.matches(defultRegx)){
            return strToDate(value,defaultFormat);
        }else{
            for(MyFormat myFormat:formarts){
                if(value.matches(myFormat.regex)){
                    return strToDate(value,myFormat.fromat);
                }
            }
            throw new DateParseException("解析转换日期失败：不能识别的日期格式："+value);
        }

    }
    public static Date strToDate(String value,String format){
        if(StringUtils.isBlank(value))return null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(value);
        return dateTime.toDate();
    }
    public static String dateToString(Date date){
        return dateToString(date,defaultFormat);
    }
    public static String dateToString(Date date,String format){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
    static class MyFormat{
        public final String fromat;
        public final String regex;
        public MyFormat(String fromat,String regex){
            this.fromat=fromat;
            this.regex=regex;
        }
    }

    public static void main(String[] args) {
        Date date=new Date();
        for(MyFormat myFormat:formarts){
            String str=dateToString(date,myFormat.fromat);
            Date d1=strToDate(str);
            System.out.println(String.format("日期格式：%s,格式化结果：%s,解析结果：%s",myFormat.fromat,str,d1));
        }
    }
}
