package com.bw.qgs.gwcrecycle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * date:2018/12/18    9:02
 * author:秦广帅(Lenovo)
 * fileName:ProjectApi
 */
public interface ProjectApi {
    @GET
    Call<String> getOne(@Url String url);
}
