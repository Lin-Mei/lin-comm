package com.lin.comm.xmparser.reader.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.lin.comm.xmparser.reader.ResourceReader;

import java.net.URL;

/**
 * @Author meilin
 * @Date 2018/10/3117:40
 * @Version 1.0
 **/
public class RestResourceReader implements ResourceReader {
    private Logger logger=LoggerFactory.getLogger(RestResourceReader.class);
    @Override
    public String read(URL url) {
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url.toString(),String.class);
        if(responseEntity!=null&&responseEntity.getStatusCodeValue()==200){
            return responseEntity.getBody();
        }else if(responseEntity!=null){
            logger.error("请求失败code:{},reason{}",responseEntity.getStatusCodeValue(),responseEntity.getStatusCode().getReasonPhrase());
        }else{
            logger.error("请求失败,没有收到任何响应");
        }
        return null;
    }
}
