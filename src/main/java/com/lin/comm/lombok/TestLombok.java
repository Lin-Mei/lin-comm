package com.lin.comm.lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author meilin
 * @Date 2018/11/2215:13
 * @Version 1.0
 **/
public class TestLombok {
    private static Logger logger = LoggerFactory.getLogger(TestLombok.class);
    public static void main(String[] args) {
        TestBean testBean=new TestBean();
        testBean.setName("小明");
        testBean.setAge("18岁");
        System.out.println(testBean.toString());
    }
}
