package com.example.myhomework.Fragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.Activity.MainActivity
import com.example.myhomework.Adapter.MsgAdapter
import com.example.myhomework.Adapter.UserHeadAdapter
import com.example.myhomework.Bean.Msg
import com.example.myhomework.Bean.UserHead
import com.example.myhomework.R


class ChatFragment : Fragment() {


    private val msgList = ArrayList<Msg>()
    private val userList = ArrayList<UserHead>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_chat, container, false)
        initMsg()   //初始化聊天机器人的见面语
        val charRecyclerView: RecyclerView =view.findViewById(R.id.chatRecyclerView)
        charRecyclerView.layoutManager = LinearLayoutManager(view.context)  //布局为线性垂直
        val charAdapter = MsgAdapter(msgList)   //建立适配器实例
        charRecyclerView.adapter = charAdapter  //传入适配器
        val sendButton: Button =view.findViewById(R.id.sendButton)
        sendButton.setOnClickListener{
            val messageEditText: EditText =view.findViewById(R.id.messageEditText)
            val content:String = messageEditText.text.toString()   //获取输入框的文本
            if(content.isNotEmpty()){
                msgList.add(Msg(content, Msg.RIGHT))     //将输入的消息及其类型添加进消息数据列表中
                charAdapter.notifyItemInserted(msgList.size-1)   //为RecyclerView添加末尾子项
                charRecyclerView.scrollToPosition(msgList.size-1) //跳转到当前位置
                messageEditText.setText("")    //清空输入框文本
            }
        }
        //用户头像数据recycle
        initHead()
        val userHeadRecyclerView: RecyclerView =view.findViewById(R.id.userHeadRecyclerView)
        userHeadRecyclerView.layoutManager = LinearLayoutManager(view.context)  //布局为线性垂直
        val userHeadAdapter = UserHeadAdapter(userList)   //建立适配器实例
        userHeadRecyclerView.adapter=userHeadAdapter      //传入适配器
        val pagename:TextView= view.findViewById(R.id.textview_toolbar);
        pagename.setText("通讯录")
        val userhead: ImageView =view.findViewById(R.id.imageButton_UserHead_toolbar)
        userhead.setOnClickListener({ v->MainActivity.drawerLayout.openDrawer(Gravity.LEFT) })
        return view
    }
    fun initMsg(){
        msgList.add(Msg("我说句话试试", Msg.RIGHT))
        msgList.add(Msg("聊天信息测试", Msg.LEFT))
    }
    fun initHead(){
        userList.add(UserHead(R.drawable.camera,"大宝","",1))
    }
    fun reCallMsg(str:String){
        msgList.add(Msg(str,Msg.LEFT))
    }

}