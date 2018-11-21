package com.qgs.gd.gouwuche.presenter;

import com.qgs.gd.gouwuche.bean.User;
import com.qgs.gd.gouwuche.http.OkHtpp;
import com.qgs.gd.gouwuche.model.OneModel;
import com.qgs.gd.gouwuche.view.OneView;

import java.util.List;

/**
 * date:2018/11/21    10:39
 * author:秦广帅(Lenovo)
 * fileName:OnePresenter
 */
public class OnePresenter {
    private OneModel mOneModel;
    private OneView mOneView;
    private List<User.DataBean> mList;

    public OnePresenter(OneView oneView) {
        mOneView = oneView;
        mOneModel = new OneModel();
    }

    public void jie(String path){
        mOneModel.jie(path, new OkHtpp.HttpCallBack() {
            @Override
            public void getData(String s) {
                if(s.equals("请求成功")){
                    mOneView.onSuccess("请求成功");
                }else{
                    mOneView.onFailer("请求失败");
                }
            }

            @Override
            public void setData(List<User.DataBean> list) {
                mList = list;
                mOneView.onSetData(mList);
            }
        });
    }
}
