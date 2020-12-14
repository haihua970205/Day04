package com.example.day04.model.tongpao;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.AssociAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseActivity;
import com.example.day04.base.BasePersenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AssociActivity extends BaseActivity {

    @BindView(R.id.mRec_associ)
    RecyclerView mRecAssoci;
    private List<TPAssociData.DataBean.ListBean> listAssoci = new ArrayList<>();
    private AssociAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_associ;
    }

    @Override
    protected void initView() {
        mRecAssoci.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssociAdapter(this, listAssoci);
        mRecAssoci.setAdapter(adapter);
    }

    @Override
    protected BasePersenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {
        List<TPAssociData.DataBean.ListBean> associ = (List<TPAssociData.DataBean.ListBean>) MyApp.getMap().get("listAssoci");
        listAssoci.addAll(associ);
        adapter.notifyDataSetChanged();
    }

}