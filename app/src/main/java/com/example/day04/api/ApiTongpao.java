package com.example.day04.api;

import com.example.day04.model.data.TPPersonalBean;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.model.tongpao.TPAssociData;
import com.example.day04.model.tongpao.TPDiscoverData;
import com.example.day04.model.tongpao.TPLevelData;
import com.example.day04.model.tongpao.TPMoneyData;
import com.example.day04.model.tongpao.TPNavTypeBean;
import com.example.day04.model.tongpao.TPNavigationBean;
import com.example.day04.model.tongpao.TPRobeBean;
import com.example.day04.model.tongpao.TPSquareBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiTongpao {

    String BASE_URL="http://cdwan.cn:7000/tongpao/";

    //首页推荐轮播图接口：/home/banner.json
    @GET("home/banner.json")
    Flowable<TPBannerBean> getBannerData();

    //首页推荐热门话题接口：/home/topic_discussed.json
    @GET("home/topic_discussed.json")
    Flowable<TPTopicBean> getTopicData();

    //首页推荐数据接口： /home/recommend.json
    @GET("home/recommend.json")
    Flowable<TPRecommendBean> getRecommendData();

    //首页推荐热门用户：/home/hot_user.json
    @GET("home/hot_user.json")
    Flowable<TPUserBean> getUserData();

    //首页用户详情: /home/personal.json
    @GET("home/personal.json")
    Flowable<TPPersonalBean> getPersonal();

    //首页视频栏数据接口：/home/video.json
    @GET("home/video.json")
    Flowable<TPVideoBean> getVideoData();

    //发现热点数据接口：/discover/hot_activity.json
    @GET("discover/hot_activity.json")
    Flowable<TPDiscoverData> getDiscover();

    //分类导航： /discover/navigation.json
    @GET("discover/navigation.json")
    Flowable<TPNavigationBean> getNavigationBean();

/*******************发现***********************/

    //对应的分类数据 /discover/news_分类的type.json
//http://cdwan.cn:7000/tongpao/1.json
    @GET("discover/news_{type}.json")
    Observable<TPNavTypeBean> getNavTypeData(@Path("type") int type);

    //发现热点 袍子  /discover/robe.json
    @GET("discover/robe.json")
    Flowable<TPRobeBean> getRobeData();

    //社团  ：/discover/association.json
    @GET("discover/association.json")
    Flowable<TPAssociData> getAssocData();

    //排行榜--等级榜  /discover/rank_level.json
    @GET("discover/rank_level.json")
    Flowable<TPLevelData> getLevelData();

    //排行榜--签到榜 /discover/rank_sign.json
    @GET("discover/rank_sign.json")
    Flowable<TPSquareBean> getSquareData();

    //排行榜--土豪榜 /discover/rank_money.json
    @GET("discover/rank_money.json")
    Flowable<TPMoneyData> getMoneyData();




}
