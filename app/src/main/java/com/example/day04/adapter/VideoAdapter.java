package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.utils.ImageLoaderUtil;
import com.example.day04.utils.TxtUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class VideoAdapter extends BaseAdapter {

    private StandardGSYVideoPlayer mGs;

    public VideoAdapter(Context context, List mData) {
        super(context, mData);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_video_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPVideoBean.DataBean.ListBean bean = (TPVideoBean.DataBean.ListBean) data;
        ImageView frame = (ImageView) vh.getViewById(R.id.void_frame);
        TextView title = (TextView) vh.getViewById(R.id.void_title);
        ImageView head = (ImageView) vh.getViewById(R.id.video_head);
        TextView name = (TextView) vh.getViewById(R.id.video_name);
        RadioButton love = (RadioButton) vh.getViewById(R.id.video_love);
        TextView count = (TextView) vh.getViewById(R.id.video_count);

        TxtUtils.setImageView(mContext,frame,bean.getCover());
        TxtUtils.setTextView(title,bean.getContent());
        Glide.with(mContext).load(bean.getHeadUrl()).apply(new RequestOptions().circleCrop()).into(head);
        TxtUtils.setTextView(name,bean.getNickName());
        TxtUtils.setTextView(count,bean.getLikeNumber());
    }
}
