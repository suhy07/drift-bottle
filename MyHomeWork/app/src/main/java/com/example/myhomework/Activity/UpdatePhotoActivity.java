package com.example.myhomework.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.myhomework.R;

import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

public class UpdatePhotoActivity extends AppCompatActivity {
    private MapView mMapView ;
    BaiduMap mBaiduMap;
    LocationClient mLocationClient;
    private ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        setContentView(R.layout.activity_update_photo);

        ImageView imageView;
        imageView = findViewById(R.id.picture);
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UpdatePhotoActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(UpdatePhotoActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    openAlbum();
                }
            }
        });


        //获取地图控件引用
        mMapView = (MapView)findViewById(R.id.baiduMapView);
        //获取到地图
        mBaiduMap = mMapView.getMap();
        //设置地图放大的倍数
        init();
        //设置地图定位的一些参数，如定位图标，精度圈颜色等
        configure();
        //定位初始化
        init_location();


    }


    //图片
    private void openAlbum(){
        Toast.makeText(this,"openAlbum",Toast.LENGTH_LONG).show();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);//打开相册
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){

        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                    //Toast.makeText(this,"onRequestPermissionsResult",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"你拒绝了许可",Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onResume()
    {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
    /**
     * 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
     */

    public class MyLocationListener extends BDAbstractLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation location)
        {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
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

    /**
     *    设置地图放大的倍数
     */
    public void init()
    {
        //设置地图放大的倍数
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }



    /**
     * 自定义内容:
     * 参数说明
     * (1)定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
     * （2）是否开启方向
     * （3）自定义定位图标 支持自定义定位图标样式，
     * （4）自定义精度圈填充颜色
     * （5）自定义精度圈边框颜色
     */

    public void configure()
    {
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true,
                BitmapDescriptorFactory.fromResource(R.drawable.icon_location),
                0xAAFFFF88, 0xAA00FF00));
    }

    /**
     * 定位的初始化
     */
    public void init_location()
    {

        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(UpdatePhotoActivity.this);
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

        Toast.makeText(this,"onActivityResult",Toast.LENGTH_LONG).show();

        switch (requestCode) {
            case 2:
                Toast.makeText(this,"RESULT_OK",Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= 19) {
                    Uri uri=data.getData();
                    Toast.makeText(this,""+uri,Toast.LENGTH_LONG).show();

                    this.imageview=findViewById(R.id.picture);
                    this.imageview.setImageURI(uri);
                } else {
                    handleImageBeforeKitKat(data);
                    Toast.makeText(this,"4.4以下",Toast.LENGTH_LONG).show();
                }
                //}
                break;
            default:
                break;

        }

    }
    @TargetApi(19)
    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private  String getImagePath(Uri uri,String selection){
        String path =null;
        Cursor cursor =getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        //Toast.makeText(this,"图片地址"+path,Toast.LENGTH_LONG).show();
        return path;
    }

    private  void displayImage(String imagePath){
        if(imagePath!=null){

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            this.imageview=findViewById(R.id.picture);
            this.imageview.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"未能获得图像",Toast.LENGTH_LONG).show();
        }
    }

}