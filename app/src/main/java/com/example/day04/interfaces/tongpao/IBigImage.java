package com.example.day04.interfaces.tongpao;

import com.example.day04.interfaces.IBasePersenter;
import com.example.day04.interfaces.IBaseView;
import com.example.day04.interfaces.IModel;

public interface IBigImage {

    interface View extends IBaseView {

        void downReturn(String path);

    }

    interface Persenter extends IBasePersenter<View> {
        void downImg(String url);
    }


    interface Model extends IModel {
        void getBigImage();
    }
}
