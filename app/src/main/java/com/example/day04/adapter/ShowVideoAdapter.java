package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.utils.TxtUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class ShowVideoAdapter extends BaseAdapter {

    private StandardGSYVideoPlayer mGs;
    private List<TPVideoBean.DataBean.ListBean> list;
    private int pos;

    public ShowVideoAdapter(Context context, List mData ) {
        super(context, mData);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_show_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
//        TPVideoBean.DataBean.ListBean bean = list.get(pos);

        TPVideoBean.DataBean.ListBean bean = (TPVideoBean.DataBean.ListBean) data;
        mGs = (StandardGSYVideoPlayer) vh.getViewById(R.id.mGs);
        mGs.setUp(bean.getVideoPath(),false,bean.getContent());


        //增加封面
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        TxtUtils.setImageView(mContext,imageView,bean.getCover());
        mGs.setThumbImageView(imageView);
        //增加title
        mGs.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        mGs.getBackButton().setVisibility(View.VISIBLE);
        //是否可以滑动调整
        mGs.setIsTouchWiget(true);
        //设置返回按键功能
        mGs.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //释放所有
                mGs.setVideoAllCallBack(null);
            }
        });
        mGs.startPlayLogic();
    }

    public void onDestroyVideo() {
        GSYVideoManager.releaseAllVideos();//释放所有视频
        mGs.setVideoAllCallBack(null);
    }
}
