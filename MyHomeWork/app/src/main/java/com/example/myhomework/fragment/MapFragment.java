package com.example.myhomework.fragment;

import static com.example.myhomework.global.GlobalMemory.Latitude;
import static com.example.myhomework.global.GlobalMemory.Longitude;
import static com.example.myhomework.global.GlobalMemory.MapRecordList;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.myhomework.R;
import com.example.myhomework.adapter.MapRecordAdapter;
import com.example.myhomework.databinding.FragmentMapBinding;
import com.example.myhomework.service.MapService;
import com.example.myhomework.service.SDKReceiver;
import com.example.myhomework.utils.MapUtil;


public class MapFragment extends Fragment {

    FragmentMapBinding binding;
    private LocationClient mLocationClient;
    MapRecordAdapter mapRecordAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        binding.map.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        binding.map.onDestroy();
    }

    private BaiduMap mBaiduMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater);

        mBaiduMap = binding.map.getMap();
        MapUtil.initLocationOption(mBaiduMap,getActivity(),binding.map);
        initRecycleView();
        initPoint();
        /**动态注册广播*/
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        SDKReceiver mReceiver = new SDKReceiver();
        getActivity().registerReceiver(mReceiver, iFilter);

        return binding.getRoot();
    }

    private void initRecycleView() {
        mapRecordAdapter = new MapRecordAdapter(MapRecordList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(mapRecordAdapter);
    }


    /**
     * 初始化定位参数配置
     */
    private void initPoint(){
        MapService.refreshPointList(mBaiduMap,mapRecordAdapter);
    }
}
