package com.qgs.gd.gouwuche.view;

import com.qgs.gd.gouwuche.bean.User;

import java.util.List;

/**
 * date:2018/11/21    10:39
 * author:秦广帅(Lenovo)
 * fileName:OneView
 */
public interface OneView {
    void onSuccess(String result);
    void onFailer(String msg);
    void onSetData(List<User.DataBean> list);
}
