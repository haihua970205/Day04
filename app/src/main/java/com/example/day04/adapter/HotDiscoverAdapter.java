package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.day04.R;
import com.example.day04.model.tongpao.TPNavTypeBean;
import com.jaeger.ninegridimageview.NineGridImageView;
import java.util.ArrayList;
import java.util.List;

public class HotDiscoverAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TPNavTypeBean.DataBean.ListBean> list;

    public HotDiscoverAdapter(Context context, List<TPNavTypeBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ViewHolderHot(View.inflate(context, R.layout.adapter_hot_item0, null));
        } else if (viewType == 1) {
            return new ViewHolderHot(View.inflate(context, R.layout.adapter_hot_item1, null));
        } else if (viewType == 2) {
            return new ViewHolderHot(View.inflate(context, R.layout.adapter_hot_item2, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TPNavTypeBean.DataBean.ListBean bean = list.get(position);

        //循环 获取九宫格数据源
        List<TPNavTypeBean.DataBean.ListBean.FilePathListBean> pathList = bean.getFilePathList();
        ArrayList<String> image = new ArrayList();
        for (int i = 0; i < pathList.size(); i++) {
            image.add(pathList.get(i).getFilePath());
        }

        int type = getItemViewType(position);
        ViewHolderHot vh = (ViewHolderHot) holder;
        if (type==0){
            vh.tv_hot_title.setText(bean.getTitle());
            vh.tv_hot_time.setText(bean.getCreateTime());

            //九宫格绑定
            NineGridAdapter nineGridAdapter = new NineGridAdapter();
            vh.nineGrid.setAdapter(nineGridAdapter);
            vh.nineGrid.setImagesData(image);
        }else if (type==1){
            vh.tv_hot_title.setText(bean.getTitle());
            vh.tv_hot_time.setText(bean.getCreateTime());

            //九宫格绑定
            NineGridAdapter nineGridAdapter = new NineGridAdapter();
            vh.nineGrid.setAdapter(nineGridAdapter);
            vh.nineGrid.setImagesData(image);
        }else if (type==2){
            vh.tv_hot_title.setText(bean.getTitle());
            vh.tv_hot_time.setText(bean.getCreateTime());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 0;
        } else if (position % 3 == 1) {
            return 1;
        } else if (position % 3 == 2){
            return 2;
        }
        return 0;
    }

    public static
    class ViewHolderHot extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_hot_title;
        public TextView tv_hot_time;
        public NineGridImageView nineGrid;

        public ViewHolderHot(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_hot_title = (TextView) rootView.findViewById(R.id.tv_hot_title);
            this.tv_hot_time = (TextView) rootView.findViewById(R.id.tv_hot_time);
            this.nineGrid = (NineGridImageView) rootView.findViewById(R.id.nineGrid);
        }

    }
}
