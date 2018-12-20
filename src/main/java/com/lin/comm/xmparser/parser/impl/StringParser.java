package com.lin.comm.xmparser.parser.impl;

import com.lin.comm.xmparser.utils.JsonUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author meilin
 * @Date 2018/11/217:46
 * @Version 1.0
 **/
public class StringParser {
    private ThreadLocal<P> threadLocal=new ThreadLocal<>();
    public static void main1(String []dsd){
        int max=10000_0000;
        int n=0;
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            n++;
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("n："+n);
    }
    public static void main(String[] args) {
        int max=10000;
        ExecutorService executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
        LongAdder longAdder=new LongAdder();
        System.out.println("开始");
        String content="dahnghan";
        StringParser stringParser=new StringParser();
        long time=System.currentTimeMillis();
        for(int i=0;i<max;i++){
            executorService.execute(()->{
                String s=stringParser.parser(content);
                longAdder.increment();
                if(longAdder.longValue()<2){
                    System.out.println(s);
                }
            });
        }
        while (longAdder.longValue()<max){
        }
        System.out.println("总耗时："+(System.currentTimeMillis()-time));
        System.out.println("平均耗时："+(System.currentTimeMillis()-time)/max);
        executorService.shutdown();
    }
    public String parser(String name){
        P p=threadLocal.get();
        if(p==null){
            p=new P();
            threadLocal.set(p);
        }
        p.setName(name);
        return JsonUtils.toJson(p);
    }
    public class P{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
}
