package com.example.day04.interfaces;


import io.reactivex.disposables.Disposable;

public interface IModel {

    void addDisposable(Disposable disposable);

    void clear();
}
