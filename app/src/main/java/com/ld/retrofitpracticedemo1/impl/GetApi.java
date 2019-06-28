package com.ld.retrofitpracticedemo1.impl;


import com.ld.retrofitpracticedemo1.entities.ArticalInfo;
import com.ld.retrofitpracticedemo1.entities.UserInfo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @Author LD
 * @Time 2019/6/26 18:11
 * @Describe Get请求方式接口
 * @Modify
 */
public interface GetApi {

    /**
     * 获取用户信息
     *
     * @return
     * @Query 注解
     */
    @GET("api/getUserInfo")
    Call<UserInfo> getUserInfo(@Query("id") String userId);



    /**
     * 获取文章信息
     *
     * @param params
     * @return
     */
    @GET("api/getArticalInfo")
    Call<ArticalInfo> getArticalInfo(@QueryMap Map<String, String> params);


    /**
     * 获取动态信息
     *
     * @param param
     * @return
     */
    @GET("api/getDynamicInfo/{param}/data")
    Call<ResponseBody> getDynamicInfo(@Path("param") int param);


    //验证@Path注解不能加入/
    @GET("api/getDynamicInfo/{param}/data")
    Call<ResponseBody> getDynamicInfo2(@Path("param") String param);


    /**
     * 整体动态的Url地址
     *
     * @param url http://mock-api.com/2vKVbXK8.mock/api/getDynamicUrlData
     * @return
     */
    @GET
    Call<ResponseBody> getDynamicUrl(@Url String url);


    /**
     * 静态添加一个头部信息
     *
     * @return
     */
    @Headers("version:1.1")
    @GET("api/staticHeadersInfo")
    Call<ResponseBody> getStaticHeadersInfo();

    /**
     * 静态添加多个头部信息
     *
     * @return
     */
    @Headers({"version:1.1",
            "type:android"})
    @GET("api/staticHeadersInfo")
    Call<ResponseBody> getStaticMoreHeadersInfo();

    /**
     * 动态添加单个头部
     *
     * @param version
     * @return
     */
    @GET("api/dynamicHeadersInfo")
    Call<ResponseBody> getDynamicHeaderInfo(@Header("version") String version);

    /**
     * 动态添加多个头部
     *
     * @param headers
     * @return
     */
    @GET("api/dynamicHeadersInfo")
    Call<ResponseBody> getDynamicHeadersInfo(@HeaderMap Map<String, String> headers);


}
