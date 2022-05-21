package com.example.myhomework.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.myhomework.R;
import com.example.myhomework.utils.PermissionsUtils;
import com.example.myhomework.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    public static DrawerLayout drawerLayout;
    private ActivityMainBinding binding;

// 调用类实现OnGetDataResultListener监听，用于接收Android AR识别SDK回调数据信息
    /**
     * 返回景区数据，跳转到景区Activity
     * @param arSceneryResponse
     */

    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideActionBar();
//        mainActivity=this;
//        UserService.ResetUserData();
//        drawerLayout=binding.drawerLayout;
//        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.page_news, R.id.page_photo, R.id.page_chat,R.id.page_home)
//                .setOpenableLayout(drawerLayout)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//        //binding.navigationView.setCo
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.page_map,R.id.page_mine)
                .setOpenableLayout(drawerLayout)
                .build();
        PermissionsUtils.verifyStoragePermissions(this);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
    }

    private void hideActionBar(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_NOTHING);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
