package com.example.day04.map;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.day04.R;
import com.example.day04.adapter.MapTypeAdapter;
import com.example.day04.adapter.SuggestAdapter;
import com.example.day04.app.MyApp;
import com.example.day04.base.BaseAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.common.collect.MapMaker;
import com.mapapi.clusterutil.clustering.ClusterManager;
import com.mapapi.overlayutil.WalkingRouteOverlay;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MapBaiduActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_zhinan)
    ImageView ivZhinan;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.mRec_type_map)
    RecyclerView mRecTypeMap;
    @BindView(R.id.iv_nav)
    ImageView ivNav;
    @BindView(R.id.mNav_map)
    NavigationView mNavMap;
    @BindView(R.id.mDra_map)
    DrawerLayout mDraMap;

    //百度地图的数据操作
    BaiduMap baiduMap;
    //百度地图定位的类
    LocationClient locationClient;

    //指南针 传感器管理器
    SensorManager mSensorManager;

    int r = 300;

    /********************检索********************/
    SearchItemAdapter searchItemAdapter;
    List<PoiInfo> poiList;
    /********************检索********************/

    /*******************路径规划********************/
    RoutePlanSearch routePlanSearch;
    private TextView inputStart;
    private TextView inputEnd;
    private Button routePlan;


    /*******************路径规划********************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        initView();

        initMap();//百度地图的数据操作

        initLocation();//初始化位置
        initMapType();//切换地图类型
        initPoi();//初始化检索
        initRoutePlan();//初始化路径规划


        // 获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }


    private void initView() {
        btnSearch.setOnClickListener(this);
//        iv_nav.setOnClickListener(this);
    }

    private void initMap() {
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        //设置为普通类型的地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    /**********************8***定位************************/
    //防止每次定位都重新设置中心点和marker
//    private boolean isFirstLocation = true;
    //初始化定位
    private void initLocation() {
        //定位初始化
        locationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        // 打开Gps
        option.setOpenGps(true);
        // 设置坐标类型 MyApp里面默认的坐标类型
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(1000);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        //设置locationClientOption
        locationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        locationClient.start();
    }

    //地图定位的监听
    // BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口听
    //实现定位监听 位置一旦有所改变就会调用这个方法
    //可以在这个方法里面获取到定位之后获取到的一系列数据
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //mapView 销毁后不在处理新接收的位置
            if (bdLocation == null || mapView == null) {
                return;
            }
            /*//获取定位结果
            bdLocation.getTime();    //获取定位时间
            bdLocation.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            bdLocation.getLocType();    //获取定位类型
            bdLocation.getLatitude();    //获取纬度信息
            bdLocation.getLongitude();    //获取经度信息
            bdLocation.getRadius();    //获取定位精准度
            bdLocation.getAddrStr();    //获取地址信息
            bdLocation.getCountry();    //获取国家信息
            bdLocation.getCountryCode();    //获取国家码
            bdLocation.getCity();    //获取城市信息
            bdLocation.getCityCode();    //获取城市码
            bdLocation.getDistrict();    //获取区县信息
            bdLocation.getStreet();    //获取街道信息
            bdLocation.getStreetNumber();    //获取街道码
            bdLocation.getLocationDescribe();    //获取当前位置描述信息
            bdLocation.getPoiList();    //获取当前位置周边POI信息

            bdLocation.getBuildingID();    //室内精准定位下，获取楼宇ID
            bdLocation.getBuildingName();    //室内精准定位下，获取楼宇名称
            bdLocation.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息*/
            //经纬度
            float lat = (float) bdLocation.getLatitude();
            float lon = (float) bdLocation.getLongitude();

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
        }
    }


    /************************检索*********************/
    PoiSearch poiSearch;

    private void initPoi() {
        poiList = new ArrayList<>();
        searchItemAdapter = new SearchItemAdapter(this, poiList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchItemAdapter);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiSearchResultListener);

        searchItemAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                //点击条目进行定位
                PoiInfo poiInfo = poiList.get(pos);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(poiInfo.location);
                baiduMap.setMapStatus(status);
                drawCircle(poiInfo.location.latitude, poiInfo.location.longitude);//圆形
                addMark(poiInfo.location.latitude, poiInfo.location.longitude, poiInfo.getName());//圆心 图标
            }
        });
    }

    //地图类型切换
    private void initMapType() {
        List<String> listMapType = new ArrayList<>();
        listMapType.add("俯视图");
        listMapType.add("卫星图");
        listMapType.add("聚合");
        mRecTypeMap.setLayoutManager(new LinearLayoutManager(this));
        mRecTypeMap.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        MapTypeAdapter mapTypeAdapter = new MapTypeAdapter(this, listMapType);
        mRecTypeMap.setAdapter(mapTypeAdapter);
        mapTypeAdapter.notifyDataSetChanged();

        mapTypeAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                if (pos == 0) baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                if (pos == 1) baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//                if (pos == 2) initPolymerization(); //实现聚合定点
            }
        });
    }

    //****************聚合管理******************//*
    ClusterManager<MyItem> mClusterManager;

    private void initPolymerization() {
        //初始化点聚合管理类
        if (mClusterManager == null){
            mClusterManager = new ClusterManager<>(this, baiduMap);
        }

        // 添加Marker点
        if (poiList.size()>0){
            List<MyItem> items = new ArrayList<>();
            for (int i = 0; i < poiList.size(); i++) {
                PoiInfo poiInfo = poiList.get(i);
                LatLng llA = new LatLng(poiInfo.location.latitude, poiInfo.location.longitude);
                items.add(new MyItem(llA));

                addMark(poiInfo.location.latitude,poiInfo.location.longitude,poiInfo.getName());
            }
            mClusterManager.addItems(items);
        }
    }


    /*******************************路径规划*************************/
    private PlanNode startNode, endNode; //开始和结束的坐标点
    SuggestionSearch suggestionSearch; //地点检索的类
    SuggestAdapter suggestAdapter; //路径规划搜索出来的列表
    List<SuggestionResult.SuggestionInfo> suggestList; //地点检索的结果
    boolean isStart = true; //当前处理的是否是起点
    LatLng startLatLng ; //起点的经纬度

    //初始化路径规划
    private void initRoutePlan() {
        View view = mNavMap.getHeaderView(0);
        inputStart = view.findViewById(R.id.input_start);
        inputEnd = view.findViewById(R.id.input_end);
        routePlan = view.findViewById(R.id.btn_routePlan);
        RecyclerView recyNodes = view.findViewById(R.id.recy_nodes);

        routePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRoute();
            }
        });


        suggestionSearch = SuggestionSearch.newInstance();
        suggestList = new ArrayList<>();
        suggestAdapter = new SuggestAdapter(this, suggestList);
        recyNodes.setLayoutManager(new LinearLayoutManager(this));
        recyNodes.setAdapter(suggestAdapter);
        //设置监听地点检索
        suggestionSearch.setOnGetSuggestionResultListener(suggestionResultListener);

        inputStart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = true;
                    recyNodes.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听起点输入框的变化
        inputStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //起点输入改变以后获取对应的起点数据
                SuggestionSearchOption option = new SuggestionSearchOption();
                option.city("北京");
                option.citylimit(true);
                option.keyword(s.toString());
                suggestionSearch.requestSuggestion(option);
            }
        });
        //监听终点输入框的光标
        inputEnd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isStart = false;
                    recyNodes.setVisibility(View.VISIBLE);
                }
            }
        });
        //监听终点输入框的输入
        inputEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取终点框对应的数据
                SuggestionSearchOption option = new SuggestionSearchOption();
                option.city("北京");
                option.citylimit(true);
                option.keyword(s.toString());
                suggestionSearch.requestSuggestion(option);
            }
        });


        routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(routePlanResultListener);

        suggestAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClik(int pos) {
                SuggestionResult.SuggestionInfo suggestionInfo = suggestList.get(pos);
                if (isStart) {
                    inputStart.setText(suggestionInfo.getKey());
                    startLatLng = suggestionInfo.getPt();
                } else {
                    inputEnd.setText(suggestionInfo.getKey());
                }
            }
        });
    }

    /**
     * 地点检索监听
     */
    OnGetSuggestionResultListener suggestionResultListener = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            //地点检索结果
            suggestList.clear();
            suggestList.addAll(suggestionResult.getAllSuggestions());
            suggestAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 路径搜索的监听
     */
    OnGetRoutePlanResultListener routePlanResultListener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            Log.i("TAG", "onGetWalkingRouteResult");

            //创建一个路径规划的类
            WalkingRouteOverlay walkingRouteOverlay = new WalkingRouteOverlay(baiduMap);
            //判断当前查找出来的路线
            if (walkingRouteResult.getRouteLines() != null && walkingRouteResult.getRouteLines().size() > 0) {
                WalkingRouteLine walkingRouteLine = walkingRouteResult.getRouteLines().get(0);
                walkingRouteOverlay.setData(walkingRouteLine);
                walkingRouteOverlay.addToMap();
                //当前的定位移动到开始点并放大地图

                startLatLng = new LatLng(39.86923, 116.397428);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(startLatLng, 16);
                baiduMap.setMapStatus(status);

            }
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            Log.i("TAG", "onGetTransitRouteResult");
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            Log.i("TAG", "onGetMassTransitRouteResult");
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            Log.i("TAG", "onGetDrivingRouteResult");
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
            Log.i("TAG", "onGetIndoorRouteResult");
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
            Log.i("TAG", "onGetBikingRouteResult");
        }
    };

    private void searchRoute() {
        String startName, endName;
        startName = inputStart.getText().toString();
        endName = inputEnd.getText().toString();
        if (TextUtils.isEmpty(startName) || TextUtils.isEmpty(endName)) {
            Toast.makeText(this, "请输入正确起点和终点", Toast.LENGTH_SHORT).show();
        } else {
            startNode = PlanNode.withCityNameAndPlaceName("北京", startName);
            endNode = PlanNode.withCityNameAndPlaceName("北京", endName);

            WalkingRoutePlanOption option = new WalkingRoutePlanOption();
            option.from(startNode);
            option.to(endNode);



            //搜索路径
            boolean search = routePlanSearch.walkingSearch(option);
            Log.e("111", search + "");

            //搜索路径 骑车
//            routePlanSearch.bikingSearch((new BikingRoutePlanOption())
//                    .from(startNode).to(endNode));

//            mDraMap.closeDrawer(Gravity.LEFT);//关闭侧滑
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                String city = "北京";
                search(city);
                break;
            case R.id.iv_nav:
                //打开侧滑
                mDraMap.openDrawer(Gravity.LEFT);
                break;

        }
    }

    /**
     * 搜索
     *
     * @param city
     */
    private void search(String city) {
        String word = input.getText().toString();
        if (!TextUtils.isEmpty(word)) {
            PoiCitySearchOption option = new PoiCitySearchOption();
            option.city(city);
            option.keyword(word);
            poiSearch.searchInCity(option);
        }
    }

    /**
     * 搜索的监听
     */
    OnGetPoiSearchResultListener poiSearchResultListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.i("TAG", "onGetPoiResult");
            poiList.clear();
            if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                poiList.addAll(poiResult.getAllPoi());
                searchItemAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            Log.i("TAG", "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            Log.i("TAG", "onGetPoiDetailResult");
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            Log.i("TAG", "onGetPoiIndoorResult");
        }
    };


    /**
     * 以当前的经纬度为圆心绘制一个圆
     */
    private void drawCircle(double lat, double gt) {
        //设置圆心位置
        baiduMap.clear();
        LatLng center = new LatLng(lat, gt);
        //设置圆对象
        CircleOptions circleOptions = new CircleOptions().center(center)
                .radius(2000)
                .fillColor(R.color.colorAccent)
                .stroke(new Stroke(3, R.color.color_white)); //设置边框的宽度和颜色
        //在地图上添加显示圆
        baiduMap.addOverlay(circleOptions);
    }

    /**
     * 以当前的经纬度为圆心添加图标
     */
    private void addMark(double lat, double gt, String name) {

        //地图放大
        MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(startLatLng, 16);
        baiduMap.setMapStatus(status);


        //定义Maker坐标点 //设置圆心位置
        LatLng point = new LatLng(lat, gt);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ai);
        //构建MarkerOption，用于在地图上添加Marker
        MarkerOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //定义动画
        option.animateType(MarkerOptions.MarkerAnimateType.jump);
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);

        /*添加信息窗（弹窗覆盖物InfoWindow）*/

        //用来构造InfoWindow的Button
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.popup);
        button.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前的定位移动到开始点并放大地图
                LatLng start = new LatLng(lat,gt);
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLngZoom(start, 18);
                baiduMap.setMapStatus(status);
            }
        });

        //构造InfoWindow
        //point 描述的位置点
        //-100 InfoWindow相对于point在y轴的偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, point, -100);

        //使InfoWindow生效
        baiduMap.showInfoWindow(mInfoWindow);
    }

    /**
     * 申请权限成功时
     */
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    void ApplySuccess() {
        initMap();
    }

    /*指南针*/
    // 记录指南针图片转过的角度
    float currentDegree = -100f;

    // 获取触发event的传感器类型 领图片方向旋转
    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        if (sensorType == Sensor.TYPE_ORIENTATION) {
            // 获取绕Z轴转过的角度
            float degree = event.values[0];
            // 创建旋转动画（反向转过degree度）
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            // 设置动画的持续时间
            ra.setDuration(200);
            // 运行动画
            ivZhinan.startAnimation(ra);
            currentDegree = -degree;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();

        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener((SensorEventListener) this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();

        // 取消注册 系统的方向传感器
        mSensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    protected void onStop() {
        // 取消注册 系统的方向传感器
        mSensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        locationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
    }
}