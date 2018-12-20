package com.lin.comm.xmparser;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @Author meilin
 * @Date 2018/11/218:29
 * @Version 1.0
 **/
public class TestScript {
    @Test
    public void test(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try{
            engine.eval("function add(a,b){" +
                    "return a+b;" +
                    "}");
            if (engine instanceof Invocable) {
                Invocable in = (Invocable) engine;
                System.out.println(in.invokeFunction("add",1,1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
