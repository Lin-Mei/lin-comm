package com.lin.comm.xmparser.parser.impl;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import com.lin.comm.xmparser.demodata.model.Demo;
import com.lin.comm.xmparser.demodata.model.DemoC;
import com.lin.comm.xmparser.utils.DateUtils;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @Author meilin
 * @Date 2018/11/116:35
 * @Version 1.0
 **/
public class MyContentHandler extends DefaultHandler {
    private StringBuffer valueSb=new StringBuffer(100);
    private StringBuffer path=new StringBuffer(50);
    private static String dateFormat="yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
    private XMLReader xmlReader;
    private Demo demo;
    private DemoC demoC;
    public Demo getModel(){
        return demo;
    }

    public XMLReader getXmlReader() {
        return xmlReader;
    }

    public void setXmlReader(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }


    @Override
    public void startElement(String namespaceURI, String localName,String qName, Attributes attr) throws SAXException {
        switch (qName){
            case "list":{
                demoC=new DemoC();
                if(demo.getList()==null)demo.setList(new ArrayList<>());
                demo.getList().add(demoC);
                break;
            }
            case "demo":{
                path.setLength(0);
                demo=new Demo();
                break;
            }
        }
        path.append('>');
        path.append(qName);
    }

    @Override
    public void endElement(String namespaceURI, String localName,String qName) throws SAXException {
        String value=valueSb.toString();
        switch (path.toString()) {
            case ">demo>id":demo.setId(Integer.parseInt(value));break;
            case ">demo>uuid":demo.setUuid(UUID.fromString(value));break;
            case ">demo>age":demo.setAge(Long.parseLong(value));break;
            case ">demo>name":demo.setName(value);break;
            case ">demo>date":demo.setDate(DateUtils.strToDate(value,"yyyy-MM-dd HH:mm:ss"));break;
            case ">demo>content":demo.setContent(value);break;
            case ">demo>fanyi":demo.setFanyi(value);break;
            case ">demo>list>id":demoC.setId(Integer.parseInt(value));break;
            case ">demo>list>uuid":demoC.setUuid(UUID.fromString(value));break;
            case ">demo>list>date":demoC.setDate(DateUtils.strToDate(value,"yyyy-MM-dd HH:mm:ss"));break;
            case ">demo>list>text":demoC.setText(value);break;
        }
        path.delete(path.length()-qName.length()-1,path.length());
        valueSb.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valueSb.append(ch,start,length);
    }
}
