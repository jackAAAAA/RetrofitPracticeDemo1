package com.ld.retrofitpracticedemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.ld.retrofitpracticedemo1.entities.ArticalInfo;
import com.ld.retrofitpracticedemo1.entities.PostBodyBean;
import com.ld.retrofitpracticedemo1.entities.UserInfo;
import com.ld.retrofitpracticedemo1.impl.GetApi;
import com.ld.retrofitpracticedemo1.impl.PostApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Get相关
    private Button btnQuery;
    private Button btnQueryMap;
    private Button btnPath;
    private Button btnUrl;
    private Button btnHeaders;
    private Button btnHeader;       //@Header注解
    private Button btnHeaderMap;    //@HeaderMap注解

    //Post相关
    private Button btnField;
    private Button btnFieldMap;
    private Button btnBody;


    private Retrofit retrofit;
    private GetApi getApi;
    private PostApi postApi;

    private static final String TAG = "ceshi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initRetrofit();

    }

    private void initRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //返回的Json数据进行解析
                .build();

        getApi = retrofit.create(GetApi.class);
        postApi = retrofit.create(PostApi.class);

    }

    /**
     * 控件的绑定及点击事件
     */
    private void initView() {

        //Get相关
        btnQuery = findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(this);

        btnQueryMap = findViewById(R.id.btn_queryMap);
        btnQueryMap.setOnClickListener(this);

        btnPath = findViewById(R.id.btn_path);
        btnPath.setOnClickListener(this);

        btnUrl = findViewById(R.id.btn_url);
        btnUrl.setOnClickListener(this);

        btnHeaders = findViewById(R.id.btn_headers);
        btnHeaders.setOnClickListener(this);

        btnHeader = findViewById(R.id.btn_header);
        btnHeader.setOnClickListener(this);

        btnHeaderMap = findViewById(R.id.btn_headerMap);
        btnHeaderMap.setOnClickListener(this);

        //Post相关
        btnField = findViewById(R.id.btn_field);
        btnField.setOnClickListener(this);

        btnFieldMap = findViewById(R.id.btn_fieldMap);
        btnFieldMap.setOnClickListener(this);

        btnBody = findViewById(R.id.btn_body);
        btnBody.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:        //@Query注解

                getUserInfo("1234");

                break;
            case R.id.btn_queryMap:     //@QueryMap注解

                getArticalInfo();

                break;
            case R.id.btn_path:         //@Path注解
                getDynamicInfo(1);
                getDynamicInfo(2);
                getDynamicInfo2("na/me");
                break;
            case R.id.btn_url:          //@@Url注解

                getDynamicUrlInfo("http://mock-api.com/2vKVbXK8.mock/api/getDynamicUrlData");
                getDynamicUrlInfo("http://mock-api.com/2vKVbXK8.mock/api/getUserInfo?id=1234");

                break;
            case R.id.btn_headers:      //@Headers()静态添加头部信息

                getStaticHeadersInfo();
                getStaticMoreHeadersInfo();
                break;

            case R.id.btn_header:       //@Header动态添加单个头部信息


                getDynamicHeaderInfo();

                break;
            case R.id.btn_headerMap:    //@HeaderMap动态添加多个头部信息

                getDynamicHeaderMapInfo();
                break;

            case R.id.btn_field:        //@Field注解

                postFieldFun();
                break;

            case R.id.btn_fieldMap:     //@FieldMap注解
                postFieldMapFun();

                break;

            case R.id.btn_body:         //@Body注解

                postBodyFun();

                break;

            default:
                break;
        }
    }

    //-------------------------------------------Get相关---------------------------------------------

    /**
     * @Query 获取用户信息
     */
    private void getUserInfo(String userid) {

        getApi.getUserInfo(userid).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response != null && response.body() != null) {
                    //此处为获取到的信息
                    UserInfo userInfo = response.body();
                    Log.i(TAG, "onResponse: " + userInfo.toString());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.i(TAG, "onFailure: 错误" + t);
            }
        });
    }

    /**
     * @QueryMap 获取文章信息
     */
    private void getArticalInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "405");
        params.put("page", "1");

        getApi.getArticalInfo(params).enqueue(new Callback<ArticalInfo>() {
            @Override
            public void onResponse(Call<ArticalInfo> call, Response<ArticalInfo> response) {
                if (response != null && response.body() != null) {
                    Log.i(TAG, "onRespons:" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ArticalInfo> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    /**
     * @param param
     * @Path注解
     */
    private void getDynamicInfo(int param) {

        getApi.getDynamicInfo(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    private void getDynamicInfo2(String param) {

        getApi.getDynamicInfo2(param).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }


    /**
     * @Url注解
     */
    private void getDynamicUrlInfo(String url) {

        getApi.getDynamicUrl(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });


    }

    /**
     * @Headers 静态添加单个注解
     */
    private void getStaticHeadersInfo() {
        getApi.getStaticHeadersInfo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * @Headers 静态添加多个注解
     */
    private void getStaticMoreHeadersInfo() {
        getApi.getStaticMoreHeadersInfo().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 动态添加单个头部信息
     */
    private void getDynamicHeaderInfo() {

        getApi.getDynamicHeaderInfo("1.1.1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 动态添加多个头部信息
     */
    private void getDynamicHeaderMapInfo() {

        Map<String, String> headers = new HashMap<>();
        headers.put("version", "2.2.2");
        headers.put("type", "Android");
        getApi.getDynamicHeadersInfo(headers).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    //------------------------------------------Post相关---------------------------------------------

    /**
     * @Field 注解
     */
    private void postFieldFun() {

        postApi.postFieldFun("myfittinglife").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);
            }
        });
    }

    /**
     * @FieldMap 注解
     */
    private void postFieldMapFun() {

        Map<String, String> params = new HashMap<>();
        params.put("key", "myfittinglife");
        params.put("password", "123456");

        postApi.postFildMapFun(params).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);

            }
        });
    }

    /**
     * @Body 注解
     */
    private void postBodyFun(){


        PostBodyBean postBodyBean = new PostBodyBean("myfittinglife",1,true);
        postApi.postBodyFun(postBodyBean).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String str = new String(response.body().bytes());
                    Log.i(TAG, "onResponse: " + str);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t);

            }
        });
    }


    //生成Json字符串
    private void GsonForm() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1234");
        userInfo.setUserName("LD");
        userInfo.setDescribe("真帅");
        Gson gson = new Gson();
        String s = gson.toJson(userInfo);
        Log.i(TAG, "onClick: Json字符串为" + s);
    }
}
