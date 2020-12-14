package com.example.day04.model.tongpao;

import com.example.day04.base.BaseModel;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IArtFrag;
import com.example.day04.net.CommonSubscriber;
import com.example.day04.net.HttpManager;
import com.example.day04.utils.RxUtils;

public class ArtModel extends BaseModel implements IArtFrag.Model {
    @Override
    public void getDiscover(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getDiscover()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPDiscoverData>(callBack) {
                    @Override
                    public void onNext(TPDiscoverData tpDiscoverData) {
                        callBack.success(tpDiscoverData);
                    }
                }));
    }

    @Override
    public void getNavigation(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getNavigationBean()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPNavigationBean>(callBack) {
                    @Override
                    public void onNext(TPNavigationBean tpNavigationBean) {
                        callBack.success(tpNavigationBean);
                    }
                }));
    }

    @Override
    public void getRobe(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getRobeData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPRobeBean>(callBack) {
                    @Override
                    public void onNext(TPRobeBean tpRobeBean) {
                        callBack.success(tpRobeBean);
                    }
                }));
    }

    @Override
    public void getAssoci(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getAssocData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPAssociData>(callBack) {
                    @Override
                    public void onNext(TPAssociData tpAssociData) {
                        callBack.success(tpAssociData);
                    }
                }));
    }

    @Override
    public void getLevels(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getLevelData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPLevelData>(callBack) {
                    @Override
                    public void onNext(TPLevelData tpLevelData) {
                        callBack.success(tpLevelData);
                    }
                }));
    }

    @Override
    public void getSquare(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getSquareData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPSquareBean>(callBack) {
                    @Override
                    public void onNext(TPSquareBean tpSquareBean) {
                        callBack.success(tpSquareBean);
                    }
                }));
    }

    @Override
    public void getMoney(ICallBack callBack) {
        addDisposable(HttpManager.getInstance().getToangpao().getMoneyData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TPMoneyData>(callBack) {
                    @Override
                    public void onNext(TPMoneyData tpMoneyData) {
                        callBack.success(tpMoneyData);
                    }
                }));
    }
}
