package com.bw.qgs.gwcrecycle.model;

import com.bw.qgs.gwcrecycle.CallBack;
import com.bw.qgs.gwcrecycle.OkHttp;
import com.bw.qgs.gwcrecycle.ProjectApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * date:2018/12/18    9:01
 * author:秦广帅(Lenovo)
 * fileName:OneModel
 */
public class OneModel {
    public void getOne(String url, final CallBack callBack){
        ProjectApi projectApi = OkHttp.retrofit.create(ProjectApi.class);
        Call<String> call = projectApi.getOne(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                callBack.onSuccess(body);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onFailer("失败");
            }
        });
    }
}
