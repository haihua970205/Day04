package com.example.day04.ui.tongpao.userFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.adapter.UserDetailsAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseFragment;
import com.example.day04.base.BasePersenter;
import com.example.day04.model.data.TPUserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MaterialFragment extends BaseFragment {

    @BindView(R.id.mRec_user_material)
    RecyclerView mRecUserMaterial;
    private TPUserBean.DataBean bean = new TPUserBean.DataBean();
    private List<TPUserBean.DataBean> list = new ArrayList<>();
    private UserDetailsAdapter adapter;

    public void setBean(TPUserBean.DataBean bean) {
        this.bean = bean;

        list.add(bean);
        list.add(bean);
        list.add(bean);

        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getLayout() {
        return R.layout.user_material_fragment;
    }

    @Override
    public void initView() {
        mRecUserMaterial.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserDetailsAdapter(getContext(), list);
        mRecUserMaterial.setAdapter(adapter);
    }

    @Override
    public BasePersenter createPersenter() {
        return null;
    }

    @Override
    public void initData() {

    }
}
