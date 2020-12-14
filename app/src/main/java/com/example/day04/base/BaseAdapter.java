package com.example.day04.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    protected Context mContext;
    List<D> mData;
    IListClick clik;

    public BaseAdapter(Context context,List<D> mData) {
        this.mData = mData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayout(),parent,false);
        VH vh = new VH(view);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clik!= null)
                    clik.itemClik(vh.getAdapterPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindData(mData.get(position),(VH)holder);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract int getLayout();

    protected abstract void bindData(D data,VH vh);

    public void addListClick(IListClick listClick){
        this.clik = listClick;
    }

    //定义回调接口
    public interface IListClick{
        void itemClik(int pos);
    }

    public class VH extends RecyclerView.ViewHolder {

        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        //查找item 的View
        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }
}


























