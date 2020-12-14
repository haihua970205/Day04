package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;

import com.example.day04.model.tongpao.TPAssociData;
import com.example.day04.utils.TxtUtils;

import java.util.List;

public class AssociAdapter extends BaseAdapter {

    private Context context;
    public AssociAdapter(Context context, List mData) {
        super(context, mData);
        this.context = context;
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_associ_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TPAssociData.DataBean.ListBean bean = (TPAssociData.DataBean.ListBean) data;
        ImageView logo = (ImageView) vh.getViewById(R.id.logo);
        TextView fullName = (TextView) vh.getViewById(R.id.fullName);
        TextView type = (TextView) vh.getViewById(R.id.type);
        TextView note = (TextView) vh.getViewById(R.id.note);
        ImageView attachment = (ImageView) vh.getViewById(R.id.attachment);
        TextView createuserid = (TextView) vh.getViewById(R.id.createuserid);
        TextView createtime = (TextView) vh.getViewById(R.id.createtime);

        TxtUtils.setTextView(fullName,bean.getFullName());
        TxtUtils.setImageView(context,logo,bean.getLogo());
        TxtUtils.setTextView(type,bean.getType());
        TxtUtils.setTextView(note,bean.getNote());
        TxtUtils.setTextView(createuserid,bean.getCreateuserid());
        TxtUtils.setTextView(createtime,bean.getCreatetime());

        //网址简单处理
        String url = bean.getAttachment();
        String[] split = url.split("https");
        if (split.length>1){
            TxtUtils.setImageView(context,attachment,"https"+split[split.length-1]);
        }else {
            TxtUtils.setImageView(context,attachment,bean.getAttachment());
        }


    }
    /*<!--    countUser : 403
            * countActivity : 4
            * logo : https://tpcdn.whfpsoft.com:443/File/Api/Team/20191212/6670078354343094.jpg
            * fullName : 北京汉学会
        * type : 商业
        * province : 北京
        * city : 东城区
        * note : 北京汉学会，全称北京汉文化学习研讨会，以汉本位思想为根本，致力于华夏文明复兴
        * attachment : https://tpcdn.whfpsoft.com:443 https://tpcdn.whfpsoft.com:443/File/Api/Team/20191212/6971803377973547.jpg
            * createuserid : 180633
            * integralnumber : 4178
            * announcement : 新加入社团的同袍，加我微信18810642083拉你们进微信群
        * createtime : 2019-12-12 10:09:22
            * monthIntegrall : 610-->*/
}
