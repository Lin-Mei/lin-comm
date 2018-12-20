package com.lin.comm.xmparser.parser;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import com.lin.comm.xmparser.parser.impl.XmlParserSAX;
import com.lin.comm.xmparser.reader.ResourceReader;
import com.lin.comm.xmparser.reader.impl.FileResourceReader;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author meilin
 * @Date 2018/11/111:27
 * @Version 1.0
 **/
public class ParserTest {
    private XmlParser xmlParser;
    private String  content;
    private int max=10000;
    private int max_threads=Runtime.getRuntime().availableProcessors()+1;
    @Before
    public void initdata()throws Exception{
//        xmlParser=new XmlParserDefault();
        xmlParser=new XmlParserSAX();
        URL url=new ClassPathResource("xmparser/demodata/demodata_back.xml").getURL();
        ResourceReader resourceReader=new FileResourceReader();
        content=resourceReader.read(url);
        //预热
        int max_copy=max;
        max=1;
        testParser();
        testConcurrent();
        max=max_copy;
        System.out.println("初始化结束**********************************************");
    }
    @Test
    public void testParser(){
        System.out.println("开始");
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            Object demo=xmlParser.parser(content);
            if(i==0){
                System.out.println("第一次解析结果："+demo);
            }
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
    }
    @Test
    public void testConcurrent(){
        System.out.println("开始");
        ExecutorService executorService=Executors.newFixedThreadPool(max_threads);
        LongAdder longAdder=new LongAdder();
        List<Object> list=new CopyOnWriteArrayList<>();
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            executorService.execute(()->{
                Object o=xmlParser.parser(content);
                list.add(o);
                longAdder.increment();
            });

        }
        while (longAdder.longValue()<max){
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
        executorService.shutdown();

    }
    @Test
    public void cpuInfo(){
        System.out.println("cpu核数："+Runtime.getRuntime().availableProcessors());
    }
}
