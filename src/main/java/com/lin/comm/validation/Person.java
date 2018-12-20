package com.lin.comm.validation;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

/**
 * @Author meilin
 * @Date 2018/12/315:23
 * @Version 1.0
 **/
public class Person {
    @Length(min=2,max=20,message = "字符串长度必须在2到20之间")// 被注释的字符串的大小必须在指定的范围内
    @NotEmpty(message = "姓名不能为空")   //被注释的字符串的必须非空
    private String name;
    @Range(min=0,max=100,message="年龄必须在0到100之间")//  被注释的元素必须在合适的范围内
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
