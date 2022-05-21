package com.example.myhomework.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myhomework.R;

public class TopBar extends RelativeLayout {
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;
    private TopBarClickListener topBarClickListener;


    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);//获取自定义xml那些属性
        int mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);//取出对应的值，如果不能存在默认值为0
        Drawable mBackground = ta.getDrawable(R.styleable.TopBar_background);
        String mLeftText = ta.getString(R.styleable.TopBar_leftText);

        int mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        Drawable mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        String mRightText = ta.getString(R.styleable.TopBar_rightText);

        float mTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,10);
        int mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor,0);
        String mTitle = ta.getString(R.styleable.TopBar_title);
        ta.recycle(); //避免重建时发生错误  资源回收

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);
        //赋值
        mLeftButton.setText(mLeftText); //标题
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mBackground);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //布局
        RelativeLayout.LayoutParams mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftParams); //添加到viewGroup

        RelativeLayout.LayoutParams mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams); //添加到viewGroup

        RelativeLayout.LayoutParams mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams); //添加到viewGroup

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarClickListener != null){
                    topBarClickListener.rightClick(); //右边点击事件
                }
            }
        });
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topBarClickListener != null){
                    topBarClickListener.leftClick(); //左边点击事件
                }
            }
        });
    }

    /**
     * 是否隐藏或者显示
     * @param id 0 显示 其他为隐藏
     * @param flag ture 显示 false 隐藏
     */
    public void setButtonVisible(int id,boolean flag){
        if(flag){
            if(id == 0){
                mLeftButton.setVisibility(VISIBLE);
            }else {
                mRightButton.setVisibility(VISIBLE);
            }
        }else {
            if(id == 0){
                mLeftButton.setVisibility(GONE);
            }else {
                mRightButton.setVisibility(GONE);
            }
        }
    }

    /**
     * 暴露一个方法实现调用者来注册接口回调
     * @param topBarClickListener
     */
    public void setTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    //定义接口
    public interface TopBarClickListener{
        /**
         * 左按钮点击事件
         */
        void leftClick();

        /**
         * 右按钮点击事件
         */
        void rightClick();
    }
}