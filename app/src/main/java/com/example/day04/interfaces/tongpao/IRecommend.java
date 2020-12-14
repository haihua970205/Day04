package com.example.day04.interfaces.tongpao;

import com.example.day04.interfaces.IBasePersenter;
import com.example.day04.interfaces.IBaseView;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.IModel;
import com.example.day04.model.data.TPBannerBean;
import com.example.day04.model.data.TPRecommendBean;
import com.example.day04.model.data.TPTopicBean;
import com.example.day04.model.data.TPUserBean;
import com.example.day04.model.data.TPVideoBean;

public interface IRecommend {
    //tongpao 业务下 V层接口
    interface View extends IBaseView{
        //获取  数据 p -> v
        void getBannerReturn(TPBannerBean result);
        void getTopicReturn(TPTopicBean result);
        void getRecommendReturn(TPRecommendBean result);
        void getUserReturn(TPUserBean result);
        void getVideoReturn(TPVideoBean result);
//        void getDataReturn(Object object);
    }

    interface Persenter extends IBasePersenter<View>{
        void getBanner();
        void getTopic();
        void getRecommend();
        void getUser();
        void getVideo();
//        void getPersonal();
    }

    interface Model extends IModel{
        void getBanner(ICallBack callBack);
        void getTopic(ICallBack callBack);
        void getRecommend(ICallBack callBack);
        void getUser(ICallBack callBack);
        void getVideo(ICallBack callBack);
//        void getPersonal(ICallBack callBack);
    }
}
