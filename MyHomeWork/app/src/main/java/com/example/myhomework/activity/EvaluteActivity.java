package com.example.myhomework.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myhomework.bean.Evaluate;
import com.example.myhomework.service.EvaluateService;
import com.example.myhomework.service.UserService;
import com.example.myhomework.databinding.ActivityEvaluteBinding;
import com.example.myhomework.databinding.AppbarBinding;

public class EvaluteActivity extends AppCompatActivity {

    ActivityEvaluteBinding binding;
    AppbarBinding appbarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEvaluteBinding.inflate(getLayoutInflater());
        binding.include.textviewToolbar.setText("发表评价");
        setContentView(binding.getRoot());
        binding.push.setOnClickListener(v->{
            String star=binding.ratingBar.getNumStars()+"";
            String msg=binding.msg.getText().toString();
            if(msg.equals(""))
                Toast.makeText(this,"评价内容不能为空",Toast.LENGTH_LONG).show();
            Evaluate evaluate=new Evaluate();
            evaluate.setUid(UserService.GetUid());
            evaluate.setHid(HistoricalRecordsActivity.temhistory.getHid());
            evaluate.setMsg(msg);
            evaluate.setStar(star);
            EvaluateService.addEvaluate(evaluate,this);
        });
        binding.close.setOnClickListener(v->finish());
    }
}