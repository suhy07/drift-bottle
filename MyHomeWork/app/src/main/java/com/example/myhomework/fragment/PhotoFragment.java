package com.example.myhomework.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework.activity.HistoricalRecordsActivity;
import com.example.myhomework.activity.MainActivity;
import com.example.myhomework.activity.UpdatePhotoActivity;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.R;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.HttpUtils;
import com.example.myhomework.utils.PermissionsUtils;

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
        Button history=view.findViewById(R.id.history);
        picture = view.findViewById(R.id.picture);

        PermissionsUtils.verifyStoragePermissions(getActivity());//获取权限
        takePhoto.setOnClickListener(v -> {
            Intent intent =new Intent(getActivity(), UpdatePhotoActivity.class);
            startActivity(intent);
        });
        history.setOnClickListener(v ->startActivity(new Intent(getActivity(), HistoricalRecordsActivity.class)));
        TextView PageName=view.findViewById(R.id.textview_toolbar);
        PageName.setText("随手拍");
        ImageView userhead=  view.findViewById(R.id.imageButton_UserHead_toolbar);
        userhead.setOnClickListener(v -> MainActivity.drawerLayout.openDrawer(Gravity.LEFT));
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+ UserService.GetUserHead(),userhead,getActivity());
        return view;
    }

}