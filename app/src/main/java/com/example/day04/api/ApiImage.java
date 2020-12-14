package com.example.day04.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 图片下载的接口
 */
public interface ApiImage {

    @GET
    Flowable<ResponseBody> downImage(@Url String url);
}
