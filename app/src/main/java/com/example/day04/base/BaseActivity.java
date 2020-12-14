package com.example.day04.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.day04.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

// Activity基础类
public abstract class BaseActivity<P extends BasePersenter> extends AppCompatActivity implements IBaseView {

    protected P persenter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //需要的界面view
        setContentView(getLayout());
        bind = ButterKnife.bind(this);
        //初始化界面
        initView();
        persenter = createPersenter();
        if (persenter != null) {
            persenter.attachView(this);
        }
        //初始化数据
        initData();
    }

    //定义一个获取当前界面的方法，由子类提供
    protected abstract int getLayout();

    //初始化界面
    protected abstract void initView();

    //初始化P层方法
    protected abstract P createPersenter();

    //初始化界面数据
    protected abstract void initData();


    @Override
    public void tips(String tip) {
        Log.e("111", tip + "tips");
    }

    @Override
    public void loading(int visible) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放p关联的v的引用
        if (persenter != null) {
            persenter.unAttachView();
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}
