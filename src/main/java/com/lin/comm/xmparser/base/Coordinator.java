package com.lin.comm.xmparser.base;

/**
 * @Author meilin
 * @Date 2018/11/110:14
 * @Version 1.0
 **/
public class Coordinator {
    public static void main(String[] args) {
        CacheQueue cacheQueue=new CacheQueueSimple();
        cacheQueue.put(1);
        cacheQueue.put(1);
        cacheQueue.put(null);
        cacheQueue.put(2);
        System.out.println(cacheQueue.size());
        System.out.println(cacheQueue.poll());
        System.out.println(cacheQueue.poll());
        System.out.println(cacheQueue.poll());
        System.out.println(cacheQueue.poll());
        System.out.println(cacheQueue.size());
    }
}
