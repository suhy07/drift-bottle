package com.example.myhomework.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myhomework.activity.HistoricalRecordsActivity;
import com.example.myhomework.activity.MainActivity;
import com.example.myhomework.global.GlobalMemory;
import com.example.myhomework.R;
import com.example.myhomework.service.UserService;
import com.example.myhomework.utils.HttpUtils;
import com.example.myhomework.databinding.AppbarBinding;
import com.example.myhomework.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    AppbarBinding appbarBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater);
        appbarBinding=AppbarBinding.inflate(inflater);
        ImageButton userItem=binding.imageButtonUserItemHomePage;
        ImageButton usermessage=binding.imageButtonMessageHomePage;
        ImageButton contact=binding.imageButtonContactHomePage;
        ImageButton settle=binding.imageButtonSettleHomePage;

        ImageView userHead=binding.imageViewUserHeadHomepage;
        TextView username=binding.textViewUserNameHomePage;
        TextView usertype=binding.textViewUserTypeHomePage;
        TextView pagename=binding.getRoot().findViewById(R.id.textview_toolbar);
        ImageView imageButton=binding.getRoot().findViewById(R.id.imageButton_UserHead_toolbar);
        imageButton.setOnClickListener(v->MainActivity.drawerLayout.openDrawer(Gravity.LEFT));

        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+
         UserService.GetUserHead(),userHead,getActivity());
        username.setText(UserService.GetUserNickName());
        usertype.setText(UserService.GetUserType());
        pagename.setText("主页");

        userItem.setOnTouchListener(new ImageButtonOuTouchView(userItem));
        usermessage.setOnTouchListener(new ImageButtonOuTouchView(usermessage));
        usermessage.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.page_news));
        contact.setOnTouchListener(new ImageButtonOuTouchView(contact));
        contact.setOnClickListener(v -> startActivity(new Intent(getActivity(), HistoricalRecordsActivity.class)));
        settle.setOnTouchListener(new ImageButtonOuTouchView(settle));
        settle.setOnClickListener(v -> Navigation.findNavController(getView()).navigate(R.id.page_chat));
        HttpUtils.setURLimageViewByBitmap(GlobalMemory.FileServerDownloadFileUri+"/"+ UserService.GetUserHead(),imageButton,getActivity());
        return binding.getRoot();
    }
    private class ImageButtonOuTouchView implements View.OnTouchListener {
        ImageButton imageButton;
        public ImageButtonOuTouchView(ImageButton imageButton){this.imageButton=imageButton;}
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                imageButton.setBackgroundColor(Color.GRAY);
            }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                imageButton.setBackgroundColor(Color.WHITE);
            }
            return false;
        }
    }
}