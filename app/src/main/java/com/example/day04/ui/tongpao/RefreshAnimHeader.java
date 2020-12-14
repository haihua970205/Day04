package com.example.day04.ui.tongpao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.example.day04.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * 自定义帧动画
 * @author wapchief
 * @date 2018/1/30
 */
@SuppressLint("RestrictedApi")
public class RefreshAnimHeader extends LinearLayout implements RefreshHeader {

    private ImageView mAnimationImg;
    private AnimationDrawable mAnimationDrawable;
    Context mContext;
    public RefreshAnimHeader(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }

    /**注意不能为null*/
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        start();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        stop();
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_loading_anim_refresh, this);
        mAnimationImg = (ImageView) view.findViewById(R.id.loading_anim_img);
//        mAnimationDrawable=(AnimationDrawable) ContextCompat.getDrawable(mContext,R.drawable.loading_anim_refresh);
        mAnimationDrawable.setOneShot(true);
        mAnimationImg.setImageDrawable(mAnimationDrawable);
    }


    /**开始*/
    protected void start() {
        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }
    /**结束*/
    protected void stop() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

}
