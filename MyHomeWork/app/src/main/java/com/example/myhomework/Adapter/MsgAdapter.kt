package com.example.myhomework.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.Bean.Msg
import com.example.myhomework.R
import java.lang.IllegalArgumentException

class MsgAdapter(val msgList:List<Msg>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //载入右聊天框布局控件
    inner class RightViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val rightMsg: TextView = view.findViewById(R.id.rightText)
    }
    //载入左聊天框布局控件
    inner class LeftViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val leftMsg:TextView = view.findViewById(R.id.leftText)
    }
    //获取消息类型(左或者右),返回到onCreateViewHolder()方法的viewType参数里面
    fun getItemviewType(position: Int): Int {
        val msg = msgList[position] //根据当前数据源的元素类型
        return msg.type
    }
    //根据viewType消息类型的不同,构建不同的消息布局(左&右)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType== Msg.LEFT){
            val leftView = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_layout,parent,false)
            return LeftViewHolder(leftView) //返回控件+布局
        }else{
            val rightView = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_layout,parent,false)
            return RightViewHolder(rightView)
        }
    }
    //对聊天控件的消息文本进行赋值
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.rightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }
    //返回项数
    override fun getItemCount(): Int {
        return msgList.size
    }
}