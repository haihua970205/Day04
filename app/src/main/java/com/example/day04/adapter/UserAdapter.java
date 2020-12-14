package com.example.day04.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.utils.TxtUtils;
import com.youth.banner.Banner;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    public UserAdapter(Context context, List Data) {
        super(context, Data);
        this.context=context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_user_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPUserBean.DataBean bean= (TPUserBean.DataBean) data;
        ImageView head_img = (ImageView) vh.getViewById(R.id.iv_user_head_img);
        RelativeLayout mRl_user = (RelativeLayout) vh.getViewById(R.id.mRl_user);

        TextView nickname = (TextView) vh.getViewById(R.id.tv_user_nick_name);
        TextView province = (TextView) vh.getViewById(R.id.tv_user_province);
        Banner banner = (Banner) vh.getViewById(R.id.iv_user_filePath);


        TxtUtils.setRelativeColor(mContext,mRl_user,bean.getId());

        //控件在最上层使用
        head_img.bringToFront();

        //赋值    iv_user_head_img  头部图片
        TxtUtils.setImageView(mContext,head_img,bean.getHeadUrl());

        //赋值    tv_user_nick_name
        TxtUtils.setTextView(nickname,bean.getNickName());
        //赋值    tv_user_province
        TxtUtils.setTextView(province,bean.getProvince());

        //赋值    banner
        List<String> listBanner = new ArrayList<>();
        List<String> bannerTitle = new ArrayList<>();
        for (int i = 0; i < bean.getFileBeanList().size(); i++) {
            listBanner.add(bean.getFileBeanList().get(i).getFilePath());
            bannerTitle.add(bean.getNickName());
        }
        TxtUtils.setBannerView(banner,listBanner,bannerTitle);
    }
}
