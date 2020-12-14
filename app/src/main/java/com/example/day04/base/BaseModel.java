package com.example.day04.base;

import com.example.day04.interfaces.IModel;


import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

//model 层 基础类 处理网络数据加载效率问题通过被压式
public abstract class BaseModel implements IModel {

    CompositeDisposable disposableSet= new CompositeDisposable();

    //将当前的网络请天求添加到缓存
    @Override
    public void addDisposable(Disposable disposable) {
        disposableSet.add(disposable);
    }

    //取消还未进行的网络数据

    @Override
    public void clear() {
        disposableSet.clear();
    }
}
