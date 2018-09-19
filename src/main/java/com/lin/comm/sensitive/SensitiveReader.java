package com.lin.comm.sensitive;

import com.lin.comm.utils.JsonUtil;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author meilin
 * @Date 2018/9/1819:15
 * @Version 1.0
 **/
public abstract class SensitiveReader {
    public static void main(String[] args){
        String path="classpath:sensitive";
        Map map=read(path);
        System.out.println(JsonUtil.toJsonString(map));

    }
    public static Map<String,List<String>> read(String path){
        File file=null;
        try{
            file=ResourceUtils.getFile(path);
        }catch (IOException io){
            io.printStackTrace();
        }
        Map<String,List<String>> map=new HashMap<>();
        if(file!=null&&file.isDirectory()){
            for(File item:file.listFiles()){
                List<String> list=readFile(item);
                String groupName=item.getName();
                map.put(groupName, list);
            }
        }
        return map;
    }
    private static List<String> readFile(File file){
        if(file!=null&&file.isFile()){
            List<String> list=new ArrayList<>();
            InputStreamReader inputStreamReader=null;
            BufferedReader bufferedReader=null;
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    list.add(line);
                }
            }catch (IOException io){
                io.printStackTrace();
            }finally {
                try{
                    bufferedReader.close();
                }catch (IOException i1){
                    i1.printStackTrace();
                }finally {
                    try{
                        inputStreamReader.close();
                    }catch (IOException i2){
                        i2.printStackTrace();
                    }
                }
            }
            return list;
        }
        return null;
    }
}
