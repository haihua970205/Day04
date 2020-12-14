package com.example.day04.model.tongpao;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.HotDiscoverAdapter;
import com.example.day04.base.BaseFragment;

import com.example.day04.net.HttpManager;
import com.example.day04.persenter.tongpao.ArtPersenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HotFragment extends BaseFragment<ArtPersenter> {

    @BindView(R.id.mRec_hot_discover)
    RecyclerView mRecHotDiscover;
    private int type;
    private List<TPNavTypeBean.DataBean.ListBean> listData ;
    private HotDiscoverAdapter hotDiscoverAdapter;

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_discover_hot;
    }

    @Override
    public void initView() {
        //发现列表
        mRecHotDiscover.setLayoutManager(new LinearLayoutManager(getContext()));
        listData = new ArrayList<>();
        hotDiscoverAdapter = new HotDiscoverAdapter(getContext(),listData);
        mRecHotDiscover.setAdapter(hotDiscoverAdapter);
    }

    @Override
    public ArtPersenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {
        HttpManager
                .getInstance()
                .getToangpao()
                .getNavTypeData(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TPNavTypeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TPNavTypeBean tpNavTypeBean) {
                        listData.addAll(tpNavTypeBean.getData().getList());
                        hotDiscoverAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("111",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
