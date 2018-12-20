package com.lin.comm.design_pattern.template_pattern;

/**
 * @Author meilin
 * @Date 2018/11/812:55
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) {
        String str="2018-09-11 12:25:45";
        DateParser dateParser=new DateParserFromSDF();
        DateParser dateParser1=new DateParserSimple();
        dateParser.parser(str);
        dateParser1.parser(str);
    }
}
