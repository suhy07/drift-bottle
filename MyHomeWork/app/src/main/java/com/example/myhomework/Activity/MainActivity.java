package com.example.myhomework.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myhomework.R;
import com.example.myhomework.Util.HttpUtil;
import com.example.myhomework.Util.InitUserDataUtil;
import com.example.myhomework.Util.PhotoUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myhomework.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {



    private ActivityMainBinding binding;
    public static DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        InitUserDataUtil.ResetUserData(binding.getRoot(),this);

        BottomNavigationView navView = binding.navView;
        drawerLayout=binding.drawerLayout;
        NavigationView navigationView=binding.navigationView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        View headlayoutView=navigationView.inflateHeaderView(R.layout.drawer_header);
        CircleImageView circleImageView=headlayoutView.findViewById(R.id.userhead_drawerlayout);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.page_news, R.id.page_photo,R.id.page_home)
                .build();
        NavigationUI.setupWithNavController(binding.navView, navController);
        verifyStoragePermissions(this);

        //Bitmap pngBM=getURLimage("http://47.98.173.217:8080/downloadFile/app_icon.png");
        //circleImageView.setImageBitmap(pngBM);
        //imageView.setImageBitmap(pngBM);

    }
    public static Uri picuri;
    public static String filename;

    @Override
    protected  void  onActivityResult(int  requestCode,  int  resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(picuri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //存入相册
        MediaStore.Images.Media.insertImage(this.getContentResolver(),
                bitmap, filename, null);
    }


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE={
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };
    //权限申请函数，必须在onCreate里面调用
    public static void verifyStoragePermissions(Activity activity){
        try {
            int permisssion= ActivityCompat.checkSelfPermission(activity,"android.permission.WRITE_EXTERNAL_STORAGE" );
            if (true){
                ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);//弹出权限申请对话框
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
