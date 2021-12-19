package com.example.myhomework.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.myhomework.Global.GlobalMemory;
import com.example.myhomework.R;
import com.example.myhomework.Service.UserService;
import com.example.myhomework.Utils.HttpUtils;
import com.example.myhomework.Utils.PermissionsUtils;
import com.example.myhomework.databinding.AppbarBinding;
import com.example.myhomework.databinding.DrawerHeaderBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myhomework.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public static DrawerLayout drawerLayout;
    private ActivityMainBinding binding;


    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_NOTHING);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainActivity=this;
        UserService.ResetUserData();
        drawerLayout=binding.drawerLayout;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        PermissionsUtils.verifyStoragePermissions(this);
    }
    public void onRecallSetHead(){
        ImageView head1=binding.navigationView.getHeaderView(0).findViewById(R.id.userhead_drawerlayout),
                head2=binding.getRoot().findViewById(R.id.imageButton_UserHead_toolbar);
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+UserService.GetUserHead(),head1,this);
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+UserService.GetUserHead(),head2,this);
        //HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+UserService.GetUserHead(),binding.picture,this);
        // headerBinding.userheadDrawerlayout.setImageBitmap(HttpUtils.getURLimage("http://47.98.173.217:8080/downloadFile/app_icon.png"));
    }
}
