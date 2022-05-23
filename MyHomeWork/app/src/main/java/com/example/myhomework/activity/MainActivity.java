package com.example.myhomework.activity;

import static com.example.myhomework.utils.UiUtil.hideActionBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.baidu.mapapi.SDKInitializer;
import com.example.myhomework.R;
import com.example.myhomework.fragment.MapFragment;
import com.example.myhomework.service.SDKReceiver;
import com.example.myhomework.utils.PermissionsUtil;
import com.example.myhomework.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    boolean visible = false;

    List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hideActionBar(this);
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.page_map,R.id.page_mine)
                .build();
        PermissionsUtil.verifyStoragePermissions(this);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);

//        /**动态注册广播*/
//        IntentFilter iFilter = new IntentFilter();
//        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
//        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
//        SDKReceiver mReceiver = new SDKReceiver();
//        registerReceiver(mReceiver, iFilter);

        viewList.add(binding.bubble);
        viewList.add(binding.view);
        viewList.add(binding.boardBtn);
        viewList.add(binding.bottleBtn);
        for(View v : viewList){
            v.setAlpha(0);
            v.setVisibility(View.INVISIBLE);
        }
        binding.bottleBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AddBottleActivity.class);
            startActivity(intent);
        });
        binding.boardBtn.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, AddMessageActivity.class);
            startActivity(intent);
        });
        binding.btnAdd.setOnClickListener(v->{
            visible = !visible;
            if (visible){
                for(View v1 : viewList){
                    v1.setVisibility(View.VISIBLE);
                    v1.animate().translationY(0).alpha(1).setDuration(200).start();
                }
            }else{
                for(View v1 : viewList){
                    v1.animate().translationY(0).alpha(0).setDuration(200).start();
                    new Thread(()->{
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        v1.setVisibility(View.INVISIBLE);
                    });

                }
            }
        });
    }
}
