package com.qgs.gd.gwc.http;

import com.google.gson.Gson;
import com.qgs.gd.gwc.BaseRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * date:2018/11/21    18:17
 * author:秦广帅(Lenovo)
 * fileName:OkHttp
 */
public class OkHttp {
    private static final String MEDIO_GET = "GET";
    private static OkHttpClient client;

    //单例模式
    private OkHttp(){}
    //创建对象
    public static void init(){
        client = new OkHttpClient.Builder()
                .connectTimeout(3000,TimeUnit.MILLISECONDS)
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .writeTimeout(3000,TimeUnit.MILLISECONDS)
                .build();
    }

    public static Request creat(String url, String method){
        /*//设置json数据
        RequestBody requestBody = null;
        //判断基类是否为空
        if (baseRequest != null) {
            //将数据变成json串
            String sjson = gson.toJson(baseRequest);
            //利用MediaType测试接口
            MediaType parse = MediaType.parse(MEDIO_TYPE);
            //存入RequestBody对象
            requestBody = RequestBody.create(parse, sjson);
        }*/
        Request.Builder builder = new Request.Builder().url(url);
        Request request = null;
        switch (method){
            case MEDIO_GET:
                request = builder.build();
                break;
            /*case MEDIO_POST:
                request = builder.post(requestBody).build();
                break;*/
        }
        return request;
    }

    public static void enqueueGet(String url, Callback callback){
        Request request = creat(url, MEDIO_GET);
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

   /* public static void enqueuePost(String url,Callback callback,BaseRequest baseRequest){
        Request request = creat(url, MEDIO_POST, baseRequest);
        Call call = client.newCall(request);
        call.enqueue(callback);
    }*/


}
