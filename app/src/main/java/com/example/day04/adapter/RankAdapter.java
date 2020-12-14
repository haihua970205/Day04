package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;

import com.example.day04.model.tongpao.ListBean;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class RankAdapter extends BaseAdapter {

    private Context context;
    public RankAdapter(Context context, List mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_rank_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ListBean bean = (ListBean) data;
        ImageView headUrl = (ImageView) vh.getViewById(R.id.headUrl);
        TextView userID = (TextView) vh.getViewById(R.id.userID);
        TextView level = (TextView) vh.getViewById(R.id.level);
        TextView nickName = (TextView) vh.getViewById(R.id.nickName);
        TextView sex = (TextView) vh.getViewById(R.id.sex);
        TextView age = (TextView) vh.getViewById(R.id.age);
        TextView city = (TextView) vh.getViewById(R.id.city);
        TextView expScore = (TextView) vh.getViewById(R.id.expScore);

        TxtUtils.setImageView(context,headUrl,bean.getHeadUrl());
        TxtUtils.setTextView(userID,"用户ID："+bean.getUserID());
        TxtUtils.setTextView(level,"等级："+bean.getLevel());
        TxtUtils.setTextView(nickName,"网名："+bean.getNickName());
        if (bean.getSex().equals("0")){
            TxtUtils.setTextView(sex,"性别："+"男");
        }else {
            TxtUtils.setTextView(sex,"性别："+"女");
        }
        TxtUtils.setTextView(age,"年龄："+bean.getAge());

        TxtUtils.setTextView(city,"现居地："+bean.getProvince()+"\t"+bean.getCity());
        TxtUtils.setTextView(expScore,"目前分数："+bean.getExpScore());
    }
}
