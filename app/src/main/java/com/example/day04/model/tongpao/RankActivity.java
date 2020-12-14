package com.example.day04.model.tongpao;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.RankAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseActivity;
import com.example.day04.base.BasePersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RankActivity extends BaseActivity {


    @BindView(R.id.mRec_rank)
    RecyclerView mRecRank;
    @BindView(R.id.level)
    RadioButton level;
    @BindView(R.id.square)
    RadioButton square;
    @BindView(R.id.money)
    RadioButton money;
    @BindView(R.id.Rg)
    RadioGroup Rg;


    private List<TPLevelData.DataBean.ExpTopBean.ListBean> listLevel = new ArrayList<>();
    private List<TPSquareBean.DataBean.SignTopBean.ListBean> listSquare = new ArrayList<>();
    private List<TPMoneyData.DataBean.TongQianTopBean.ListBean> listMoney = new ArrayList<>();
    private List<ListBean> list = new ArrayList<>();
    private RankAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initView() {
        mRecRank.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RankAdapter(this, list);
        mRecRank.setAdapter(adapter);

    }

    @Override
    protected BasePersenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {
        List<TPLevelData.DataBean.ExpTopBean.ListBean> levels = (List<TPLevelData.DataBean.ExpTopBean.ListBean>) MyApp.getMap().get("listLevel");
        List<TPSquareBean.DataBean.SignTopBean.ListBean> squares = (List<TPSquareBean.DataBean.SignTopBean.ListBean>) MyApp.getMap().get("listSquare");
        List<TPMoneyData.DataBean.TongQianTopBean.ListBean> moneys = (List<TPMoneyData.DataBean.TongQianTopBean.ListBean>) MyApp.getMap().get("listMoney");

        listLevel.addAll(levels);
        listSquare.addAll(squares);
        listMoney.addAll(moneys);

        getLeverData();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.Rg)
    public void onClick() {
        Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getId() ==  R.id.level){
                    getLeverData();
                    adapter.notifyDataSetChanged();
                }else if(group.getId() == R.id.square){
                    getSquareData();
                    adapter.notifyDataSetChanged();
                }else if (group.getId() == R.id.money){
                    getMoneyData();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getLeverData() {
        list.clear();
        for (int i = 0; i < listLevel.size(); i++) {
            TPLevelData.DataBean.ExpTopBean.ListBean bean = listLevel.get(0);
            ListBean listBean = new ListBean();
            listBean.setHeadUrl(bean.getHeadUrl());
            listBean.setUserID(bean.getUserID());
            listBean.setLevel(bean.getLevel());
            listBean.setNickName(bean.getNickName());
            listBean.setSex(bean.getSex());
            listBean.setAge(bean.getAge());
            listBean.setCity(bean.getCity());
            listBean.setProvince(bean.getProvince());
            listBean.setExpScore(bean.getExpScore());
            list.add(listBean);
        }
    }

    private void getSquareData() {
        list.clear();
        for (int i = 0; i < listSquare.size(); i++) {
            TPLevelData.DataBean.ExpTopBean.ListBean bean = listLevel.get(0);
            ListBean listBean = new ListBean();
            listBean.setHeadUrl(bean.getHeadUrl());
            listBean.setUserID(bean.getUserID());
            listBean.setLevel(bean.getLevel());
            listBean.setNickName(bean.getNickName());
            listBean.setSex(bean.getSex());
            listBean.setAge(bean.getAge());
            listBean.setCity(bean.getCity());
            listBean.setProvince(bean.getProvince());
            listBean.setExpScore(bean.getExpScore());
            list.add(listBean);
        }
    }

    private void getMoneyData() {
        list.clear();
        for (int i = 0; i < listMoney.size(); i++) {
            TPLevelData.DataBean.ExpTopBean.ListBean bean = listLevel.get(0);
            ListBean listBean = new ListBean();
            listBean.setHeadUrl(bean.getHeadUrl());
            listBean.setUserID(bean.getUserID());
            listBean.setLevel(bean.getLevel());
            listBean.setNickName(bean.getNickName());
            listBean.setSex(bean.getSex());
            listBean.setAge(bean.getAge());
            listBean.setCity(bean.getCity());
            listBean.setProvince(bean.getProvince());
            listBean.setExpScore(bean.getExpScore());
            list.add(listBean);
        }
    }

}