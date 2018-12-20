package com.lin.comm.design_pattern.template_pattern;

import java.util.Date;

/**
 * @Author meilin
 * @Date 2018/11/812:43
 * @Version 1.0
 **/
public class DateParserSimple extends DateParser {
    @Override
    protected Date toDate(String s, String dateFormat) {
        System.out.println("开始自定义解析");
        Date date=new Date();
        String []arr1=s.split(" ");
        String []arrDate=arr1[0].split("-");
        String []arrTime=arr1[1].split(":");
        int year=Integer.parseInt(arrDate[0]);
        int moth=Integer.parseInt(arrDate[1]);
        int day=Integer.parseInt(arrDate[2]);
        int hour=Integer.parseInt(arrTime[0]);
        int minute=Integer.parseInt(arrTime[1]);
        int second=Integer.parseInt(arrTime[2]);
        date.setYear(year);
        date.setMonth(moth);
        date.setDate(day);
        date.setHours(hour);
        date.setMinutes(minute);
        date.setSeconds(second);
        return date;
    }

    @Override
    protected void displayDate(Date date) {
        System.out.println(String.format("%s年%s月%s日%s时%s分%s秒",date.getYear()
        ,date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds()));
    }
}
