package com.example.myhomework.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.R;
import com.example.myhomework.Service.UserService;
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
        TextView pagename=binding.getRoot().findViewById(R.id.imageButton_UserHead_toolbar);
        ImageButton imageButton=binding.getRoot().findViewById(R.id.imageButton_UserHead_toolbar);
        imageButton.setOnClickListener(v->MainActivity.drawerLayout.openDrawer(Gravity.LEFT));

        userHead.setImageBitmap(UserService.userHeadBitmap);
        username.setText(UserService.GetUserNickName());
        usertype.setText(UserService.GetUserType());
        pagename.setText("主页");

        userItem.setOnTouchListener(new ImageButtonOuTouchView(userItem));
        usermessage.setOnTouchListener(new ImageButtonOuTouchView(usermessage));
        contact.setOnTouchListener(new ImageButtonOuTouchView(contact));
        settle.setOnTouchListener(new ImageButtonOuTouchView(settle));

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