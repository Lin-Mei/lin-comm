package com.lin.comm.xmparser.reader.utils;

import com.lin.comm.xmparser.base.CacheQueue;
import com.lin.comm.xmparser.reader.ResourceReader;
import com.lin.comm.xmparser.reader.impl.FileResourceReader;
import com.lin.comm.xmparser.reader.mode.ReaderType;
import com.lin.comm.xmparser.utils.CacheUtils;

import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author meilin
 * @Date 2018/10/3118:00
 * @Version 1.0
 **/
public class ConcurrentReader {
    private CacheQueue cacheQueue;
    private ExecutorService executorService;
    public ConcurrentReader(CacheQueue cacheQueue,int max_thread){
        this.cacheQueue=cacheQueue;
        executorService= Executors.newFixedThreadPool(max_thread);
    }
    public void read(URL url,ReaderType readerType,int max_reader_num){
        long time=System.currentTimeMillis();
        ResourceReader resourceReader=ReaderFactory.genInstance(readerType);
        LongAdder conut=new LongAdder();
        System.out.println("开始");
        new Thread(()->{
            for(int i=0;i<max_reader_num;i++){
                executorService.execute(()->{
                    String content=resourceReader.read(url);
                    cacheQueue.put(content);
                    conut.increment();
                });
            }
            while (true){
                if(conut.longValue()==max_reader_num)break;
            }
            System.out.println("共获取"+conut.longValue()+"条数据，耗时"+(System.currentTimeMillis()-time)+"ms");
            System.out.println("cacheQueueSize:"+cacheQueue.size());
        }).start();
    }
    public void close(){
        executorService.shutdown();
    }
}
