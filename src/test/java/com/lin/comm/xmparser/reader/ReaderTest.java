package com.lin.comm.xmparser.reader;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.lin.comm.xmparser.base.CacheQueue;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import com.lin.comm.xmparser.base.CacheQueue;
import com.lin.comm.xmparser.base.CacheQueueSimple;
import com.lin.comm.xmparser.reader.impl.FileResourceReader;
import com.lin.comm.xmparser.reader.impl.RestResourceReader;
import com.lin.comm.xmparser.reader.mode.ReaderType;
import com.lin.comm.xmparser.reader.utils.ConcurrentReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * @Author meilin
 * @Date 2018/10/3117:53
 * @Version 1.0
 **/
public class ReaderTest {
    private static int max=10000_0;
    private static int max_thread=Runtime.getRuntime().availableProcessors()+1;
    private CacheQueue cacheQueue;
    @Before
    public void init(){
        LoggerContext loggerContext=(LoggerContext)LoggerFactory.getILoggerFactory();
        for(Logger logger:loggerContext.getLoggerList()){
            ((ch.qos.logback.classic.Logger)logger).setLevel(Level.INFO);
        }
        cacheQueue=new CacheQueueSimple();
    }
    @Test
    public  void testFileReader()throws IOException {
        URL url=new ClassPathResource("xmparser/demodata/demodata_back.xml").getURL();
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            ResourceReader resourceReader=new FileResourceReader();
            String content=resourceReader.read(url);
            if(i==0){
                System.out.println("第一次读取结果：\n"+content);
            }
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
    }
    @Test
    public  void testRestReader()throws IOException {
        URL url=new URL("http://localhost:8080/demodata");
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            ResourceReader resourceReader=new RestResourceReader();
            String content=resourceReader.read(url);
            if(i==0){
                System.out.println("第一次读取结果：\n"+content);
            }
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
    }
    @Test
    public  void testRestXmlReader()throws Exception {
        URL url=new URL("http://localhost:8080/demodata");
        ResourceReader resourceReader=new RestResourceReader();
        String content=resourceReader.read(url);
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            Document document=DocumentHelper.parseText(content);
            if(i==0){
                System.out.println("第一次读取结果：\n"+document.getRootElement().getName());
            }
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
    }
    @Test
    public void fileConcurrent()throws IOException{
        ConcurrentReader concurrentReader=new ConcurrentReader(cacheQueue,max_thread);
        concurrentReader.read(new ClassPathResource("xmparser/demodata/demodata_back.xml").getURL(),ReaderType.FILE,max);
        while (cacheQueue.size()<max-1){}
        System.out.println("end,size:"+cacheQueue.size());
    }
    @Test
    public void restConcurrent()throws MalformedURLException {
        ConcurrentReader concurrentReader=new ConcurrentReader(cacheQueue,max_thread);
        concurrentReader.read(new URL("http://localhost:8080/demodata"),ReaderType.REST,max);
        while (cacheQueue.size()<max-1){}
        System.out.println("end,size:"+cacheQueue.size());
    }
}
