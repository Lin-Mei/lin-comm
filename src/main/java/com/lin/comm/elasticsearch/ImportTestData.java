package com.lin.comm.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.core.io.ClassPathResource;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @Author meilin
 * @Date 2018/11/1216:24
 * @Version 1.0
 **/
public class ImportTestData {
//    private static TransportClient client=TransportClientFactory.genTranportClient();
    private static String path="es_test_data";

    public static void main(String[] args) throws Exception{
        ClassPathResource classPathResource=new ClassPathResource(path);
        for(File file:classPathResource.getFile().listFiles()){
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String s=null;
            int i=0;
            while ((s=bufferedReader.readLine())!=null){
                sb.append(s);
                if(i>0)sb.append("\n");
                i++;
            }
            String json=sb.toString();
            DocumentApi.createDocument(Constants.indexName,Constants.type,json);
            System.out.println(json);
        }

    }
}
