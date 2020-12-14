package com.example.day04.interfaces.tongpao;

import com.example.day04.interfaces.ICallBack;

/**
 * 提供给所有的业务使用的公用接口
 */
public interface IDown {

    interface DownModel{
        void downImage(String url, ICallBack callback);
    }

}
