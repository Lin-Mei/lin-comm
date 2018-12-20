package com.lin.comm.xmparser.parser.impl;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.comm.xmparser.demodata.model.Demo;
import com.lin.comm.xmparser.demodata.model.DemoC;
import com.lin.comm.xmparser.parser.XmlParser;
import com.lin.comm.xmparser.utils.DateUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


/**
 * @Author meilin
 * @Date 2018/10/3119:28
 * @Version 1.0
 **/
public class XmlParserDefault implements XmlParser {
    private Logger logger=LoggerFactory.getLogger(XmlParserDefault.class);
    private Document document;
    @Override
    public Object parser(String content) {
        try{
            if(document==null){
                synchronized (this){
                    if(document==null){
                        document=DocumentHelper.parseText(content);
                    }
                }
            }
//            Document document=DocumentHelper.parseText(content);
            Element root=document.getRootElement();
            Demo demo=new Demo();
            demo.setId(Integer.parseInt(root.elementTextTrim("id")));
            demo.setUuid(UUID.fromString(root.elementTextTrim("uuid")));
            demo.setAge(Long.parseLong(root.elementTextTrim("age")));
            demo.setName(root.elementTextTrim("name"));
            demo.setDate(DateUtils.strToDate(root.elementTextTrim("date"),"yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
            demo.setContent(root.elementTextTrim("content"));
            demo.setFanyi(root.elementTextTrim("fanyi"));
            Iterator iterator=root.elementIterator("list");
            if(iterator!=null){
                List<DemoC> demoCS = new ArrayList<>();
                demo.setList(demoCS);
                while (iterator.hasNext()){
                    Element item=(Element)iterator.next();
                    DemoC demoC=new DemoC();
                    demoC.setId(Integer.parseInt(item.elementTextTrim("id")));
                    demoC.setUuid(UUID.fromString(item.elementTextTrim("uuid")));
                    demoC.setDate(DateUtils.strToDate(item.elementTextTrim("date"),"yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
                    demoC.setText(item.elementTextTrim("text"));
                    demoCS.add(demoC);
                }
            }
            return demo;
        }catch (Exception e){
            logger.error("解析错误",e);
        }
        return null;
    }
}
