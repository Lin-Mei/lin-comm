package com.lin.comm.xmparser.outer;

import com.lin.comm.xmparser.demodata.model.Demo;

import java.util.List;

/**
 * @Author meilin
 * @Date 2018/11/214:34
 * @Version 1.0
 **/
public interface Outer {
    void out(Demo demo);
    void out(List<Demo> demos);
}
