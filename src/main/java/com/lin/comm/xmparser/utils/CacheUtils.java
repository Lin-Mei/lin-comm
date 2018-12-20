package com.lin.comm.xmparser.utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author meilin
 * @Date 2018/10/3119:31
 * @Version 1.0
 **/
public class CacheUtils {
    private static int max_size=100;
    private static  ConcurrentHashMap<String,String> proMap=new ConcurrentHashMap<>();//生产队列
    private static  ConcurrentHashMap<String,String> cusMap=new ConcurrentHashMap<>();//消费队列
    public static void put(String key,String value){
        proMap.put(key,value);
        if(proMap.size()>max_size){
            synchronized (CacheUtils.class){
                ConcurrentHashMap<String,String> temp=proMap;
                proMap=cusMap;
                cusMap=temp;
            }
        }
    }

}
