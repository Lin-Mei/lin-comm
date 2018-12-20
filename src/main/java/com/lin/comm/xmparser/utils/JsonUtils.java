package com.lin.comm.xmparser.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Author meilin
 * @Date 2018/11/214:39
 * @Version 1.0
 **/
public class JsonUtils {
    public static String toJson(Object o){
        return JSON.toJSONString(o);
    }
}
