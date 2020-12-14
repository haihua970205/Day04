package com.example.day04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import cn.jzvd.JzvdStd;

public class TextjzVeoid extends AppCompatActivity {

    private JzvdStd mJzplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textjz_veoid);
        initView();
    }

    private void initView() {
        mJzplay = (JzvdStd) findViewById(R.id.jzplay);
        mJzplay.setUp("https://tpcdn.whfpsoft.com:443/File/video/20200804/dd3176d4f1d9a0147d47ed028f2842b2.mp4","text饺子");
       Picasso.with(this).load("https://tpcdn.whfpsoft.com:443/File/cover/20200804/709bda142986052cbef22d79f1cb4420.jpeg")
                .into(mJzplay.posterImageView);
    }

}