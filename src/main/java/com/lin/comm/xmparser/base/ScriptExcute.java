package com.lin.comm.xmparser.base;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @Author meilin
 * @Date 2018/11/218:30
 * @Version 1.0
 **/
public class ScriptExcute {
    private static ScriptEngine jsScriptEngine;
    static {
        jsScriptEngine=new ScriptEngineManager().getEngineByName("javascript");
    }
    public static Object exute(String scp,Object... args)throws Exception{
        Object o=jsScriptEngine.eval(scp);
        if (jsScriptEngine instanceof Invocable) {
            Invocable in = (Invocable) jsScriptEngine;
            return in.invokeFunction("add",args);
        }
        return null;
    }

    public static void main(String[] args) throws  Exception{
        Object o=ScriptExcute.exute("function add(a,b){return a+b;}",1,5);
        System.out.println(o);
        Object o1=ScriptExcute.exute("function add(){return 2332;}");
        System.out.println(o1);
    }
}
