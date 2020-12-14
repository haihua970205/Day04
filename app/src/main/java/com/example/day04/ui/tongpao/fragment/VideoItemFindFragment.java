package com.example.day04.ui.tongpao.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.ProvinceAdapter;
import com.example.day04.adapter.ShowVideoAdapter;
import com.example.day04.adapter.VideoAdapter;
import com.example.day04.base.BaseAdapter;
import com.example.day04.base.BaseFragment;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoItemFindFragment extends BaseFragment<RecommendPersenter> {

    @BindView(R.id.mRec_find)
    RecyclerView mRecFind;
    private List<TPVideoBean.DataBean.ListBean> list = new ArrayList<>();
    private VideoAdapter adapter;

    public void setList(List<TPVideoBean.DataBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public int getLayout() {
        return R.layout.find_layout;
    }

    @Override
    public void initView() {
        mRecFind.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new VideoAdapter(getActivity(), list);
        mRecFind.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                videoPopup(pos);
            }
        });
    }

    @Override
    public RecommendPersenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {
        adapter.notifyDataSetChanged();
    }

    private void videoPopup(int pos) {
        View view = View.inflate(getActivity(),R.layout.popup_video_layout,null);
        PopupWindow window = new PopupWindow(view, -1, -2);
        window.showAtLocation(mRecFind, Gravity.CENTER,0,0);

        RecyclerView mRec_video = view.findViewById(R.id.mRec_popup_video);
        mRec_video.setLayoutManager(new LinearLayoutManager(getActivity()));
        ShowVideoAdapter videoAdapter = new ShowVideoAdapter(getActivity(), list);
        mRec_video.setAdapter(videoAdapter);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                videoAdapter.onDestroyVideo();
            }
        });
    }
}
