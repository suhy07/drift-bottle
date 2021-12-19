package com.example.myhomework.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomework.R;


public class NewItemAdapter extends RecyclerView.Adapter<NewItemAdapter.MyViewHolder> {
    private final String title = "今日新闻";
    private final int icon = R.drawable.icon_news;
    private final String introduce = "这是关于今日的新闻，我也不知道我在干嘛,啦啦啦啦啦啦";
    private Activity activity;
    public NewItemAdapter(Activity activity){
        this.activity=activity;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iv.setImageResource(icon);
        holder.title.setText(title);
        holder.introduce.setText(introduce);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        TextView introduce;

        public MyViewHolder(@NonNull View itemview) {
            super(itemview);
            iv = itemview.findViewById(R.id.imageView);
            title = itemview.findViewById(R.id.newsTitle);
            introduce = itemview.findViewById(R.id.newsContent);
        }
    }
}
