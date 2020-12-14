package com.example.day04.layout.tongpao;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.util.List;

public abstract class NineGridLayout extends ViewGroup {
    public NineGridLayout(Context context){
        super(context);
    }
    public NineGridLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    //******************************其他代码省略**************************

    /**
     * 显示一张图片
     * @param imageView
     * @param url
     * @param parentWidth 父控件宽度
     * @return true 代表按照九宫格默认大小显示，false 代表按照自定义宽高显示
     */
    protected abstract boolean displayOneImage(RatioImageView imageView, String url, int parentWidth);

    protected abstract void displayImage(RatioImageView imageView, String url);

    /**
     * 点击图片时执行
     */
    protected abstract void onClickImage(int position, String url, List<String> urlList);
}
