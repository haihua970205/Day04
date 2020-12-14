package com.example.day04.net;

import com.example.day04.api.ApiImage;
import com.example.day04.api.ApiService;
import com.example.day04.api.ApiTongpao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instance;

    public static HttpManager getInstance(){
        if(instance == null){
            synchronized (HttpManager.class){
                if (instance ==null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private Retrofit getRetrofit(String url){
        Retrofit build = new Retrofit.Builder().baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        return build;
    }

    private OkHttpClient getOkHttpClient() {

        OkHttpClient build = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .build();
        return build;
    }

    static class HeaderInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 获取主页下载的对象
     * @param baseUrl
     * @return
     */

    private ApiService service;
    public ApiService getService(){
        if(service == null){
            service = getRetrofit(ApiService.CITY_URL).create(ApiService.class);
        }
        return service;
    }


    /**
     * 获取同袍下载的对象
     * @param
     * @return
     */
    private ApiTongpao tongpao;
    public ApiTongpao getToangpao(){
        if(tongpao == null){
            tongpao = getRetrofit(ApiTongpao.BASE_URL).create(ApiTongpao.class);
        }
        return tongpao;
    }


    /**
     * 获取图片下载的对象
     * @param baseUrl
     * @return
     */
    private ApiImage imageApi;
    private Map<String,Retrofit> map = new HashMap<>();  //retrofit请求对象的对象池
    public ApiImage getImageApi(String baseUrl){
        Retrofit retrofit = map.get(baseUrl);
        if (imageApi!=null){
            imageApi = retrofit.create(ApiImage.class);
        }else {
            retrofit = getRetrofit(baseUrl);
            imageApi = retrofit.create(ApiImage.class);
            map.put(baseUrl,retrofit);
        }
        return imageApi;
    }





















}