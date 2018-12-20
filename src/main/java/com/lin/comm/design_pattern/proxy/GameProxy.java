package com.lin.comm.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author meilin
 * @Date 2018/11/1317:57
 * @Version 1.0
 **/
public class GameProxy implements InvocationHandler {
    private Object object;
    public GameProxy(Object o){
        this.object=o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理："+method.getName());
        Object res=method.invoke(object,args);
        return res;
    }
}
