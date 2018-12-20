package com.lin.comm.design_pattern.template_pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author meilin
 * @Date 2018/11/811:55
 * @Version 1.0
 **/
public class DateParserFromSDF extends DateParser {
    @Override
    protected Date toDate(String s,String dateFormat) {
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        try{
            return sdf.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void displayDate(Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        System.out.println(sdf.format(date));
    }
}
