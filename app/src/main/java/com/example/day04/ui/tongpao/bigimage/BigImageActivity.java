package com.example.day04.ui.tongpao.bigimage;


import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.day04.R;
import com.example.day04.base.BaseActivity;
import com.example.day04.interfaces.tongpao.IBigImage;
import com.example.day04.persenter.tongpao.BigImagePersenter;
import com.example.day04.service.DownService;
import com.example.day04.utils.ImageLoader;
import com.example.day04.utils.TxtUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BigImageActivity extends BaseActivity<BigImagePersenter> implements IBigImage.View {

    int currentPos; //当前操作的图片的位置

    private ArrayList<String> urls = new ArrayList<>();
    private ImageAdapter adapter;
    private TextView txt_return;
    private TextView txt_page;
    private TextView txt_down;
    private ViewPager viewPager;
    private ServiceConnection connection;

    @Override
    protected int getLayout() {
        return R.layout.activity_bigimage;
    }

    @Override
    protected void initView() {
        txt_return = (TextView) findViewById(R.id.txt_return);
        txt_page = (TextView) findViewById(R.id.txt_page);
        txt_down = (TextView) findViewById(R.id.txt_down);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new ImageAdapter(this, urls);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
                updatePage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected BigImagePersenter createPersenter() {
        return new BigImagePersenter(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //data存放图片数据和当前操作下标
        if (intent != null && intent.hasExtra("data")) {
            Bundle bundle = intent.getBundleExtra("data");
            if (bundle != null) {
                ArrayList<String> list = bundle.getStringArrayList("urls");
                urls.addAll(list);
                currentPos = bundle.getInt("postion");//存放此下标

                updatePage();//调用修改Page页的方法

            }
        }
        viewPager.setCurrentItem(currentPos);//通过下标来改变集合里面的ViewPager的页面
        adapter.notifyDataSetChanged();
    }


    //更新当前选中的图片位置
    private void updatePage() {
        if (currentPos < urls.size()) {
            String page = currentPos + 1 + "/" + urls.size();
            TxtUtils.setTextView(txt_page, page);
            //判断是否有下载过
            String imagUrl = urls.get(currentPos);
            String[] arr = ImageLoader.splitUrl(imagUrl);
            String imgName = arr[1];
            String path = arr[2];
            File file = new File(path);
            if (file.exists()) {//如果存在 下载
                txt_down.setVisibility(View.GONE);//隐藏掉下载选项
            } else {//文件不存在 没下载
                txt_down.setVisibility(View.VISIBLE);//显示下载
            }
        } else {
            Toast.makeText(this, "当前图片的位置越界", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.txt_return, R.id.txt_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_return:
                finish();
                break;
            case R.id.txt_down:
                downloadImage();
                break;
        }
    }

    private void downloadImage() {
        //下载 安排
        String imgUrl = urls.get(currentPos);//获取 网址
        Intent intent = new Intent(this, DownService.class);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownService.MyBinder myBinder = (DownService.MyBinder) service;
                //判断是否有下载过
                String[] arr = ImageLoader.splitUrl(imgUrl);
                String imgName = arr[1];
                String path = arr[2];

                File file = new File(path);
                if (file.exists()) {//如果存在 下载
                    myBinder.downImg(imgUrl);
                    txt_down.setVisibility(View.GONE);//隐藏掉下载
                } else {//文件不存在 没下载
                    txt_down.setVisibility(View.VISIBLE);//显示下载
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, connection, Service.BIND_AUTO_CREATE);//Service.BIND_AUTO_CREATE  绑定时自动创建
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        connection = null;
    }

    /**
     * 下载成功回到
     *
     * @param path
     */
    @Override
    public void downReturn(String path) {
        Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT);
        Log.i("TAG", path);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}








