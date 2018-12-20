package com.lin.comm.lombok;

import lombok.Data;

/**
 * @Author meilin
 * @Date 2018/11/2215:13
 * @Version 1.0
 **/
@Data
public class TestBean {
    private String name;
    private String age;

    public void setName(String name) {
        System.out.println("set name:"+name);
        this.name = name;
    }
}
