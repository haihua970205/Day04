package com.example.day04.interfaces.home;

import com.example.day04.interfaces.IBasePersenter;
import com.example.day04.interfaces.IBaseView;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.IModel;
import com.example.day04.model.data.CityBean;
import com.example.day04.model.data.GradeBean;

public interface IHome {

    //home 业务下 的V层接口
    interface View extends IBaseView {
        //获取 城市数据返回
        void getCityReturn(CityBean result);
        void getGradeReturn(GradeBean result);
    }

    //home 业务下 P层接口
    interface Persenter extends IBasePersenter<View> {
        void getCity();
        void getGradeReturn(String station_code);
    }

    //home 业务 下 model层 接口
    interface Model extends IModel {
        void getCity(ICallBack ICallBack);
        void getGradeReturn(String station_code, ICallBack ICallBack);
    }

}
