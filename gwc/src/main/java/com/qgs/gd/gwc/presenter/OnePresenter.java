package com.qgs.gd.gwc.presenter;

import com.qgs.gd.gwc.model.OneModel;
import com.qgs.gd.gwc.view.OneView;

/**
 * date:2018/11/21    19:02
 * author:秦广帅(Lenovo)
 * fileName:OnePresenter
 */
public class OnePresenter {
    private OneView mOneView;
    private OneModel mOneModel;

    public OnePresenter(OneView oneView) {
        mOneView = oneView;
        mOneModel = new OneModel();
    }

    public void onePresenter(String url) {
        mOneModel.one(url, new OneModel.HttpCallBack() {
            @Override
            public void getData(String name) {
                if (name != null) {
                    mOneView.onSuccess(name);
                } else {
                    mOneView.onFailer("失败");
                }
            }
        });
    }
}
