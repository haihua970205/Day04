package com.example.day04.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;

import com.example.day04.model.tongpao.TPRobeBean;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class RobeAdapter extends BaseAdapter {

    private Context context;
    public RobeAdapter(Context context, List mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_robe_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPRobeBean.DataBean bean  = (TPRobeBean.DataBean) data;
        ImageView headUrl = (ImageView) vh.getViewById(R.id.headUrl);
        TextView userID = (TextView) vh.getViewById(R.id.userID);
        TextView nickName = (TextView) vh.getViewById(R.id.nickName);
        TextView age = (TextView) vh.getViewById(R.id.age);
        TextView sex = (TextView) vh.getViewById(R.id.sex);
        TextView city = (TextView) vh.getViewById(R.id.city);
        TextView socialTitle = (TextView) vh.getViewById(R.id.socialTitle);

        TxtUtils.setImageView(context,headUrl,bean.getHeadUrl());
        TxtUtils.setTextView(userID,"用户ID："+bean.getUserID());
        TxtUtils.setTextView(nickName,"网名："+bean.getNickName());
        TxtUtils.setTextView(age,"年龄："+bean.getAge());
        if (bean.getSex().equals("0")){
            TxtUtils.setTextView(sex,"性别："+"男");
        }else {
            TxtUtils.setTextView(sex,"性别："+"女");
        }

        TxtUtils.setTextView(city,"现居地："+bean.getProvince()+"\t"+bean.getCity());
        TxtUtils.setTextView(socialTitle,"个性签名："+bean.getSocialTitle());
    }
}
