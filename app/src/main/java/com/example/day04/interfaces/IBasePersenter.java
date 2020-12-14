package com.example.day04.interfaces;

public interface IBasePersenter<V extends IBaseView> {

    void attachView(V view);

    void unAttachView();
}
