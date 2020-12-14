package com.example.day04.map;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.model.LatLng;
import com.example.day04.R;
import com.mapapi.clusterutil.clustering.ClusterItem;


//ClusterItem接口的实现类
public class MyItem implements ClusterItem {
    LatLng mPosition;

    public MyItem(LatLng position) {
        mPosition = position;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(R.mipmap.ai);
    }
}