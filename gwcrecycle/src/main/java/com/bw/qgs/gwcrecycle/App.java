package com.bw.qgs.gwcrecycle;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * date:2018/12/18    9:04
 * author:秦广帅(Lenovo)
 * fileName:App
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.init();
        Fresco.initialize(this);
    }
}
