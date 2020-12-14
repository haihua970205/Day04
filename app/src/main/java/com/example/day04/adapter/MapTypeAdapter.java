package com.example.day04.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.day04.R;
import com.example.day04.base.BaseAdapter;

import java.util.List;

public class MapTypeAdapter extends BaseAdapter<String> {

    public MapTypeAdapter(Context context, List<String> mData) {
        super(context, mData);
    }

    @Override
    protected int getLayout() {
        return R.layout.adapter_map_type_item;
    }

    @Override
    protected void bindData(String data, VH vh) {
        TextView type = (TextView) vh.getViewById(R.id.txt_type);
        type.setText(data);
    }
}
