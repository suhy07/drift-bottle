package com.example.myhomework.utils;

import org.w3c.dom.*;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadXmlUtil {

    private static ReadXmlUtil readXmlUtil=new ReadXmlUtil();

    private static void ReadXmlUtil(){

    }//单例
    public static NodeList GetdNodeLise(FileInputStream inputStream, String head) {
        NodeList sList=null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
      /*
        NodeList list=null;
        FileInputStream fileInputStream=null;
        //
        //test to save userdata
        //
        String str="<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<userlist>\n" +
                "    <user>\n" +
                "        <uid>221900122</uid>\n" +
                "        <NicknName>测试用户</NicknName>\n" +
                "        <Type>普通用户</Type>\n" +
                "    </user>\n" +
                "</userlist>";
        temp_save(str,activity);
        //
        //test to save userdata
        //
        try {
            fileInputStream=activity.openFileInput("data.xml");
            list=ReadXmlUtil.GetdNodeLise(fileInputStream,"user" );
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
                try {
                    fileInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
        if(list!=null) {
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                NodeList childNodes = element.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        //TODO 设置uid检测
                        //获取节点
                        switch (childNodes.item(j).getNodeName()) {
                            case "uid":
                                uid = childNodes.item(j).getFirstChild().getNodeValue();
                                break;
                            case "NicknName":
                                userNickName = childNodes.item(j).getFirstChild().getNodeValue();
                                break;
                            case "Type":
                                userType=UserType.InitFromStr(childNodes.item(j).getFirstChild().getNodeValue());
                                break;
                        }
                    }
                }
            }
        }*/
/*
    private static void temp_save(String str,Activity activity){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=activity.openFileOutput("data.xml", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(str);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }*/
