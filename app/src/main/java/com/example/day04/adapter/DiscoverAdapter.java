package com.example.day04.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.tongpao.TPDiscoverData;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class DiscoverAdapter extends BaseAdapter {

    private Context context;
    public DiscoverAdapter(Context context, List mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_discover_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPDiscoverData.DataBean bean = (TPDiscoverData.DataBean) data;
        ImageView picture1 = (ImageView) vh.getViewById(R.id.iv_district_picture1);
        ImageView picture2 = (ImageView) vh.getViewById(R.id.iv_district_picture2);
        TextView title = (TextView) vh.getViewById(R.id.tv_discover_title);
        ImageView gs = (ImageView) vh.getViewById(R.id.iv_district_gs);
        TextView address = (TextView) vh.getViewById(R.id.iv_district_address);
        TextView time = (TextView) vh.getViewById(R.id.iv_district_time);
        TxtUtils.setImageView(context,picture1,bean.getCover());
        TxtUtils.setImageView(context,picture2,R.mipmap.ai);
        TxtUtils.setTextView(title,bean.getTitle());
        TxtUtils.setImageView(context,gs,R.mipmap.ai);
        TxtUtils.setTextView(address,bean.getLocation());
        TxtUtils.setTextView(time,bean.getStartTime());
    }
}
/**
 * isProperieter : 0
 * onLineActivityID : 1057
 * title : 礼衣华夏汉服超模大赛西北赛区-信阳赛点
 * cover : https://tpcdn.whfpsoft.com:443/File/activity/20200727/c80d61031fc3673a5387ca84f188ebc2.jpg
 * coverStr :
 * location : 信阳
 * state : 1
 * startTime : 2020-08-23 00:00
 * endTime : 2020-08-23 23:59
 * applyCutOffTime : 2020-08-21 23:59*/
