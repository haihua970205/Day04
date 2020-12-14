package com.example.day04.model.tongpao;

import com.example.day04.base.BaseModel;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IRecommend;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.net.CommonSubscriber;
import com.example.day04.net.HttpManager;
import com.example.day04.utils.RxUtils;


public class RecommendModel extends BaseModel implements IRecommend.Model {

    @Override
    public void getBanner(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao(). getBannerData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TPBannerBean>(callBack) {
            @Override
            public void onNext(TPBannerBean tpBannerBean) {
                callBack.success(tpBannerBean);
            }
        }));
    }

    @Override
    public void getTopic(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getTopicData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TPTopicBean>(callBack) {
            @Override
            public void onNext(TPTopicBean tpTopicBean) {
                callBack.success(tpTopicBean);
            }
        }));
    }

    @Override
    public void getRecommend(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getRecommendData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TPRecommendBean>(callBack) {
            @Override
            public void onNext(TPRecommendBean tpRecommendBean) {
                callBack.success(tpRecommendBean);
            }
        }));
    }

    @Override
    public void getUser(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getUserData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TPUserBean>(callBack) {
            @Override
            public void onNext(TPUserBean tpUserBean) {
                callBack.success(tpUserBean);
            }
        }));
    }

    @Override
    public void getVideo(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getVideoData()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TPVideoBean>(callBack) {
            @Override
            public void onNext(TPVideoBean tpVideoBean) {
                callBack.success(tpVideoBean);
            }
        }));
    }
}
