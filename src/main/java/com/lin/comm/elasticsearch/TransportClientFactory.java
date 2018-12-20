package com.lin.comm.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static java.lang.System.out;

/**
 * @Author meilin
 * @Date 2018/11/911:05
 * @Version 1.0
 **/
public class TransportClientFactory {
    public static TransportClient genTranportClient(){
        try{
            Settings settings=Settings.builder().put("cluster.name","elasticsearch")
                .build();
            TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY);
            transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
            return transportClient;
        }catch (UnknownHostException e){
            out.println("无效的地址");
        }
        return null;
    }

}
