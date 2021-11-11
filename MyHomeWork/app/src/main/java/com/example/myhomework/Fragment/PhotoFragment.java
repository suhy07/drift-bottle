package com.example.myhomework.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.Activity.UpdatePhotoActivity;
import com.example.myhomework.R;
import com.example.myhomework.Util.PermissionsUtil;
import com.example.myhomework.Util.PhotoUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhotoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoFragment newInstance(String param1, String param2) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ImageView picture;
    private Uri imageUri;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_photo, container, false);
        Button takePhoto = view.findViewById(R.id.take_photo);
        Button chooseFromAlbum = view.findViewById(R.id.choose_from_album);
        picture = view.findViewById(R.id.picture);

        PermissionsUtil.verifyStoragePermissions(getActivity());//获取权限

        takePhoto.setOnClickListener(v -> {
            // 创建File对象，用于存储拍照后的图片
            String filename= UUID.randomUUID().toString();
            File outputImage = new File(view.getContext().getExternalCacheDir(),filename);
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
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            startActivityForResult(intent,1);

        });

        chooseFromAlbum.setOnClickListener(v-> {
            Intent intent =new Intent();
            intent.setClass(view.getContext(),UpdatePhotoActivity.class);
            startActivityForResult(intent,2);
        });


        TextView PageName=view.findViewById(R.id.textview_toolbar);
        PageName.setText("随手拍");
        ImageButton userhead=  view.findViewById(R.id.imageButton_UserHead_toolbar);
        userhead.setOnClickListener(v -> MainActivity.drawerLayout.openDrawer(Gravity.LEFT));

        return view;
    }

}