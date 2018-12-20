package com.lin.comm.xmparser.demodata.model;

import java.util.Date;
import java.util.UUID;

/**
 * @Author meilin
 * @Date 2018/11/111:52
 * @Version 1.0
 **/
public class DemoC implements Cloneable{
    public DemoC initData(){
        this.id=1223;
        this.uuid=UUID.randomUUID();
        this.date=new Date();
        this.text="庆历四年春，滕子京谪守巴陵郡";
        return this;
    }
    private int id;
    private UUID uuid;
    private Date date;
    private String text;

    @Override
    public DemoC clone() {
        try {
            DemoC demo=(DemoC)super.clone();
            return demo;
        }catch (CloneNotSupportedException cle){
            cle.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
