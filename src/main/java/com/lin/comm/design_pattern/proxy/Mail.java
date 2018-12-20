package com.lin.comm.design_pattern.proxy;

/**
 * @Author meilin
 * @Date 2018/11/1318:26
 * @Version 1.0
 **/
public class Mail implements Cloneable
{
    private String name;
    private int age;
    private Mail(){}
    public static  Mail genM(){
        return new Mail();
    }
    @Override
    protected Mail clone() throws CloneNotSupportedException {
        Mail mail=(Mail)super.clone();
        return mail;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

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

}
