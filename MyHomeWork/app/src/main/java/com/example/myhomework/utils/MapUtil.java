package com.example.myhomework.utils;

import static com.example.myhomework.global.GlobalMemory.Address;
import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;
import static com.example.myhomework.global.GlobalMemory.PoiList;
import static com.example.myhomework.global.GlobalMemory.PoiNameList;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.example.myhomework.R;

public class MapUtil {
     public static void initLocationOption(BaiduMap mBaiduMap, Context context, View mapView) {
        LocationClient mLocationClient;
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
////定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
//        LocationClient locationClient = new LocationClient(getActivity().getApplication().getApplicationContext());
////声明LocationClient类实例并配置定位参数
//        LocationClientOption locationOption = new LocationClientOption();
//        MyLocationListener myLocationListener = new MyLocationListener();
////注册监听函数
//        locationClient.registerLocationListener(myLocationListener);
////可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
////可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
//        locationOption.setCoorType("gcj02");
////可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
//        locationOption.setScanSpan(1000);
////可选，设置是否需要地址信息，默认不需要
//        locationOption.setIsNeedAddress(true);
////可选，设置是否需要地址描述
//        locationOption.setIsNeedLocationDescribe(true);
////可选，设置是否需要设备方向结果
//        locationOption.setNeedDeviceDirect(false);
////可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//        locationOption.setLocationNotify(true);
////可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        locationOption.setIgnoreKillProcess(true);
////可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        locationOption.setIsNeedLocationDescribe(true);
////可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        locationOption.setIsNeedLocationPoiList(true);
////可选，默认false，设置是否收集CRASH信息，默认收集
//        locationOption.SetIgnoreCacheException(false);
////可选，默认false，设置是否开启Gps定位
//        locationOption.setOpenGps(true);
////可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
//        locationOption.setIsNeedAltitude(false);
////设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
//        locationOption.setOpenAutoNotifyMode();
////设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
//        locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
////需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//        locationClient.setLocOption(locationOption);
////开始定位
//        locationClient.start();
        mBaiduMap.setMyLocationEnabled(true);
         try {
             mLocationClient = new LocationClient(context);
             //通过LocationClientOption设置LocationClient相关参数
             LocationClientOption option = new LocationClientOption();
             option.setOpenGps(true); // 打开gps
             option.setCoorType("gcj02"); // 设置坐标类型
             option.setScanSpan(1100);
             option.setNeedDeviceDirect(true);
             option.setIsNeedAddress(true);
             option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
             option.setIsNeedAltitude(true);
             option.setIsNeedLocationDescribe(true);
             option.setIsNeedLocationPoiList(true);
             //设置locationClientOption
             mLocationClient.setLocOption(option);
             //注册LocationListener监听器
             MyLocationListener myLocationListener = new MyLocationListener(mBaiduMap, mapView,context);
             mLocationClient.registerLocationListener(myLocationListener);
             //开启地图定位图层
             mLocationClient.start();

         } catch (Exception e) {
             e.printStackTrace();
         }

        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true,
                BitmapDescriptorFactory.fromResource(R.drawable.arrow),
                0x3FFFFF88, 0x3F00FF00));
    }

    /**
     * 实现定位回调
     */
    static class MyLocationListener extends BDAbstractLocationListener {
        BaiduMap mBaiduMap;
        View view;
        Context context;
        MyLocationListener(BaiduMap mBaiduMap,View view, Context context){
            this.mBaiduMap = mBaiduMap;
            this.view = view;
        }
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            //获取纬度信息
            double latitude = location.getLatitude();
            Latitude = latitude;
            Log.d("TAGTAG",latitude+"");
            //获取经度信息
            double longitude = location.getLongitude();
            Longitude = longitude;
            Log.d("TAGTAG",longitude+"");
            //获取定位精度，默认值为0.0f
            float radius = location.getRadius();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            String locationDescribe = location.getLocationDescribe();
            Address = locationDescribe;

            Log.d("TAGTAG",location.toString());
            Log.d("TAGTAG","描述："+locationDescribe+"");
            Log.d("TAGTAG","乡镇: "+location.getTown()+"");
            Log.d("TAGTAG","街道："+location.getStreet()+"");
            Log.d("TAGTAG","城市："+location.getCity()+"");
            Log.d("TAGTAG","省份："+location.getProvince()+"");
            Log.d("TAGTAG","详细地址："+location.getAddrStr()+"");
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取周边POI信息相关的结果
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            if(location.getPoiList()!=null){
                Poi poi = location.getPoiList().get(0);
                String poiName = poi.getName();    //获取POI名称
                String poiTags = poi.getTags();   //获取POI类型
                String poiAddr = poi.getAddr();    //获取POI地址 //获取周边POI信息
                PoiNameList.clear();
                for (Poi _poi: location.getPoiList()){
                    Log.d("TAGTAG","poi"+_poi.getName()+"");
                    PoiList.add(_poi);
                    PoiNameList.add(_poi.getName());
                }
            }

            PoiRegion poiRegion= location.getPoiRegion();
            String poiDerectionDesc = poiRegion.getDerectionDesc();    //获取PoiRegion位置关系
            String poiRegionName = poiRegion.getName();    //获取PoiRegion名称
            String _poiTags = poiRegion.getTags();    //获取PoiRegion类型

            int errorCode = location.getLocType();
            if (location == null || view == null)
            {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }

}
