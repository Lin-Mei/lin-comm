package com.lin.comm.xmparser.parser;

import com.lin.comm.xmparser.demodata.model.Demo;
import com.lin.comm.xmparser.utils.JaxbUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author meilin
 * @Date 2018/11/114:53
 * @Version 1.0
 **/
public class JaxbTest {
    private int max_num=1000;
    String xml;
    @Before
    public void initData(){
        Demo demo=new Demo().init();
        xml= JaxbUtils.convertToXml(demo);
    }

    @Test
    public void print(){
        System.out.println(xml);
    }
    @Test
    public void test(){
        long time=System.currentTimeMillis();
        for(int i=0;i<max_num;i++){
            Demo demo1=JaxbUtils.converyToJavaBean(xml,Demo.class);
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max_num);
    }
    @Test public void concurrent(){
        long time=System.currentTimeMillis();
        Demo demo1=JaxbUtils.converyToJavaBean(xml,Demo.class);
    }
}
