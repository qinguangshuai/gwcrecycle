package com.qgs.gd.gouwuche.http;

import com.qgs.gd.gouwuche.bean.User;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * date:2018/11/21    10:33
 * author:秦广帅(Lenovo)
 * fileName:OkHtpp
 */
public class OkHtpp {
    public OkHtpp jie(String path, final HttpCallBack callBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if(code==200){
                    ResponseBody body = response.body();
                    String s = body.string();
                    callBack.getData(s);
                }
            }
        });
        return this;
    }
    public interface HttpCallBack{
        void getData(String s);
        void setData(List<User.DataBean> list);
    }
}
