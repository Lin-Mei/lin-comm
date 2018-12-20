package com.lin.comm.xmparser.parser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.lin.comm.xmparser.parser.XmlParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;

/**
 * @Author meilin
 * @Date 2018/11/116:28
 * @Version 1.0
 **/
public class XmlParserSAX implements XmlParser {
    private Logger logger=LoggerFactory.getLogger(XmlParserSAX.class);
    private static SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
    private static ThreadLocal<MyContentHandler> threadLocal=new ThreadLocal<>();
    @Override
    public Object parser(String content){
        InputSource inputSource = new InputSource(new StringReader(content));
        MyContentHandler myContentHandler=threadLocal.get();
        try{
            if(myContentHandler==null){
                XMLReader xmlReader=saxParserFactory.newSAXParser().getXMLReader();
                myContentHandler=new MyContentHandler();
                myContentHandler.setXmlReader(xmlReader);
                myContentHandler.getXmlReader().setContentHandler(myContentHandler);
                threadLocal.set(myContentHandler);
            }
            myContentHandler.getXmlReader().parse(inputSource);
        }catch (Exception e){
            logger.error("解析失败",e);
        }
        return myContentHandler.getModel();
    }
}
