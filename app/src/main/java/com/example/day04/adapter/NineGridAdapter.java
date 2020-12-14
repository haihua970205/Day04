package com.example.day04.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.day04.ui.tongpao.bigimage.BigImageActivity;
import com.example.day04.utils.TxtUtils;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;
/***********九宫格适配器***********/
public class NineGridAdapter extends NineGridImageViewAdapter {

    @Override
    protected void onDisplayImage(Context context, ImageView imageView, Object o) {
        TxtUtils.setImageView(context,imageView,(String)o);
    }

    @Override
    protected void onItemImageClick(Context context, int index, List list) {
        super.onItemImageClick(context, index, list);
        ArrayList<String> image = new ArrayList<>();
        image.addAll(list);
        //点击查看大图
        Intent intent = new Intent(context, BigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("postion",index);
        bundle.putStringArrayList("urls",image);
        intent.putExtra("data",bundle);
        context.startActivity(intent);
    }
}
