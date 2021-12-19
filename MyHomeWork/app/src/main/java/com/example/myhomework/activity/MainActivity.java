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
import android.widget.ImageView;


import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.R;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.HttpUtils;
import com.example.myhomework.utils.PermissionsUtils;
import com.example.myhomework.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static DrawerLayout drawerLayout;
    private ActivityMainBinding binding;


    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_NOTHING);
        //NgetWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mainActivity=this;
        UserService.ResetUserData();
        drawerLayout=binding.drawerLayout;
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.page_news, R.id.page_photo, R.id.page_chat,R.id.page_home)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.navigationView,navController);
        //binding.navigationView.setCo

        PermissionsUtils.verifyStoragePermissions(this);
    }
    public void onRecallSetHead(){
        ImageView head1=binding.navigationView.getHeaderView(0).findViewById(R.id.userhead_drawerlayout),
                head2=binding.getRoot().findViewById(R.id.imageButton_UserHead_toolbar);
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+UserService.GetUserHead(),head1,this);
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+UserService.GetUserHead(),head2,this);
    }
}
