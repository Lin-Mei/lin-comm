package com.lin.comm.xmparser.outer.impl;

import com.lin.comm.xmparser.demodata.model.Demo;
import com.lin.comm.xmparser.outer.Outer;
import com.lin.comm.xmparser.utils.JsonUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author meilin
 * @Date 2018/11/214:35
 * @Version 1.0
 **/
public class OuterToFile implements Outer {
    @Override
    public void out(Demo demo) {
        String josn= JsonUtils.toJson(demo);
        File file=new File("D:/data.data");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(josn.getBytes("utf-8"));
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void out(List<Demo> demos) {

        File file=new File("D:/data.data");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            for(Demo demo:demos){
                String josn= JsonUtils.toJson(demo);
                fileOutputStream.write(josn.getBytes("utf-8"));
            }
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OuterToFile outerToFile=new OuterToFile();
        List<Demo> list=new ArrayList<>();
        long time=System.currentTimeMillis();
        for(int i=0;i<10000_0;i++){
            list.add(new Demo().init());
        }
        System.out.println("创建完成："+(System.currentTimeMillis()-time));
        outerToFile.out(list);
        System.out.println("写完："+(System.currentTimeMillis()-time));
    }
}
