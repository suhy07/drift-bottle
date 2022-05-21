package com.example.myhomework.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myhomework.R;

public class TopBar extends RelativeLayout {
    private TopBarClickListener topBarClickListener;

    private ImageButton backImageButton;
    private TextView titleTextView;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);//获取自定义xml那些属性

        int backgroundColor = ta.getColor(R.styleable.TopBar_background, getResources().getColor(R.color.colorPrimaryVariant));
        setBackgroundColor(backgroundColor);

        int titleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, Color.WHITE);
        float titleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,20);
        String title = ta.getString(R.styleable.TopBar_title);

        boolean flag = ta.getBoolean(R.styleable.TopBar_back, Boolean.TRUE);

        ta.recycle(); //避免重建时发生错误  资源回收

        titleTextView = new TextView(context);
        titleTextView.setText(title);
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setTextColor(titleTextColor);

        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(titleTextView,titleParams);

        backImageButton = new ImageButton(context);
        backImageButton.setBackgroundResource(R.drawable.left);
        LayoutParams backParams = new LayoutParams(100,80);
        backParams.addRule(RelativeLayout.CENTER_IN_PARENT
        );
        backParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        backParams.setMarginStart(50);

        addView(backImageButton,backParams);


        backImageButton.setOnClickListener(v->{
            if(topBarClickListener != null){
                topBarClickListener.Click(); //右边点击事件
            }
        });
//
        if(!flag)
            backImageButton.setVisibility(INVISIBLE);

    }

    public void setTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }
    //定义接口
    public interface TopBarClickListener{
        void Click();
    }
}