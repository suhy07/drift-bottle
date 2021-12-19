package com.example.myhomework.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhomework.Activity.MainActivity;
import com.example.myhomework.Adapter.NewItemAdapter;
import com.example.myhomework.R;

public class NewsFragment extends Fragment {

    private View mview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //新闻
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview=inflater.inflate(R.layout.fragment_news, container, false);
        ImageView userhead=  mview.findViewById(R.id.imageButton_UserHead_toolbar);
        RecyclerView recyclerView = mview.findViewById(R.id.recyclerview);
        TextView PageName=mview.findViewById(R.id.textview_toolbar);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));  //设置为竖直向下
        NewItemAdapter newItemAdapter = new NewItemAdapter(getActivity());
        recyclerView.setAdapter(newItemAdapter);
        PageName.setText("新闻");

        userhead.setOnClickListener(v -> MainActivity.drawerLayout.openDrawer(Gravity.LEFT));
        return mview;
    }
}