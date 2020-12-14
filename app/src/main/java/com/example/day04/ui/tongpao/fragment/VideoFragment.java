package com.example.day04.ui.tongpao.fragment;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.VideoAdapter;
import com.example.day04.base.BaseFragment;
import com.example.day04.interfaces.tongpao.IRecommend;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

  /*  @BindView(R.id.RB_hotspot)
    RadioButton RBHotspot;
    @BindView(R.id.RB_find)
    RadioButton RBFind;
    @BindView(R.id.mFrame_void)
    FrameLayout mFrameVoid;
    @BindView(R.id.Rg)
    RadioGroup Rg;*/


    @BindView(R.id.btn_hot)
    Button btnHot;
    @BindView(R.id.btn_find)
    Button btnFind;
    @BindView(R.id.mRecycle_video)
    RecyclerView mRecycleVideo;
    private VideoItemHotFragment hotFragment;
    private VideoItemFindFragment findFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager manager;
    private List<TPVideoBean.DataBean.ListBean> listVideo;
    private VideoAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.fragment_void;
    }

    @Override
    public void initView() {
        mRecycleVideo.setLayoutManager(new GridLayoutManager(getActivity(),2));
        listVideo = new ArrayList<>();
        adapter = new VideoAdapter(getActivity(), listVideo);
        mRecycleVideo.setAdapter(adapter);
//        hotFragment = new VideoItemHotFragment();
//        findFragment = new VideoItemFindFragment();
    }

    @Override
    public RecommendPersenter createPersenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        persenter.getVideo();
//        initFragment();
    }

    @Override
    public void getBannerReturn(TPBannerBean result) {

    }

    @Override
    public void getTopicReturn(TPTopicBean result) {

    }

    @Override
    public void getRecommendReturn(TPRecommendBean result) {

    }

    @Override
    public void getUserReturn(TPUserBean result) {

    }

    @Override
    public void getVideoReturn(TPVideoBean result) {
        if (result.getData().getList().size() > 0) {
            listVideo.addAll(result.getData().getList());
            adapter.notifyDataSetChanged();
//            hotFragment.setList(listVideo);
//            findFragment.setList(listVideo);
        }

    }

   /* private void initFragment() {

        manager = getActivity().getSupportFragmentManager();
        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.mFrame_void, hotFragment)
                .add(R.id.mFrame_void, findFragment)
                .hide(findFragment).commit();
        //按钮的监听与fragment结合
        Rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (i) {
                    case R.id.RB_hotspot:
                        transaction.show(hotFragment).hide(findFragment);
                        break;
                    case R.id.RB_find:
                        transaction.show(findFragment).hide(hotFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }*/
}
