package com.example.myhomework.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.myhomework.R;
import com.example.myhomework.Util.InitUserDataUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myhomework.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

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
        //Bitmap pngBM=getURLimage("http://47.98.173.217:8080/downloadFile/app_icon.png");
        //circleImageView.setImageBitmap(pngBM);
        //imageView.setImageBitmap(pngBM);

    }
}
