package com.example.day04.persenter.home;

import android.view.View;

import com.example.day04.base.BasePersenter;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.home.IHome;
import com.example.day04.model.data.CityBean;
import com.example.day04.model.data.GradeBean;
import com.example.day04.model.home.HomeModel;


public class HomePersenter extends BasePersenter<IHome.View> implements IHome.Persenter {


    IHome.View view;
    IHome.Model model;

    public HomePersenter(IHome.View view){
        this.view = view;
        this.model = new HomeModel();
    }

    @Override
    public void getCity() {
        this.view.loading(View.VISIBLE);
        this.model.getCity(new ICallBack() {
            @Override
            public void fail(String msg) {
                if(view != null){
                    view.loading(View.GONE);
                    view.tips(msg);
                }
            }

            @Override
            public void success(Object object) {
                if(view != null){
                    view.loading(View.GONE);
                    view.getCityReturn((CityBean) object);
//                    Log.e("111",o.toString());
                }
            }
        });
    }

    @Override
    public void getGradeReturn(String station_code) {
        model.getGradeReturn(station_code, new ICallBack() {
            @Override
            public void success(Object o) {
                view.getGradeReturn((GradeBean) o);
            }

            @Override
            public void fail(String msg) {
                view.tips(msg);
            }
        });
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
        //释放当前页面还未完成的网络请求
        if(model != null){
            model.clear();
        }
    }
}
