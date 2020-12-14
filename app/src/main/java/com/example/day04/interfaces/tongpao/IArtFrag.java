package com.example.day04.interfaces.tongpao;

import com.example.day04.base.BasePersenter;
import com.example.day04.interfaces.IBasePersenter;
import com.example.day04.interfaces.IBaseView;
import com.example.day04.interfaces.ICallBack;
import com.example.day04.interfaces.IModel;
import com.example.day04.model.tongpao.TPAssociData;
import com.example.day04.model.tongpao.TPDiscoverData;
import com.example.day04.model.tongpao.TPLevelData;
import com.example.day04.model.tongpao.TPMoneyData;
import com.example.day04.model.tongpao.TPNavigationBean;
import com.example.day04.model.tongpao.TPRobeBean;
import com.example.day04.model.tongpao.TPSquareBean;

public interface IArtFrag {
    interface View extends IBaseView{
        void getDiscoverReturn(TPDiscoverData result);
        void getNavigationReturn(TPNavigationBean result);
        void getRobeReturn(TPRobeBean result);
        void getAssociReturn(TPAssociData result);
        void getLevelReturn(TPLevelData result);
        void getSquareReturn(TPSquareBean result);
        void getMoneyReturn(TPMoneyData result);
    }
    interface Persenter extends IBasePersenter<View>{
        void getDiscover();
        void getNavigation();
        void getRobe();
        void getAssoci();
        void getLevels();
        void getSquare();
        void getMoney();
    }
    interface Model extends IModel {
        void getDiscover(ICallBack callBack);
        void getNavigation(ICallBack callBack);
        void getRobe(ICallBack callBack);
        void getAssoci(ICallBack callBack);
        void getLevels(ICallBack callBack);
        void getSquare(ICallBack callBack);
        void getMoney(ICallBack callBack);
    }
}
