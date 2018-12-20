package com.lin.comm.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存简单实现，在不配置redis的情况下代替缓存使用
 * 注意事项:
 * 1.没有添加容量控制功能
 * 2.淘汰策略只有超时淘汰
 * @Author meilin
 * @Date 2018/12/2010:23
 * @Version 1.0
 **/
public class LocalCache {
    /********相关设置********/
    //默认过期时间
    private static long defaultOverTime=100;
    //是否可以获取过期但未被清理的数据
    private static boolean canGetOverValue=false;
    private static long clean_delay=1*1000;
    private static long clean_period=2*1000;
    /********相关设置********/
    private Logger logger = LoggerFactory.getLogger(LocalCache.class);
    private ConcurrentHashMap<String, Value> cache;
    private Timer timer;

    private static LocalCache _instance;

    private LocalCache(){
        logger.info("启动本地缓存，defaultOverTime：{}，canGetOverValue：{}",defaultOverTime,canGetOverValue);
        cache = new ConcurrentHashMap<>(128);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    for(Iterator<Map.Entry<String,Value>> it = cache.entrySet().iterator(); it.hasNext();){
                        Map.Entry<String,Value> entry=it.next();
                        if(isOver(entry.getValue())){
                            it.remove();
                        }
                    }
                }catch (Exception e){
                    logger.error("本地缓存清理错误",e);
                }
            }
        },clean_delay,clean_period);
    }
    private static LocalCache genInstance(){
        if(_instance==null){
            synchronized (LocalCache.class){
                if(_instance==null){
                    _instance=new LocalCache();
                }
            }
        }
        return _instance;
    }
    private static boolean isOver(Value v){
        return (v.overtime-(System.currentTimeMillis()-v.createTime))<1;
    }
    public static Object set(String key,Object value){
        return set(key, value, defaultOverTime);
    }
    public static Object set(String key,Object value,long overtime){
        Value v=new Value(System.currentTimeMillis(),overtime,value);
        LocalCache instance=genInstance();
        instance.cache.put(key,v);
        return value;
    }
    public static Object get(String key){
        LocalCache instance=genInstance();
        Value v= instance.cache.get(key);
        if(v!=null){
            if(!canGetOverValue&&isOver(v)){
                instance.cache.remove(key);
                return null;
            }
            return v.data;
        }
        return null;
    }
    public static long ttl(String key){
        LocalCache instance=genInstance();
        Value v=instance.cache.get(key);
        if(v!=null)return v.overtime-(System.currentTimeMillis()-v.createTime);
        return -1;
    }
    public static Object remove(String key){
        LocalCache instance=genInstance();
        Value v=instance.cache.remove(key);
        if(v!=null)return v.data;
        return null;
    }
    public static Map<String,Object> info(){
        Map<String, Object> info = new HashMap<>();
        if(_instance==null){
            info.put("status","not_running");
        }else{
            info.put("status","running");
            info.put("size",_instance.cache.size());
        }
        info.put("defaultOverTime",defaultOverTime);
        info.put("canGetOverValue",canGetOverValue);
        info.put("clean_delay",clean_delay);
        info.put("clean_period",clean_period);
        return info;
    }
    private static class Value{
        private long createTime;
        private long overtime;
        private Object data;
        private Value(long createTime,long overtime,Object data){
            this.createTime=createTime;
            this.overtime=overtime;
            this.data=data;
        }
    }
}