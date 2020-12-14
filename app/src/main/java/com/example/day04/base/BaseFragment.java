package com.example.day04.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.day04.interfaces.IBaseView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePersenter> extends Fragment implements IBaseView {

    protected P persenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(),getLayout(),null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        initView();
        persenter = createPersenter();
        if(persenter!=null){
            persenter.attachView(this);
        }
        initData();
    }

    public abstract int getLayout();
    public abstract void initView();
    public abstract P createPersenter();
    public abstract void initData();


    @Override
    public void tips(String tip) {
        Log.e("111",tip);
    }

    @Override
    public void loading(int visible) {

    }

    @Override
    public void onDestroy() {
        if (bind != null){
            bind.unbind();
        }
        if(persenter!=null){
           persenter.unAttachView();
        }
        super.onDestroy();
    }
}
