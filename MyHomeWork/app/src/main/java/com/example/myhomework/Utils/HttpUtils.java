package com.example.myhomework.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
    private static void HttpUtil(){}
    public static String Post_file(String url,String filename,String pathname){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String str="";
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",filename,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(pathname)))
                .build();
        try{
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
            str=response.toString();
        }catch (Exception e){
            System.out.println(e);
        }
        return str;
    }

    public static void setURLimageViewByBitmap(String url, ImageView imageView, Activity activity) {
        new Thread(()->{
            try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = null;//获得图片的数据流is = conn.getInputStream();
            Bitmap bitmap=BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
            activity.runOnUiThread(()->{

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        }).start();
    }
    public static void setURLimageButtonByBitmap(String url, ImageButton imageButton, Activity activity) {
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            activity.runOnUiThread(()->imageButton.setImageBitmap(BitmapFactory.decodeStream(is)));
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
