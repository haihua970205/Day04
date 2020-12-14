package com.example.day04.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.EventBusObject;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.ui.tongpao.bigimage.BigImageActivity;
import com.example.day04.utils.DateUtils;
import com.example.day04.utils.ImageLoader;
import com.example.day04.utils.TxtUtils;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseAdapter {

    public RecommendAdapter(Context context, List mData) {
        super(context, mData);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_recommend_item;
    }


    @Override
    protected void bindData(Object data, VH vh) {
        TPRecommendBean.DataBean.PostDetailBean bean = (TPRecommendBean.DataBean.PostDetailBean) data;
        ArrayList<TPRecommendBean.DataBean.PostDetailBean.ImagesBean> images = (ArrayList<TPRecommendBean.DataBean.PostDetailBean.ImagesBean>) bean.getImages();
        ArrayList<String> imageUrl = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            imageUrl.add(images.get(i).getFilePath());
        }
        boundDataVh(bean,vh);//数据绑定

        NineGridImageView nineGrid = (NineGridImageView) vh.getViewById(R.id.nineGrid);

        nineGrid.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                ImageLoader.loadImage((String) o,imageView);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List list) {
                //点击查看大图
                super.onItemImageClick(context, index, list);

                Intent intent = new Intent(context, BigImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("postion",index);
                bundle.putStringArrayList("urls",imageUrl);
                intent.putExtra("data",bundle);
                context.startActivity(intent);
            }
        });
        nineGrid.setImagesData(imageUrl);
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
    private void boundDataVh(TPRecommendBean.DataBean.PostDetailBean bean, VH vh) {

        ImageView gead = (ImageView) vh.getViewById(R.id.image_head);
        ImageView mark = (ImageView) vh.getViewById(R.id.image_mark);
        TextView title = (TextView) vh.getViewById(R.id.txt_nickName);
        TextView labels = (TextView) vh.getViewById(R.id.txt_labels);
        TextView count = (TextView) vh.getViewById(R.id.txt_content);
        TextView txtTime = (TextView) vh.getViewById(R.id.txt_time);
        TextView attention = (TextView) vh.getViewById(R.id.txt_attention);
        TextView love = (TextView) vh.getViewById(R.id.cb_love);
        TextView message = (TextView) vh.getViewById(R.id.cb_message);

        TxtUtils.setTextView(title,bean.getNickName());
        TxtUtils.setTextView(labels,bean.getLabels());
        TxtUtils.setTextView(count,bean.getContent(),true);
        TxtUtils.setTextView(attention,"+关注");


        TxtUtils.setTextView(love,bean.getLikeNumber());
        TxtUtils.setTextView(message,bean.getCommentNumber());

        /*TxtUtils.setCheckBox(love,bean.getLikeNumber());
        TxtUtils.setCheckBox(message,bean.getCommentNumber());*/

        TxtUtils.setImageView(mContext,gead,bean.getHeadUrl());
        TxtUtils.setImageView(mContext,mark,R.mipmap.head);

        Long time = DateUtils.getDateToTime(bean.getCreateTime(),null);
        String date = DateUtils.getStandardDate(time);
        TxtUtils.setTextView(txtTime,date);
    }
}