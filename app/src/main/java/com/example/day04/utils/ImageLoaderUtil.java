package com.example.day04.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Display;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.day04.R;
import com.example.day04.app.MyApp;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;




public class ImageLoaderUtil {


    public static ImageLoader getImageLoader(Context context) {
        return ImageLoader.getInstance();
    }

    //url 图片地址
    public static void loadImage(String url, ImageView img){
        //用key为image的值的时候来判断当前时无图还有有图模式
        if(img != null){
            Glide.with(MyApp.app).load(url).into(img);
        }
    }
    //url 图片地址
    public static void loadImage(int url, ImageView img){
        //用key为image的值的时候来判断当前时无图还有有图模式
        if(SpUtils.getInstance().getBoolean("image") && img != null){
            Glide.with(MyApp.app).load(url).into(img);
        }
    }

    public static DisplayImageOptions getPhotoImageOption() {
        Integer extra = 1;
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.mipmap.cup1)//加载空Uri的图像
                .showImageOnFail(R.mipmap.cup2)//加载失败图片
                .showImageOnLoading(R.mipmap.cup4)//加载时图片
                .extraForDownloader(extra)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    public static void displayImage(Context context, ImageView imageView, String url) {
        getImageLoader(context).displayImage(url, imageView);
    }

    public static void displayImage(Context context, ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {
        getImageLoader(context).displayImage(url, imageView, options, listener);
    }
}
