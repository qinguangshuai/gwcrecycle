package com.qgs.gd.gwc.model;

import android.os.Handler;
import android.os.Message;

import com.qgs.gd.gwc.http.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * date:2018/11/21    18:42
 * author:秦广帅(Lenovo)
 * fileName:OneModel
 */
public class OneModel {

    public void one(String url, final HttpCallBack httpCallBack) {
        OkHttp.enqueueGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.getData(s);
                    }
                });
            }
        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public interface HttpCallBack {
        void getData(String name);
    }


}
