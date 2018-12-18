package com.bw.qgs.gwcrecycle;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * date:2018/12/18    8:57
 * author:秦广帅(Lenovo)
 * fileName:OkHttp
 */
public class OkHttp {

    private static OkHttpClient client;
    public static Retrofit retrofit;

    public OkHttp(){}

    public static void init(){
        client = new OkHttpClient.Builder()
                .build();
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(UrlUtil.ZONG)
                .client(client)
                .build();
    }
}
