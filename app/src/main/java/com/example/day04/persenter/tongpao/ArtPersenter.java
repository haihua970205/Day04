package com.example.day04.persenter.tongpao;

import com.example.day04.base.BasePersenter;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IArtFrag;
import com.example.day04.model.tongpao.ArtModel;
import com.example.day04.model.tongpao.TPAssociData;
import com.example.day04.model.tongpao.TPDiscoverData;
import com.example.day04.model.tongpao.TPLevelData;
import com.example.day04.model.tongpao.TPMoneyData;
import com.example.day04.model.tongpao.TPNavigationBean;
import com.example.day04.model.tongpao.TPRobeBean;
import com.example.day04.model.tongpao.TPSquareBean;

public class ArtPersenter extends BasePersenter<IArtFrag.View> implements IArtFrag.Persenter{

    private IArtFrag.View view;
    private IArtFrag.Model model;

    public ArtPersenter(IArtFrag.View view) {
        this.view = view;
        model = new ArtModel();
    }

    @Override
    public void getDiscover() {
        model.getDiscover(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getDiscoverReturn((TPDiscoverData) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getNavigation() {
        model.getNavigation(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getNavigationReturn((TPNavigationBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getRobe() {
        model.getRobe(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getRobeReturn((TPRobeBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getAssoci() {
        model.getAssoci(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getAssociReturn((TPAssociData) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getLevels() {
        model.getLevels(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getLevelReturn((TPLevelData) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getSquare() {
        model.getSquare(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getSquareReturn((TPSquareBean) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }

    @Override
    public void getMoney() {
        model.getMoney(new ICallBack() {
            @Override
            public void success(Object o) {
                if (view != null){
                    view.getMoneyReturn((TPMoneyData) o);
                }
            }

            @Override
            public void fail(String msg) {
                if (view != null){
                    view.tips(msg);
                }
            }
        });
    }
}
