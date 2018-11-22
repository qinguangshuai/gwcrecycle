package com.qgs.gd.gwc;

import android.app.Application;

import com.qgs.gd.gwc.http.OkHttp;

/**
 * date:2018/11/21    18:41
 * author:秦广帅(Lenovo)
 * fileName:App
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.init();
    }
}
