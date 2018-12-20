package com.lin.comm.elasticsearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * @Author meilin
 * @Date 2018/11/914:10
 * @Version 1.0
 **/
public class IndexApi {
    private static TransportClient client=TransportClientFactory.genTranportClient();


    public static void main(String[] args) throws IOException {
        String []indexNames=new String[]{Constants.indexName};
        if(exist(indexNames)){
            deleteIndex(indexNames);
        }
        createIndex(Constants.indexName);
//        IndexResponse indexResponse=client.prepareIndex("indexName","test_doc","1")
//                .setSource(
//                        jsonBuilder()
//                        .startObject()
//                        .field("user","kimchy")
//                        .field("postDate",new Date())
//                        .field("message","trying out elasticsearch")
//                        .endObject()
//                ).get();
//        System.out.println(indexResponse.toString());
    }
    public static void createIndex(String indexName){
        CreateIndexRequest createIndexRequest=new CreateIndexRequest();
        createIndexRequest.index(indexName);
        CreateIndexResponse createIndexResponse = client.admin().indices().create(createIndexRequest).actionGet();
        System.out.println("create index ["+indexName+"]"+createIndexResponse.isAcknowledged());
    }
    public static boolean exist(String indexNames[]){
        IndicesExistsRequest indicesExistsRequest=new IndicesExistsRequest();
        indicesExistsRequest.indices(indexNames);
        IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(indicesExistsRequest).actionGet();
        System.out.println("exist index "+Arrays.toString(indexNames)+indicesExistsResponse.isExists());
        return indicesExistsResponse.isExists();
    }
    public static void deleteIndex(String[] indexNames){
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(indexNames);
        DeleteIndexResponse deleteIndexRespon=client.admin().indices().delete(deleteIndexRequest).actionGet();
        System.out.println("delete index "+Arrays.toString(indexNames)+deleteIndexRespon.isAcknowledged());
    }
}
