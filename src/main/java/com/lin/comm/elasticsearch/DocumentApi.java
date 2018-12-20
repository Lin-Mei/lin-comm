package com.lin.comm.elasticsearch;

import com.lin.comm.utils.JsonUtil;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.InternalFilter;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.util.Map;

/**
 * @Author meilin
 * @Date 2018/11/915:57
 * @Version 1.0
 **/
public class DocumentApi {
    private static TransportClient client=TransportClientFactory.genTranportClient();

    public static void main(String[] args) {
//        String json = "{" +
//                "\"heroName\":\"王昭君\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//        createDocument(Constants.indexName,Constants.type,json);
//        query(new String[]{Constants.indexName},new String[]{Constants.type});
        aggs(new String[]{Constants.indexName},new String[]{Constants.type});
    }
    public static void createDocument(String indexName,String type,String json){
        IndexResponse indexResponse=client.prepareIndex(indexName,type)
                .setSource(json,XContentType.JSON).get();
        System.out.println(indexResponse.toString());
        System.out.println("response status code :"+indexResponse.status().getStatus());
    }
    public static void aggs(String []indexNames,String types[]){
        SearchResponse searchResponse=client.prepareSearch(indexNames)
                .setTypes(types)
//                .addAggregation(AggregationBuilders.filter("all_author",QueryBuilders.termQuery("author.keyword","杜甫")))
                .addAggregation(AggregationBuilders.terms("agg-author").field("author.keyword"))
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        SearchHits hits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        InternalFilter res=searchResponse.getAggregations().get("all_author");
        System.out.println(res.getDocCount());

    }
    public static void query(String []indexNames,String types[]){
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<high>");//高亮标签前缀,默认是em
        highlightBuilder.postTags("</high>");//高亮标签后缀
        highlightBuilder.field("*");//高亮字段，支持正则
        SearchResponse searchResponse=client.prepareSearch(indexNames)
                .setTypes(types)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("fieldName","value"))//根据值过滤
                .setQuery(QueryBuilders.termsQuery("fieldName","value1","value2"))//多字段过滤
                .setQuery(QueryBuilders.existsQuery("fieldName"))//存在字段fieldName的文档过滤


//                .setQuery(QueryBuilders.matchAllQuery())//返回匹配所有文档
//                .setQuery(QueryBuilders.termQuery("author.keyword","王之涣"))//精确匹配作者为王之涣的文档
//                .setQuery(QueryBuilders.matchQuery("author","孟"))//模糊匹配作者名字中含有孟字的文档
                //多字段模糊匹配
                .setQuery(QueryBuilders.multiMatchQuery("故人","titile","content","author","source","yizhu","shangxi"))
                .setQuery(QueryBuilders.matchPhraseQuery("content","伐月支"))
                //字段名称正则
//                .setQuery(QueryBuilders.multiMatchQuery("故人","*tile"))
                //数值范围匹配
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))//添加过滤器，年龄在12到18之间
//                .setPostFilter(QueryBuilders.rangeQuery("age").gt(30))//添加过滤器，年龄在30之间
                .setPostFilter(QueryBuilders.termQuery("author.keyword","张籍"))//添加过滤器
                .highlighter(highlightBuilder)//高亮设置
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits2 = hits.getHits();
        System.out.println("total hists :"+hits.totalHits+",max score："+hits.getMaxScore());
        for (SearchHit sh : hits2) {
            Map<String, Object> source = sh.getSource();
            String id = sh.getId();
            float score = sh.getScore();
            System.out.println("source:" + source + ",id:" + id + ",score:"
                    + score+",highlightFields:"+sh.getHighlightFields());
        }
    }
}
