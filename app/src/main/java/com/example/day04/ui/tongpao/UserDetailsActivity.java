package com.example.day04.ui.tongpao;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day04.R;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseActivity;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;
import com.example.day04.ui.tongpao.userFragment.DynamicFragment;
import com.example.day04.ui.tongpao.userFragment.MaterialFragment;
import com.example.day04.utils.TxtUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailsActivity extends BaseActivity<RecommendPersenter> {

    @BindView(R.id.iv_user_back)
    ImageView ivUserBack;
    @BindView(R.id.iv_user_head)
    ImageView ivUserHead;
    @BindView(R.id.tv_user_title)
    TextView tvUserTitle;
    @BindView(R.id.rb_user_love)
    CheckBox rbUserLove;
    @BindView(R.id.rb_user_message)
    CheckBox rbUserMessage;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.tv_essay)
    TextView tvEssay;
    @BindView(R.id.tv_user_keep1)
    TextView tvUserKeep1;
    @BindView(R.id.tv_user_keep2)
    TextView tvUserKeep2;
    @BindView(R.id.tv_user_keep3)
    TextView tvUserKeep3;
    @BindView(R.id.tv_user_name1)
    TextView tvUserName1;
    @BindView(R.id.tv_user_name2)
    TextView tvUserName2;
    @BindView(R.id.tv_user_name3)
    TextView tvUserName3;
    @BindView(R.id.mTab_user)
    TabLayout mTabUser;
    @BindView(R.id.mVp_user)
    ViewPager mVpUser;


    private TPUserBean.DataBean bean;


    @Override
    protected int getLayout() {
        return R.layout.activity_user_details;
    }

    @Override
    protected void initView() {
        bean = (TPUserBean.DataBean) MyApp.getMap().get("bean");

        TxtUtils.setImageView(this, ivUserBack, bean.getFileBeanList().get(0).getFilePath());
        Glide.with(this).load(bean.getHeadUrl()).apply(new RequestOptions().circleCrop()).into(ivUserHead);
        TxtUtils.setTextView(tvUserTitle, bean.getNickName());
        TxtUtils.setTextView(tvEssay, "\t分享一切有趣好玩的历史知识，爱发现可爱表情包的历史爱好者~\t");
        TxtUtils.setTextView(tvUserKeep1, bean.getLevel());
        TxtUtils.setTextView(tvUserKeep2, bean.getId());
        TxtUtils.setTextView(tvUserKeep3, bean.getUserID());

        MaterialFragment materialFragment = new MaterialFragment();
        materialFragment.setBean(bean);

        List<Fragment> list = new ArrayList<>();
        list.add(materialFragment);
        list.add(new MaterialFragment());
        list.add(new DynamicFragment());
        list.add(new DynamicFragment());
        list.add(new DynamicFragment());


        mVpUser.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] title = {"资料", "动态", "活动", "社团", "文章"};

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        mTabUser.setupWithViewPager(mVpUser);



        rbUserLove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    TxtUtils.setTextView(tvUserKeep1, bean.getLevel()+1);
                }else {
                    TxtUtils.setTextView(tvUserKeep1, bean.getLevel()-1);
                }
            }
        });
    }

    @Override
    protected RecommendPersenter createPersenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}