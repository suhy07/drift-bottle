package com.example.myhomework.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhomework.Adapter.MsgAdapter
import com.example.myhomework.Adapter.UserHeadAdapter
import com.example.myhomework.MemObject.Msg
import com.example.myhomework.MemObject.UserHead
import com.example.myhomework.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
                msgList.add(Msg(content,Msg.RIGHT))     //将输入的消息及其类型添加进消息数据列表中
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
        return view
    }
    fun initMsg(){
        msgList.add(Msg("聊天信息测试",Msg.LEFT))
    }
    fun initHead(){
        userList.add(UserHead(R.drawable.camera,"大宝","hi",1))
    }

    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}