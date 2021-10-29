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
import com.example.myhomework.Util.InitUserDataUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mview;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        mview=inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton userhead=  mview.findViewById(R.id.imageButton_UserHead_toolbar);
        ImageButton useritem=mview.findViewById(R.id.imageButton_UserItem_HomePage);
        ImageButton usermessage=mview.findViewById(R.id.imageButton_Message_HomePage);
        ImageButton contact=mview.findViewById(R.id.imageButton_Contact_HomePage);
        ImageButton settle=mview.findViewById(R.id.imageButton_Settle_HomePage);

        ImageView state=  mview.findViewById(R.id.imageView_State_toolbar);
        TextView username=mview.findViewById(R.id.textView_UserName_HomePage);
        TextView usertype=mview.findViewById(R.id.textView_UserType_HomePage);
        TextView pagename=mview.findViewById(R.id.textview_toolbar);


        username.setText(InitUserDataUtil.GetUserNickName());
        usertype.setText(InitUserDataUtil.GetUserType());
        pagename.setText("主页");

        userhead.setOnClickListener(v -> MainActivity.drawerLayout.openDrawer(Gravity.LEFT));
        useritem.setOnTouchListener(new ImageButtonOuTouchView(useritem));
        usermessage.setOnTouchListener(new ImageButtonOuTouchView(usermessage));
        contact.setOnTouchListener(new ImageButtonOuTouchView(contact));
        settle.setOnTouchListener(new ImageButtonOuTouchView(settle));

        return   mview;
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