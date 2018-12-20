package com.lin.comm.xmparser.demodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lin.comm.xmparser.base.CacheQueue;
import com.lin.comm.xmparser.demodata.model.Demo;
import com.lin.comm.xmparser.parser.utils.ConcurrentParser;
import com.lin.comm.xmparser.reader.ResourceReader;
import com.lin.comm.xmparser.reader.impl.FileResourceReader;
import com.lin.comm.xmparser.reader.mode.ReaderType;
import com.lin.comm.xmparser.reader.utils.ConcurrentReader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

/**
 * 解析实例
 * @Author meilin
 * @Date 2018/10/3117:31
 * @Version 1.0
 **/
@RestController
public class DemoDataController {
    private String content=null;
    @Autowired
    private CacheQueue cacheQueue;
    private ConcurrentReader concurrentReader;
    private ConcurrentParser concurrentParser;
    @Value("${parser.max_reader_threads}")
    private int max_reader_threads=Runtime.getRuntime().availableProcessors()+1;
    @Value("${parser.max_parser_threads}")
    private int max_parser_threads=Runtime.getRuntime().availableProcessors()+1;
    @PostConstruct
    public void initdata()throws IOException{
        URL url=new ClassPathResource("/xmparserdemodata/demodata_back.xml").getURL();
        ResourceReader resourceReader=new FileResourceReader();
        content=resourceReader.read(url);
        concurrentReader=new ConcurrentReader(cacheQueue,max_reader_threads);
        concurrentParser=new ConcurrentParser(cacheQueue,max_parser_threads);
        concurrentParser.start();
    }

    /**
     * 放回测试数据
     * @return
     */
    @GetMapping(value = "/xmparser/demodata", produces = { "application/xml;charset=UTF-8" })
    public Demo demodata(){
        return new Demo().init();
    }

    /**
     *指定获取数据的条数
     * @param cout
     * @return
     * @throws Exception
     */
    @GetMapping("/pro/{count}")
    public String proData(@PathVariable("count")int cout)throws Exception{
        concurrentReader.read(new ClassPathResource("xmparser/demodata/demodata_back.xml").getURL(),ReaderType.FILE,cout);
        return "OK";
    }

    /**
     * 返回已经解析的数据记录数量
     * @return
     */
    @GetMapping("/cusCount")
    public String cusCount(){
        return "共解析"+concurrentParser.parserCount()+"条数据";
    }

    /**
     * 返回等待解析的数据记录数量
     * @return
     */
    @GetMapping("/quereSize")
    public String quereSize(){
        return "共有"+cacheQueue.size()+"条数据等待解析";
    }
}
