package com.lin.comm.xmparser.parser.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.comm.xmparser.base.CacheQueue;
import com.lin.comm.xmparser.parser.XmlParser;
import com.lin.comm.xmparser.parser.impl.XmlParserDefault;
import com.lin.comm.xmparser.parser.impl.XmlParserSAX;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author meilin
 * @Date 2018/10/3119:30
 * @Version 1.0
 **/
public class ConcurrentParser {
    private Logger logger = LoggerFactory.getLogger(ConcurrentParser.class);
    private CacheQueue cacheQueue;
    private int max_parser_thread=10;
    private ExecutorService executorService;
    private XmlParser xmlParser;
    private LongAdder count=new LongAdder();
    public ConcurrentParser(CacheQueue cacheQueue,int max_parser_thread){
        this.cacheQueue=cacheQueue;
        this.max_parser_thread=max_parser_thread;
        executorService=Executors.newFixedThreadPool(max_parser_thread);
//        xmlParser=new XmlParserDefault();
        xmlParser=new XmlParserSAX();
    }
    public long parserCount(){
        return count.longValue();
    }
    public void start(){
        new Thread(()->{
            logger.info("开始解析，解析线程数{}",max_parser_thread);
            while (true){
                String content=(String)cacheQueue.poll();
                if(content!=null){
                    executorService.execute(()->{
                        xmlParser.parser(content);
                        count.increment();
//                        logger.info("解析成功");
                    });
                }else{
                    logger.info("空闲:"+cacheQueue.size());
                    try{
                        Thread.sleep(1000L);
                    }catch (InterruptedException e){
                        logger.error("线程睡眠异常",e);
                    }
                }
            }
        }).start();

    }
    public void close(){
        executorService.shutdown();
    }
}
