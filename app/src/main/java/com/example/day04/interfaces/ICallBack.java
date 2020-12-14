package com.example.day04.interfaces;


//数据回调 m->p
public interface ICallBack<T> {

    //成功方法 泛型为 任意类型
    void success(T t);
    void fail(String msg);

}
