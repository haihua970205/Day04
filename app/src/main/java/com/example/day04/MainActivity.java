package com.example.day04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day04.jpush.TestJPushActivity;
import com.example.day04.map.MapBaiduActivity;
import com.example.day04.ui.easemob.EaseMobActivity;
import com.example.day04.ui.tongpao.TongpaoActivity;
import com.example.day04.ui.tongpao.WelcomActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mCir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TongpaoActivity.class));
            }
        });
        findViewById(R.id.mCirs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestJPushActivity.class));
            }
        });
        findViewById(R.id.btn_baidu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapBaiduActivity.class));
            }
        });
        findViewById(R.id.btn_easeMob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EaseMobActivity.class));
            }
        });
        findViewById(R.id.btn_jz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TextjzVeoid.class));
            }
        });
    }
}

