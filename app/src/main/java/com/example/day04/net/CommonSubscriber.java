package com.example.day04.net;

import android.text.TextUtils;


import com.example.day04.interfaces.ICallBack;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 处理网络请求结果的错误和完成
 * @param <T>
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    private ICallBack callback;
    private String errorMsg;
    private boolean isShowErrorState = false;

    protected CommonSubscriber(ICallBack callback){
        this.callback = callback;
    }

    protected CommonSubscriber(ICallBack callback, String emsg){
        this.callback = callback;
        errorMsg = emsg;
    }

    @Override
    public void onError(Throwable t) {
        //LoadingUtil.getInstance().hideLoading();
        if(callback == null) return;
        if(errorMsg != null && TextUtils.isEmpty(errorMsg)){
            callback.fail(errorMsg);
        }
    }

    @Override
    public void onComplete() {
        //LoadingUtil.getInstance().hideLoading();
        //mView.showLoading(View.GONE);
    }
}
