package com.example.day04.model.tongpao;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.RobeAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseActivity;
import com.example.day04.base.BasePersenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RobeActivity extends BaseActivity {

    @BindView(R.id.mRec_robe)
    RecyclerView mRecRobe;
    private List<TPRobeBean.DataBean> listRobe = new ArrayList<>();
    private RobeAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_robe;
    }

    @Override
    protected void initView() {
        mRecRobe.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RobeAdapter(this, listRobe);
        mRecRobe.setAdapter(adapter);

    }

    @Override
    protected BasePersenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {
        List<TPRobeBean.DataBean> robe = (List<TPRobeBean.DataBean>) MyApp.getMap().get("listRobe");
        listRobe.addAll(robe);
        adapter.notifyDataSetChanged();
    }
}