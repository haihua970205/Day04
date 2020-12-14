package com.example.day04.api;


import com.example.day04.model.data.CityBean;
import com.example.day04.model.data.GradeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String CITY_URL="https://api.epmap.org/";
    @GET("api/v1/air/station_list")
    Flowable<CityBean> getCity();

    @GET("api/v1/air/station")
    Flowable<GradeBean> getGradeBean(@Query("station_code")String station_code);

}
