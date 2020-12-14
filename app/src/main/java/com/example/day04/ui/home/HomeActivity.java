package com.example.day04.ui.home;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.adapter.ProvinceAdapter;
import com.example.day04.R;
import com.example.day04.base.BaseActivity;
import com.example.day04.interfaces.home.IHome;
import com.example.day04.model.data.CityBean;
import com.example.day04.model.data.GradeBean;
import com.example.day04.persenter.home.HomePersenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<HomePersenter> implements IHome.View {

    private TextView txt_GPS;
    private Button btn_city;
    private Button btn_weather;
    private RecyclerView mRec_province;
    private RecyclerView mRec_city;
    private RecyclerView mRec_district;
    private IHome.Persenter persenter;
    private List<String> listGroup = new ArrayList<>();
    private List<String> listChlid = new ArrayList<>();
    private List<String> listWeated = new ArrayList<>();
    private ProvinceAdapter adapter_province;
    private ProvinceAdapter adapter_city;
    private ProvinceAdapter adapter_district;
    private List<CityBean.DataBean> listCityGroup = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        txt_GPS = findViewById(R.id.txt_GPS);
        btn_city = findViewById(R.id.btn_city);
        btn_weather = findViewById(R.id.btn_weather);
        mRec_province = findViewById(R.id.mRec_province);//省
        mRec_city = findViewById(R.id.mRec_city);//市
        mRec_district = findViewById(R.id.mRec_district);//区



        mRec_province.setLayoutManager(new LinearLayoutManager(this));
        adapter_province = new ProvinceAdapter(this, listGroup);
        mRec_province.setAdapter(adapter_province);

        mRec_city.setLayoutManager(new LinearLayoutManager(this));
        adapter_city = new ProvinceAdapter(this, listChlid);
        mRec_city.setAdapter(adapter_city);

        mRec_district.setLayoutManager(new LinearLayoutManager(this));
        adapter_district = new ProvinceAdapter(this, listWeated);
        mRec_district.setAdapter(adapter_district);


        adapter_province.setMyIOClickListener(new ProvinceAdapter.MyIOClickListener() {
            @Override
            public void IOClickListener(int pos) {


                listChlid.clear();
                String city = listCityGroup.get(pos).getCity();
                for (int i = 0; i < listCityGroup.size(); i++) {
                    if(city.equals(listCityGroup.get(i).getCity())){
                        listChlid.add(listCityGroup.get(i).getStation());
                    }
                }
                adapter_city.notifyDataSetChanged();
            }
        });

        adapter_city.setMyIOClickListener(new ProvinceAdapter.MyIOClickListener() {
            @Override
            public void IOClickListener(int pos) {

                listWeated.clear();
                String Station_code = listCityGroup.get(pos).getStation_code();
                persenter.getGradeReturn(Station_code);
            }
        });
    }

    @Override
    protected HomePersenter createPersenter() {
        return new HomePersenter(this);
    }

    @Override
    protected void initData() {
        persenter = new HomePersenter(this);
        persenter.getCity();
    }

    @Override
    public void getCityReturn(CityBean result) {
        List<CityBean.DataBean> data = result.getData();
        listCityGroup.addAll(data);
        for (int i = 0; i < listCityGroup.size(); i++) {
            if (!listGroup.contains(listCityGroup.get(i).getCity())){
                listGroup.add(listCityGroup.get(i).getCity());//获取 省级城市数据
            }
        }
        adapter_province.notifyDataSetChanged();
    }

    @Override
    public void getGradeReturn(GradeBean result) {
        GradeBean.DataBean data = result.getData();
        Log.e("111",data.getPM2_5()+data.getQuality());
        listWeated.add(data.getPollutions()+":"+data.getPM10()+"空气质量" +data.getQuality());
        adapter_district.notifyDataSetChanged();
    }
}
