package com.example.day04.model.home;

import com.example.day04.base.BaseModel;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.home.IHome;
import com.example.day04.model.data.CityBean;
import com.example.day04.model.data.GradeBean;
import com.example.day04.net.CommonSubscriber;
import com.example.day04.net.HttpManager;
import com.example.day04.utils.RxUtils;


public class HomeModel extends BaseModel implements IHome.Model {
    @Override
    public void getCity(final ICallBack callBack) {
        //产生一个网络请求disposable对象
        // 把请求对象添加到对象池
        addDisposable(HttpManager.getInstance().getService().getCity()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CityBean>(callBack) {
                    @Override
                    public void onNext(CityBean cityBean) {
                        callBack.success(cityBean);
                    }
                }));
    }

    @Override
    public void getGradeReturn(String station_code, final ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getService().getGradeBean(station_code)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GradeBean>(callBack) {
                    @Override
                    public void onNext(GradeBean gradeBean) {
                        callBack.success(gradeBean);
                    }
                }));
    }
}
