package com.example.day04.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class TopicAdapter extends BaseAdapter {

    private Context context;
    public TopicAdapter(Context context, List mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPTopicBean.DataBean dataBean = (TPTopicBean.DataBean) data;
        int labelId = dataBean.getLabelId();

        ImageView imgIcon = (ImageView) vh.getViewById(R.id.img_head);
        TextView title = (TextView) vh.getViewById(R.id.txt_title);
        TextView count = (TextView) vh.getViewById(R.id.txt_count);
        TextView event = (TextView) vh.getViewById(R.id.txt_event);
        RelativeLayout rl = (RelativeLayout) vh.getViewById(R.id.RL_color);

        TxtUtils.setImageView(context,imgIcon,dataBean.getImageUrl());
        TxtUtils.setTextView(title,dataBean.getName());
        TxtUtils.setTextView(count,dataBean.getUseTime()+"人参与");
        TxtUtils.setTextView(event,"活动");
        TxtUtils.setRelativeColor(context,rl,labelId);
        //加载数据
    }

}
