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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;


import com.example.myhomework.R;
import com.example.myhomework.Service.UserService;
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

    private ActivityMainBinding binding;
    public static DrawerLayout drawerLayout;
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mainActivity=this;
        setContentView(binding.getRoot());
        UserService.ResetUserData();
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

        //Bitmap pngBM=getURLimage("http://47.98.173.217:8080");
        circleImageView.setImageBitmap(UserService.userHeadBitmap);
        //imageView.setImageBitmap(pngBM);
        test();

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
    public void test(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"imageUrl\":\"\",\r\n    \"title\":\"快递中心门口井盖没盖！\",\r\n    \"desc\":\"前面的路上有井盖没盖好！大家注意绕行！！\",\r\n    \"account\":\"221900203\",\r\n    \"address\":\"福建\",\r\n    \"category\":\"安全隐患\",\r\n    \"degree\":1,\r\n    \"time\":\"2021-11-06T13:14:25.909+00:00\",\r\n    \"process\":\"处理完成\"\r\n}\r\n\r\n");
        Request request = new Request.Builder()
                .url("http://49.235.134.191:8080/feedback/save")
                .method("POST", body)
                .addHeader("feedBack", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Toast.makeText(MainActivity.this,response.message()+":response",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
