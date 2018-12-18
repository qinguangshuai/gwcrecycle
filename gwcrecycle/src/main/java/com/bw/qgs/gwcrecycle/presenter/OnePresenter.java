package com.bw.qgs.gwcrecycle.presenter;

import com.bw.qgs.gwcrecycle.CallBack;
import com.bw.qgs.gwcrecycle.model.OneModel;
import com.bw.qgs.gwcrecycle.view.OneView;

/**
 * date:2018/12/18    9:09
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

    public void getOne(String url){
        mOneModel.getOne(url, new CallBack() {
            @Override
            public void onSuccess(String result) {
                mOneView.onSuccess(result);
            }

            @Override
            public void onFailer(String msg) {
                mOneView.onFailer("失败");
            }
        });
    }
}
