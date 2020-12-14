package com.example.day04.persenter.tongpao;

import com.example.day04.base.BasePersenter;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.tongpao.IBigImage;
import com.example.day04.model.tongpao.BigImageModel;

public class BigImagePersenter extends BasePersenter<IBigImage.View> implements IBigImage.Persenter {

    IBigImage.View view;
    //接口的规范是DownModel mode
    BigImageModel mode;


    public BigImagePersenter(IBigImage.View view){
        this.view = view;
        mode = new BigImageModel();
    }

    @Override
    public void downImg(String url) {
        mode.downImage(url, new ICallBack() {
            @Override
            public void success(Object o) {
                if(view != null){
                    view.downReturn((String) o);
                }
            }
            @Override
            public void fail(String msg) {

            }
        });
    }

    @Override
    public void unAttachView() {
        super.unAttachView();
        if(mode != null){
            mode.clear();
            mode = null;
        }
    }
}