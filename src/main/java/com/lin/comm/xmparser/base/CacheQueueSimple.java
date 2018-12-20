package com.lin.comm.xmparser.base;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author meilin
 * @Date 2018/11/110:04
 * @Version 1.0
 **/
@Component
public class CacheQueueSimple  implements CacheQueue{

    private ConcurrentLinkedQueue queue;
    public CacheQueueSimple(){
        queue=new ConcurrentLinkedQueue();
    }
    @Override
    public void put(Object e){
        if(e!=null)
        queue.add(e);
    }
    @Override
    public Object poll(){
        if(queue.isEmpty())return null;
        return queue.poll();
    }
    @Override
    public int size(){
        return queue.size();
    }
}
