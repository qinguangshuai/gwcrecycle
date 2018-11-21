package com.qgs.gd.gouwuche.model;

import com.google.gson.Gson;
import com.qgs.gd.gouwuche.bean.User;
import com.qgs.gd.gouwuche.http.OkHtpp;

import java.util.List;

/**
 * date:2018/11/21    10:39
 * author:秦广帅(Lenovo)
 * fileName:OneModel
 */
public class OneModel {
    public void jie(String path, final OkHtpp.HttpCallBack callBack){
        OkHtpp okHtpp = new OkHtpp();
        okHtpp.jie(path, new OkHtpp.HttpCallBack() {
            @Override
            public void getData(String s) {
                Gson gson = new Gson();
                User user = gson.fromJson(s, User.class);
                List<User.DataBean> data = user.getData();
                callBack.setData(data);
            }

            @Override
            public void setData(List<User.DataBean> list) {

            }
        });
    }
}
