package com.ld.retrofitpracticedemo1.impl;

import com.ld.retrofitpracticedemo1.entities.PostBodyBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author LD
 * @Time 2019/6/27 17:09
 * @Describe Post请求方式接口
 * @Modify
 */
public interface PostApi {


    /**
     * @param key
     * @return
     * @Field 注解
     */
    @FormUrlEncoded     //别忘了这一句，否则会出现@Field parameters can only be used with form encoding的错误
    @POST("api/fieldParam")
    Call<ResponseBody> postFieldFun(@Field("key") String key);


    /**
     * @param params
     * @return
     * @FieldMap 注解
     */
    @FormUrlEncoded
    @POST("api/fieldMapParam")
    Call<ResponseBody> postFildMapFun(@FieldMap Map<String, String> params);


    /**
     * @return
     * @Body 注解
     */
    @POST("api/bodyParam")
    Call<ResponseBody> postBodyFun(@Body PostBodyBean postBodyBean);

}
