package com.example.day04.ui.easemob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day04.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EaseMobActivity extends AppCompatActivity {

    @BindView(R.id.btn_log)
    Button btnLog;
    @BindView(R.id.tv_friend)
    TextView tvFriend;
    @BindView(R.id.tv_failMsg)
    TextView tvFailMsg;
    @BindView(R.id.ed_user)
    EditText edUser;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.et_num)
    TextView etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease_mob);
        ButterKnife.bind(this);
        initTalk();

        IntentFilter intentFilter = new IntentFilter("com.a.a");
        registerReceiver(recvier, intentFilter);
    }
    private  BroadcastReceiver recvier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int num = intent.getIntExtra("num", 0);
            etNum.setText(num+"");
        }
    };

    private void initTalk() {
        tvFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EaseMobActivity.this, ChatActivity.class);
                intent.putExtra("touserid", tvFriend.getText().toString());
                startActivity(intent);
                /*String data = "2002A_A01";
                Intent intent = new Intent(EaseMobActivity.this,UserDetailActivity.class);
                intent.putExtra("username",data);
                startActivity(intent);*/
            }
        });
    }

    @OnClick(R.id.btn_log)
    public void onViewClicked() {
        login();
    }

    private void login() {
        //登录
        String username = edUser.getText().toString();
//        String username = "2002A_A01";
        String password = "123456";
        EMClient.getInstance().login(username, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("TAG", "登录成功");
                getFriends();
            }

            @Override
            public void onError(int code, String error) {
                Log.e("TAG", "onError:" + error);
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("TAG", "status:" + status);
            }
        });
    }

    private void getFriends() {
        try {
            List<String> allContactsFromServer = EMClient.getInstance().contactManager().getAllContactsFromServer();
            String s = allContactsFromServer.get(0);
            if (s != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvFriend.setText(s);
                    }
                });
            } else {
                tvFailMsg.setText("目前没有好友选项，请去添加聊天吧");
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recvier);
    }
}