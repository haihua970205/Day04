package com.example.day04.ui.tongpao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.day04.R;
import com.example.day04.adapter.RecommendAdapter;
import com.example.day04.adapter.TopicAdapter;
import com.example.day04.adapter.UserAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseAdapter;
import com.example.day04.base.BaseFragment;
import com.example.day04.interfaces.tongpao.IRecommend;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;
import com.example.day04.ui.tongpao.RefreshAnimHeader;
import com.example.day04.ui.tongpao.UserDetailsActivity;
import com.example.day04.ui.tongpao.bigimage.BigImageActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecommendFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

    @BindView(R.id.mBanner_rec)
    Banner mBannerRec;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_more)
    TextView txtMore;
    @BindView(R.id.mRec_talk)
    RecyclerView mRecTalk;
    @BindView(R.id.mRec_recommend)
    RecyclerView mRecRecommend;
    @BindView(R.id.mRec_hotuser)
    RecyclerView mRecHotuser;
    @BindView(R.id.txt_title1)
    TextView txtTitle1;
    @BindView(R.id.txt_more1)
    TextView txtMore1;
    @BindView(R.id.head_fragment)
    FrameLayout headFragment;
    @BindView(R.id.mCollToolbar)
    CollapsingToolbarLayout mCollToolbar;


    private List<TPBannerBean.DataBean.ListBean> listBanner = new ArrayList<>();
    private List<TPTopicBean.DataBean> listTopic = new ArrayList<>();
    private List<TPRecommendBean.DataBean.PostDetailBean> listRecommend = new ArrayList<>();
    private List<TPUserBean.DataBean> listUser = new ArrayList<>();
    private TopicAdapter topicAdapter;
    private RecommendAdapter recommendAdapter;
    private UserAdapter userAdapter;
    private HeadCoordFragment fragment;
    private FragmentTransaction hide;

    @Override
    public int getLayout() {
        return R.layout.fragment_recommend;
    }


    @Override
    public void initView() {

//        initSmart();

        fragment = new HeadCoordFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        hide = manager.beginTransaction()
                .add(R.id.head_fragment, fragment)
                .hide(fragment);
        hide.commit();

        //热门话题
        mRecTalk.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        topicAdapter = new TopicAdapter(getActivity(), listTopic);
        mRecTalk.setAdapter(topicAdapter);
        topicAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                View view = View.inflate(getContext(), R.layout.popup_mord, null);
                TextView title = view.findViewById(R.id.pop_title);
                title.setText(listTopic.get(pos).getDepict());
                PopupWindow window = new PopupWindow(view, ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(null);
                window.setOutsideTouchable(true);
                window.showAsDropDown(mRecTalk, -10, -10);
            }
        });

        //推荐数据
        mRecRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommendAdapter = new RecommendAdapter(getActivity(), listRecommend);
        mRecRecommend.setAdapter(recommendAdapter);

//        DisplayImageOptions

        //推荐用户
        mRecHotuser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        userAdapter = new UserAdapter(getContext(), listUser);
        mRecHotuser.setAdapter(userAdapter);
        userAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                Intent intent = new Intent(getContext(), UserDetailsActivity.class);
                MyApp.getMap().put("bean",(TPUserBean.DataBean)listUser.get(pos));
                getActivity().startActivity(intent);
            }
        });


    }

    private void initSmart() {
        //初始化header
        RefreshAnimHeader refreshAnimHeader = new RefreshAnimHeader(getActivity());
//        setHeader(refreshAnimHeader);//给头部设置自定义刷新

//        smart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                initData();
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                listTopic.clear();
//                listRecommend.clear();
//                listUser.clear();
//                initData();
//            }
//        });
    }

    //设置刷新的header风格
//    private void setHeader(RefreshHeader header) {
//        if (smart.isRefreshing()) {
//            smart.finishRefresh();
//        }
//        smart.setRefreshHeader(header);
//    }

    @Override
    public RecommendPersenter createPersenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        persenter.getBanner();//推荐Banner
        persenter.getTopic();//热门话题
        persenter.getRecommend();//推荐数据
        persenter.getUser();//热门用户

        hide.show(fragment);
    }

    @Override
    public void getBannerReturn(TPBannerBean result) {
        listBanner.clear();
        listBanner.addAll(result.getData().getList());
        if (listBanner.size() < 0) return;
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add(result.getData().getList().get(0).getBanner());
            strings1.add(result.getData().getList().get(0).getName());
        }
        mBannerRec.setImages(strings)
                .setBannerTitles(strings1)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setBannerAnimation(Transformer.ForegroundToBackground)
                .setDelayTime(3000)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getActivity(), "动画效果", Toast.LENGTH_SHORT).show();
                    }
                })
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).start();
    }

    @Override
    public void getTopicReturn(TPTopicBean result) {
        listTopic.clear();
        listTopic.addAll(result.getData());
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public void getRecommendReturn(TPRecommendBean result) {
        listRecommend.clear();
        listRecommend.add(result.getData().getPostDetail());
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUserReturn(TPUserBean result) {
        listUser.clear();
        listUser.addAll(result.getData());
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void getVideoReturn(TPVideoBean result) {

    }
}
