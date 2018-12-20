package com.lin.comm.xmparser.reader.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.comm.xmparser.reader.ResourceReader;
import java.io.*;
import java.net.URL;

/**
 * @Author meilin
 * @Date 2018/10/3117:03
 * @Version 1.0
 **/
public class FileResourceReader implements ResourceReader {
    private Logger logger=LoggerFactory.getLogger(FileResourceReader.class);
    @Override
    public String read(URL url) {
        StringBuffer stringBuffer=new StringBuffer();
        try{
            InputStream inputStream=new FileInputStream(url.getPath());
            String line=null;
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
        }catch (IOException e){
            logger.error("读取文件错误",e);
        }
        return stringBuffer.toString();
    }
}
