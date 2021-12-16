package com.example.myhomework.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.Activity.UpdatePhotoActivity;
import com.example.myhomework.R;
import com.example.myhomework.Utils.PermissionsUtils;
import com.example.myhomework.Utils.PhotoUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PhotoFragment extends Fragment {

    private ImageView picture;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_photo, container, false);
        Button takePhoto = view.findViewById(R.id.take_photo);
        Button chooseFromAlbum = view.findViewById(R.id.choose_from_album);
        picture = view.findViewById(R.id.picture);

        PermissionsUtils.verifyStoragePermissions(getActivity());//获取权限

        takePhoto.setOnClickListener(v -> {
            // 创建File对象，用于存储拍照后的图片
            /*
            File outputImage = new File(view.getContext().getExternalCacheDir(), UUID.randomUUID().toString());
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT < 24) {
                imageUri = Uri.fromFile(outputImage);

            } else {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, outputImage.getPath());
                imageUri = FileProvider.getUriForFile(view.getContext(),
                        view.getContext().getPackageName() + ".fileprovider",
                        outputImage);
            }
            // 启动相机程序
             */
            //Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            Intent intent =new Intent(getActivity(), UpdatePhotoActivity.class);
            startActivity(intent);
        });
        chooseFromAlbum.setOnClickListener(v-> {
            PhotoUtils.openAlbum(getActivity());
            picture.setImageURI(MainActivity.picuri);
        });
        TextView PageName=view.findViewById(R.id.textview_toolbar);
        PageName.setText("随手拍");
        ImageButton userhead=  view.findViewById(R.id.imageButton_UserHead_toolbar);
        userhead.setOnClickListener(v -> MainActivity.drawerLayout.openDrawer(Gravity.LEFT));

        return view;
    }

}