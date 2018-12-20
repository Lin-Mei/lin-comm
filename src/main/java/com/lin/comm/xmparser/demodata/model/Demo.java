package com.lin.comm.xmparser.demodata.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author meilin
 * @Date 2018/11/111:48
 * @Version 1.0
 **/
@XmlRootElement
public class Demo implements Cloneable{
    private int id;
    private UUID uuid;
    private String name;
    private long age;
    private Date date;
    private String content;
    private String fanyi;
    private List<DemoC> list;

    public Demo init(){
        this.id=1321;
        this.uuid=UUID.randomUUID();
        this.age=1231L;
        this.name="小明";
        this.date=new Date();
        this.content="豫章故郡，洪都新府";
        this.fanyi="这里是汉代的豫章郡城，如今是洪州的都督府";
        list=new ArrayList<DemoC>(){{
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
            add(new DemoC().initData());
        }};
        return this;
    }
    public void  clean(){
        this.id=0;
        this.uuid=null;
        this.name=null;
        this.age=0;
        this.date=null;
        this.content=null;
        this.fanyi=null;
        this.list=null;
    }

    @Override
    public Demo clone() {
        try {
            Demo demo=(Demo)super.clone();
            return demo;
        }catch (CloneNotSupportedException cle){
            cle.printStackTrace();
        }
        return null;
    }

    public  final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final UUID getUuid() {
        return uuid;
    }

    public final void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Long getAge() {
        return age;
    }

    public final void setAge(Long age) {
        this.age = age;
    }

    public final Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        this.date = date;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    public final String getFanyi() {
        return fanyi;
    }

    public final void setFanyi(String fanyi) {
        this.fanyi = fanyi;
    }

    public final List<DemoC> getList() {
        return list;
    }

    public final void setList(List<DemoC> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
