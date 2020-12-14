package com.example.day04.base;

import com.example.day04.interfaces.IBasePersenter;
import com.example.day04.interfaces.IBaseView;
import com.example.day04.interfaces.IModel;

import java.lang.ref.WeakReference;

//persenter P层基类
public abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {

    protected V mView;
    //通过弱引用把V层关联

    WeakReference<V> weakReference;

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<V>(view);
        mView = weakReference.get();
    }

    IModel model;

    @Override
    public void unAttachView() {
        mView = null;
        if (model != null) {
            model.clear();
        }
    }
}
