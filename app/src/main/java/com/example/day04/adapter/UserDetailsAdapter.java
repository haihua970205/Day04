package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class UserDetailsAdapter extends BaseAdapter {

    public UserDetailsAdapter(Context context, List mData) {
        super(context, mData);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_user_detail_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPUserBean.DataBean  bean = (TPUserBean.DataBean) data;
        TextView sex = (TextView) vh.getViewById(R.id.tv_sex);
        TextView birthday = (TextView) vh.getViewById(R.id.tv_birthday);
        TextView address = (TextView) vh.getViewById(R.id.tv_address);
        TextView skill = (TextView) vh.getViewById(R.id.tv_skill);

        TxtUtils.setTextView(sex,bean.getUserID()%2==0?"行别：男":"行别：女");
        TxtUtils.setTextView(birthday,"生日："+(2020-bean.getAge())+"-02-12 \t\t 星座：水平座");
        TxtUtils.setTextView(address,"所在地区："+bean.getProvince());
        TxtUtils.setTextView(skill,"暂无");
    }
}
