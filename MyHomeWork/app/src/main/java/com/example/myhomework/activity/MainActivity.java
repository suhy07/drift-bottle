package com.example.myhomework.activity;

import static com.example.myhomework.utils.UiUtil.hideActionBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.myhomework.R;
import com.example.myhomework.utils.PermissionsUtil;
import com.example.myhomework.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

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
    }
}
