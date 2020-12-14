package com.example.day04.ui.easemob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;

public class FindUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUseridInput;
    private Button mFindBtn;
    private RecyclerView mUsersRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        initView();
    }

    private void initView() {
        mUseridInput = (EditText) findViewById(R.id.input_userid);
        mFindBtn = (Button) findViewById(R.id.btn_find);
        mFindBtn.setOnClickListener(this);
        mUsersRecy = (RecyclerView) findViewById(R.id.recy_users);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find:
                // TODO 20/12/07
                break;
            default:
                break;
        }
    }
}