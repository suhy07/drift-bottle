package com.example.myhomework.Util;

import org.w3c.dom.*;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadXmlUtil {

    private static ReadXmlUtil readXmlUtil=new ReadXmlUtil();

    private static void ReadXmlUtil(){

    }//单例
    public static NodeList GetdNodeLise(FileInputStream inputStream, String head) {
        //1.创建DocumentBuilderFactory对象
        NodeList sList=null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse(inputStream);
            sList = d.getElementsByTagName(head);//头部标签
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sList;
    }
}
