package com.example.day04.ui.tongpao.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.day04.R;
import com.example.day04.adapter.DiscoverAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseFragment;
import com.example.day04.interfaces.tongpao.IArtFrag;
import com.example.day04.model.tongpao.AssociActivity;
import com.example.day04.model.tongpao.HotFragment;
import com.example.day04.model.tongpao.RankActivity;
import com.example.day04.model.tongpao.RobeActivity;
import com.example.day04.model.tongpao.TPAssociData;
import com.example.day04.model.tongpao.TPDiscoverData;
import com.example.day04.model.tongpao.TPLevelData;
import com.example.day04.model.tongpao.TPMoneyData;
import com.example.day04.model.tongpao.TPNavigationBean;
import com.example.day04.model.tongpao.TPRobeBean;
import com.example.day04.model.tongpao.TPSquareBean;
import com.example.day04.persenter.tongpao.ArtPersenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ArticleFragment extends BaseFragment<ArtPersenter> implements IArtFrag.View {

    @BindView(R.id.tv_head_name)
    TextView tvHeadName;
    @BindView(R.id.iv_district_image1)
    ImageView ivDistrictImage1;
    @BindView(R.id.iv_district_image2)
    ImageView ivDistrictImage2;
    @BindView(R.id.iv_district_image3)
    ImageView ivDistrictImage3;
    @BindView(R.id.iv_district_name1)
    TextView ivDistrictName1;
    @BindView(R.id.iv_district_name2)
    TextView ivDistrictName2;
    @BindView(R.id.iv_district_name3)
    TextView ivDistrictName3;
    @BindView(R.id.tv_title_district)
    TextView tvTitleDistrict;
    @BindView(R.id.tv_more_district)
    TextView tvMoreDistrict;
    @BindView(R.id.mRec_district1)
    RecyclerView mRecDistrict1;
    @BindView(R.id.mTab_discover)
    TabLayout mTabDiscover;
    @BindView(R.id.mVp_discover)
    ViewPager mVpDiscover;


    private List<TPDiscoverData.DataBean> listDiscover = new ArrayList<>();
    private List<TPNavigationBean.DataBean> listNavigation = new ArrayList<>();
    private List<TPRobeBean.DataBean> listRobe = new ArrayList<>();
    private List<TPAssociData.DataBean.ListBean> listAssoci = new ArrayList<>();
    private List<TPLevelData.DataBean.ExpTopBean.ListBean> listLevel = new ArrayList<>();
    private List<TPSquareBean.DataBean.SignTopBean.ListBean> listSquare = new ArrayList<>();
    private List<TPMoneyData.DataBean.TongQianTopBean.ListBean> listMoney = new ArrayList<>();
    private DiscoverAdapter discoverAdapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_art;
    }

    @Override
    public void initView() {
        Glide.with(getContext()).load(R.mipmap.cup2).apply(new RequestOptions().circleCrop()).into(ivDistrictImage1);
        Glide.with(getContext()).load(R.mipmap.a8).apply(new RequestOptions().circleCrop()).into(ivDistrictImage2);
        Glide.with(getContext()).load(R.mipmap.cup4).apply(new RequestOptions().circleCrop()).into(ivDistrictImage3);

        //托把 中热门活动
        mRecDistrict1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        discoverAdapter = new DiscoverAdapter(getContext(), listDiscover);
        mRecDistrict1.setAdapter(discoverAdapter);

        //TabLayout 选中监听
        mTabDiscover.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.text_size_layout, null);

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中的
                textView.setTextSize(20);
//                textView.setTextColor(getActivity().getResources().getColor(R.color.color_RLB));
                textView.setText(tab.getText());
                tab.setCustomView(textView);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中的
                textView.setTextSize(14);
//                textView.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                textView.setText(tab.getText());
                tab.setCustomView(textView);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //复选的
            }
        });
    }

    @Override
    public ArtPersenter createPersenter() {
        return new ArtPersenter(this);
    }

    @Override
    public void initData() {
        //热门活动 拖把
        persenter.getDiscover();
        //类型 tablayout
        persenter.getNavigation();
        //袍子
        persenter.getRobe();
        //社团
        persenter.getAssoci();

        ///排行榜--等级榜
        persenter.getLevels();
        ////排行榜--签到榜
        persenter.getSquare();
        ////排行榜--土豪榜
        persenter.getMoney();
    }

    @Override
    public void getDiscoverReturn(TPDiscoverData result) {
        List<TPDiscoverData.DataBean> data = result.getData();
        listDiscover.addAll(data);
        discoverAdapter.notifyDataSetChanged();
    }

    @Override
    public void getNavigationReturn(TPNavigationBean result) {
        List<TPNavigationBean.DataBean> data = result.getData();
        listNavigation.addAll(data);
        if (listNavigation.size() > 0) {
            List<Fragment> fragmentList = new ArrayList<>();
            for (int i = 0; i < listNavigation.size(); i++) {
                HotFragment hotFragment = new HotFragment();
                hotFragment.setType(listNavigation.get(i).getType());
                fragmentList.add(hotFragment);
            }
            mVpDiscover.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

                @NonNull
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return fragmentList.size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    return listNavigation.get(position).getName();
                }
            });
            mTabDiscover.setupWithViewPager(mVpDiscover);
        }
    }

    @Override
    public void getRobeReturn(TPRobeBean result) {
        //袍子
        listRobe.addAll(result.getData());
    }

    @Override
    public void getAssociReturn(TPAssociData result) {
        //社团
        listAssoci.addAll(result.getData().getList());
    }

    @Override
    public void getLevelReturn(TPLevelData result) {
        listLevel.addAll(result.getData().getExpTop().getList());
    }

    @Override
    public void getSquareReturn(TPSquareBean result) {
        listSquare.addAll(result.getData().getSignTop().getList());
    }

    @Override
    public void getMoneyReturn(TPMoneyData result) {
        listMoney.addAll(result.getData().getTongQianTop().getList());
    }


    @OnClick({R.id.iv_district_image1, R.id.iv_district_image2, R.id.iv_district_image3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_district_image1:
                Intent intentRobe = new Intent(getContext(), RobeActivity.class);
                MyApp.getMap().put("listRobe", listRobe);
                startActivity(intentRobe);
                break;
            case R.id.iv_district_image2:
                Intent intentAssoci = new Intent(getContext(), AssociActivity.class);
                MyApp.getMap().put("listAssoci", listAssoci);
                startActivity(intentAssoci);
                break;
            case R.id.iv_district_image3:
                Intent intentRank = new Intent(getContext(), RankActivity.class);
                MyApp.getMap().put("listLevel", listLevel);
                MyApp.getMap().put("listSquare", listSquare);
                MyApp.getMap().put("listMoney", listMoney);
                startActivity(intentRank);
                break;
        }
    }

}
