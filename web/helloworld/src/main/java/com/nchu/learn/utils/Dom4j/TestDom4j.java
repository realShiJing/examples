package com.nchu.learn.utils.Dom4j;

import org.dom4j.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestDom4j {

    @Test
    public void bulidXmlByDom4j(){
        //创建document对象
        Document document = DocumentHelper.createDocument();
        //设置xml文档编码
        document.setXMLEncoding("UTF-8");
        //创建root根节点
        Element root = DocumentHelper.createElement("root");
        root.addAttribute("version","1.0");
        //创建head节点
        Element header = DocumentHelper.createElement("header");
        //设置节点的属性
        header.addAttribute("version","1.0");
        //设置子节点 (子节点无属性时，可直接用addElement创建)
        Element element_header1 = header.addElement("timestamp");
        //设置子节点的内容
        element_header1.setText("20190122");
        Element element_header2 = header.addElement("username");
        element_header2.setText("yangsj");
        Element element_header3 = header.addElement("password");
        element_header3.setText("root");

        //创建body节点
        Element body = DocumentHelper.createElement("body");
        body.addAttribute("version","1.0");
        //创建action节点
        Element action = DocumentHelper.createElement("action");
        //创建action的子节点
        Element action_option = DocumentHelper.createElement("option");
        action_option.addAttribute("name","url");
        action_option.addAttribute("value","http://127.0.0.1");
        action.add(action_option);

        //创建data 节点
        Element data = DocumentHelper.createElement("data");
        Element dataField = DocumentHelper.createElement("field");
        dataField.addAttribute("name","money");
        dataField.addAttribute("value","10000");
        data.add(dataField);

        body.add(action);
        body.add(data);

        root.add(header);
        root.add(body);
        document.add(root);
        String xml = document.asXML();

        System.out.println(xml);
    }

    @Test
    public void readXmlInfo() throws DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root version=\"1.0\"><body " +
                "version=\"1.0\"><action><option name=\"url\" value=\"http://127.0.0.1\"/></action><data><field name=\"money\" value=\"10000\"/></data></body><header version=\"1.0\"><timestamp>20190122</timestamp><username>yangsj</username><password>root</password></header></root>";

        Map<String,String> headerMap = new HashMap<>();
        Map<String,String> actionMap = new HashMap<>();
        Map<String,String> dataMap = new HashMap<>();

        Document document = DocumentHelper.parseText(xml);
        // 获取根元素
        Element root = document.getRootElement();
        //遍历根元素
        for (Iterator iter = root.elementIterator(); iter.hasNext();){
            Element element = (Element) iter.next();
            //遍历header节点
            if("header".equalsIgnoreCase(element.getName())){
                for(Iterator i = element.elementIterator(); i.hasNext();){
                    Element headerElement = (Element) i.next();
                    headerMap.put(headerElement.getName(),headerElement.getTextTrim());
                }
            }
            //遍历body节点
            if("body".equalsIgnoreCase(element.getName())){
                for (Iterator j = element.elementIterator(); j.hasNext();){
                    Element bodyElement = (Element) j.next();
                    //遍历action节点
                    if ("action".equalsIgnoreCase(bodyElement.getName())){
                        for (Iterator k = bodyElement.elementIterator(); k.hasNext();){
                            Element actionElement = (Element) k.next();
                            //获取节点的属性值
                            String name = actionElement.attributeValue("name");
                            String value = actionElement.attributeValue("value");
                            actionMap.put(name,value);
                        }
                    }
                    //遍历data节点
                    if ("data".equalsIgnoreCase(bodyElement.getName())){
                        for (Iterator k = bodyElement.elementIterator(); k.hasNext();){
                            Element actionElement = (Element) k.next();
                            //获取节点的属性值
                            String name = actionElement.attributeValue("name");
                            String value = actionElement.attributeValue("value");
                            dataMap.put(name,value);
                        }
                    }
                }
            }

        }

        headerMap.forEach((x,y)->System.out.println("name :" + x + " value :" + y));
        System.out.println("------------------------------------");
        actionMap.forEach((x,y)->System.out.println("name :" + x + " value :" + y));
        System.out.println("------------------------------------");
        dataMap.forEach((x,y)->System.out.println("name :" + x + " value :" + y));


    }
}
