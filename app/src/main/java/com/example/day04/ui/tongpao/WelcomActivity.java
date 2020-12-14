package com.example.day04.ui.tongpao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import io.reactivex.functions.Consumer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day04.R;
import com.example.day04.ui.home.HomeActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WelcomActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_dao;
    private String[] title = {
            "就给您安排上",
            "",
            "在这里偶遇您",
            "",
            "那么",
            "",
            "可现实残酷滴",
            "",
            "您喜欢奇迹吗",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        initView();
    }

    private void initView() {
       /* tv_title = (TextView) findViewById(R.id.tv_title);
        tv_dao = (TextView) findViewById(R.id.tv_dao);*/
        findViewById(R.id.tv_goto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomActivity.this, TongpaoActivity.class));
            }
        });


       /* AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1500);
        animation.setRepeatCount(3);
        animation.setRepeatMode(2);
        tv_title.startAnimation(animation);*/


       /* Observable.intervalRange(0, 9, 0, 2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long l = 8 - aLong;
                        tv_title.setText(title[(int)l]);
                        tv_dao.setText("倒计时"+l+"s");
                        if (l == 0) {
                            startActivity(new Intent(WelcomActivity.this, TongpaoActivity.class));
                            finish();
                        }
                    }
                });*/

    }
}
