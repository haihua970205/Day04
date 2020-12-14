package com.example.day04.model.data;

import java.util.ArrayList;

public class EventBusObject {
    private int index;
    private ArrayList<String> image;
    private TPRecommendBean.DataBean.PostDetailBean data;

    public EventBusObject(){}

    public EventBusObject(int index, ArrayList<String> image, TPRecommendBean.DataBean.PostDetailBean data) {
        this.index = index;
        this.image = image;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
        this.image = image;
    }

    public TPRecommendBean.DataBean.PostDetailBean getData() {
        return data;
    }

    public void setData(TPRecommendBean.DataBean.PostDetailBean data) {
        this.data = data;
    }
}
