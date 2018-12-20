package com.lin.comm.design_pattern.template_pattern;

import java.util.Date;

/**
 * 日期解析类
 * @Author meilin
 * @Date 2018/11/811:28
 * @Version 1.0
 **/
public abstract class DateParser {
    protected static String dateFormat="yyyy-MM-dd hh:mm:ss";
    protected static String regx="^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$";
    protected abstract Date toDate(String s,String dateFormat);
    protected abstract void displayDate(Date date);
    private boolean validate(String s){
        if(s==null||s.equals("")){
            System.out.println("目标字符串为空");
            return false;
        }else if(s.matches(regx)){
            System.out.println(String.format("输入字符串%s为合法的日期格式",s));
            return true;
        }else{
            System.out.println(String.format("非法的日期格式%s,请输入%s格式的日期",s,dateFormat));
            return false;
        }

    }
    public  final Date parser(String str){
        boolean b=validate(str);
        if(b){
            Date date=toDate(str,dateFormat);
            displayDate(date);
            return date;
        }else{
            return null;
        }
    }
}
