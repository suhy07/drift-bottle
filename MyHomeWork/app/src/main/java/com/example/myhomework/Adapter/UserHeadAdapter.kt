package com.example.myhomework.Adapter

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.Bean.UserHead
import com.example.myhomework.R

class UserHeadAdapter(val userHeadList:List<UserHead>):RecyclerView.Adapter<UserHeadAdapter.ViewHolder>(){
    lateinit var _view:View
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userHeadImageView:ImageView = view.findViewById(R.id.img)
        val historyMsgTextView:TextView =view.findViewById(R.id.msg)
        val userIdTextView:TextView=view.findViewById(R.id.title)
        val msgNumImageView:ImageView=view.findViewById(R.id.msgNumImageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHeadAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.userhead_item, parent, false)
        _view=view
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userList = userHeadList[position]
       // holder.userHeadImageView.setImageURI(userList.userHeadUri)
        holder.userHeadImageView.setImageResource(userList.userHeadUri)
        holder.userIdTextView.text=userList.id
        holder.historyMsgTextView.text=userList.historyMsg
    }
    private fun getBitmapFromUri(uri: Uri) = _view.context.contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
    override fun getItemCount() = userHeadList.size
}