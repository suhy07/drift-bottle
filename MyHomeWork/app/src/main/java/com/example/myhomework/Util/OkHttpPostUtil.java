package com.example.myhomework.Util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpPostUtil {
    String uri;
    String filename;
    String pathname;
    public void Postfile(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",filename,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(pathname)))
                .build();
        try{
            Request request = new Request.Builder()
                    .url(uri)
                    .method("POST", body)
                    .build();
            Response response = client.newCall(request).execute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
