package com.example.myhomework.Util;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;


public class InitUserDataUtil {
    public static InitUserDataUtil initUserDataUtil=new InitUserDataUtil();
    private static String uid;
    private static String userNickName;
    private static UserType userType;
    private InitUserDataUtil() {
    }
    private static InitUserDataUtil GetInitUserDataUtil(){
        return initUserDataUtil;
    }
    public static void ResetUserData(View view,Activity activity){
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

        Toast.makeText(view.getContext(),"uid:"+uid,Toast.LENGTH_LONG).show();
        Toast.makeText(view.getContext(),"userNickName:"+userNickName,Toast.LENGTH_LONG).show();
        Toast.makeText(view.getContext(),"userType:"+userType,Toast.LENGTH_LONG).show();
    }
    public static String GetUid(){
        return uid;
    }
    public static String GetUserNickName(){
        return userNickName;
    }
    public static String GetUserType(){
        return userType.typeStr;
    }
    private void SetHead(){ }

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
    }
    protected enum UserType{
        Normal("普通用户",1),Admin("管理员",2);
        private String typeStr;
        private int index;
        private UserType(String typeStr,int index){
            this.typeStr=typeStr;this.index=index;}
        public static UserType InitFromStr(String str){
            switch (str){
                case "普通用户":return Normal;
                case "管理员":return Admin;
            }
            return null;
        }
    }
}

