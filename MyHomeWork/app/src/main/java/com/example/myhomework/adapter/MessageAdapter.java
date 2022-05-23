package com.example.myhomework.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhomework.R;
import com.example.myhomework.activity.MessagedetailsActivity;
import com.example.myhomework.bean.Message;
import com.example.myhomework.databinding.ItemMessageBinding;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>  {

    private List<Message> messages;
    Activity activity;
    ItemMessageBinding itemMessageBinding;
    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View view) {
            super(view);
            itemMessageBinding  = ItemMessageBinding.bind(itemView);
        }
    }

    public MessageAdapter(List<Message> messages, Activity activity) {
        this.messages = messages;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Message message = messages.get(position);
        itemMessageBinding.message.setText(message.getMessage());
        itemMessageBinding.author.setText(message.getAuthor());
        itemMessageBinding.getRoot().setOnClickListener(v->{
             Intent intent = new Intent(activity, MessagedetailsActivity.class);
             intent.putExtra("message_id",message.getId());
             activity.startActivity(intent);
           }
        );
    }
    @Override
    public int getItemCount(){
        return messages.size();
    }

}

