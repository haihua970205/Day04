package com.example.day04.ui.tongpao.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.UserAdapter;
import com.example.day04.base.BaseFragment;
import com.example.day04.interfaces.tongpao.IRecommend;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;
import com.example.day04.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HeadCoordFragment extends BaseFragment<RecommendPersenter> implements IRecommend.View {

    @BindView(R.id.iv_find_image1)
    ImageView ivFindImage1;
    @BindView(R.id.iv_find_image2)
    ImageView ivFindImage2;
    @BindView(R.id.iv_find_image3)
    ImageView ivFindImage3;
    @BindView(R.id.tv_title_find)
    TextView tvTitleFind;
    @BindView(R.id.tv_more_find)
    TextView tvMoreFind;
    @BindView(R.id.mRec_find)
    RecyclerView mRecFind;

    private List<TPUserBean.DataBean> listUser = new ArrayList<>();
    private UserAdapter userAdapter;


    @Override
    public int getLayout() {
        return R.layout.appbarlayout_head;
    }

    @Override
    public void initView() {
        mRecFind.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        userAdapter = new UserAdapter(getContext(), listUser);
        mRecFind.setAdapter(userAdapter);
    }

    @Override
    public RecommendPersenter createPersenter() {
        return new RecommendPersenter(this);
    }

    @Override
    public void initData() {
        persenter.getUser();
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
        listUser.addAll(result.getData());
        if (listUser.size()>0){
            upDataVH(listUser);
            userAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getVideoReturn(TPVideoBean result) {

    }

    private void upDataVH(List<TPUserBean.DataBean> listUser) {
        List<TPUserBean.DataBean.FileBeanListBean> fileBeanList = listUser.get(0).getFileBeanList();
        TxtUtils.setImageView(getActivity(),ivFindImage1,fileBeanList.get(0).getFilePath());
        TxtUtils.setImageView(getActivity(),ivFindImage2,fileBeanList.get(1).getFilePath());
        TxtUtils.setImageView(getActivity(),ivFindImage3,fileBeanList.get(3).getFilePath());
    }
}
