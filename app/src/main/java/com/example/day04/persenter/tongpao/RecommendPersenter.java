package com.example.day04.persenter.tongpao;

import android.view.View;

import com.example.day04.base.BasePersenter;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IRecommend;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.model.tongpao.RecommendModel;

public class RecommendPersenter extends BasePersenter<IRecommend.View> implements IRecommend.Persenter {

    IRecommend.View view;
    IRecommend.Model model;

    public RecommendPersenter(IRecommend.View view) {
        this.view = view;
        this.model = new RecommendModel();
    }

    @Override
    public void getBanner() {
        model.getBanner(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null) view.getBannerReturn((TPBannerBean) o);
            }

            @Override
            public void fail(String msg) {
                if (view != null) view.tips(msg);
            }
        });
    }

    @Override
    public void getTopic() {
        view.loading(View.VISIBLE);
        model.getTopic(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view!=null){
                    view.loading(View.GONE);
                    view.getTopicReturn((TPTopicBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getRecommend() {
        model.getRecommend(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null) view.getRecommendReturn((TPRecommendBean) o);
            }

            @Override
            public void fail(String msg) {
                if (view != null) view.tips(msg);
            }
        });
    }

    @Override
    public void getUser() {
        view.loading(View.VISIBLE);
        model.getUser(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null) {
                    view.loading(View.GONE);
                    view.getUserReturn((TPUserBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getVideo() {
        model.getVideo(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view!=null){
                    view.getVideoReturn((TPVideoBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }
        });
    }
}
