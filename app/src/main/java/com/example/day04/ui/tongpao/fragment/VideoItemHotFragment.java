package com.example.day04.ui.tongpao.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.ShowVideoAdapter;
import com.example.day04.adapter.VideoAdapter;
import com.example.day04.base.BaseAdapter;
import com.example.day04.base.BaseFragment;
import com.example.day04.model.data.TPVideoBean;
import com.example.day04.persenter.tongpao.RecommendPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoItemHotFragment extends BaseFragment<RecommendPersenter> {

    @BindView(R.id.mRec_hot)
    RecyclerView mRecHot;

    private VideoAdapter adapter;
    private List<TPVideoBean.DataBean.ListBean> list = new ArrayList<>();

    public void setList(List<TPVideoBean.DataBean.ListBean> list) {
        this.list = list;
    }

    @Override
    public int getLayout() {
        return R.layout.hot_layout;
    }

    @Override
    public void initView() {
        mRecHot.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new VideoAdapter(getActivity(), list);
        mRecHot.setAdapter(adapter);

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
        View view = null;
        if (view == null){
            view = View.inflate(getActivity(),R.layout.popup_video_layout,null);
        }
        PopupWindow window = null;
        if (window==null){
            window = new PopupWindow(view, -1, -2);
        }
        window.showAtLocation(mRecHot, Gravity.CENTER,0,0);


        ShowVideoAdapter videoAdapter = null;
        if (videoAdapter==null){

            List<TPVideoBean.DataBean.ListBean> beanList = new ArrayList<>();
            for (int i = 0; i < list.size()-pos-1; i++) {
                beanList.add(list.get(i+pos));
            }

            RecyclerView mRec_video = view.findViewById(R.id.mRec_popup_video);
            mRec_video.setLayoutManager(new LinearLayoutManager(getActivity()));
            videoAdapter = new ShowVideoAdapter(getActivity(),beanList);
            mRec_video.setAdapter(videoAdapter);
        }

        ShowVideoAdapter finalVideoAdapter = videoAdapter;
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                finalVideoAdapter.onDestroyVideo();
            }
        });
    }
}
