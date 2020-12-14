package com.example.day04.ui.easemob;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;
import com.example.day04.utils.GlideEngine;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_title;
    private RecyclerView recy_chat;
    private EditText input_word;
    private Button btn_send;
    private ImageView send_img;

    private String toUserId;
    private String selfId;

    List<EMMessage> msgsList;
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        //  initDa();
    }

    private void initDa() {

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toUserId);
        //获取此会话的所有消息
        if (conversation.getAllMessages() != null && conversation.getAllMessages().size() > 0) {
            List<EMMessage> messages = conversation.getAllMessages();
            msgsList.addAll(messages);
            chatAdapter.notifyDataSetChanged();
        }

    }

    //网络拷贝的穿透消息文本
    private void sendTouchuan() {
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);

        //支持单聊和群聊，默认单聊，如果是群聊添加下面这行
        //cmdMsg.setChatType(ChatType.GroupChat)
        String action = "action1";//action可以自定义
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        //发送给某个人
        cmdMsg.setTo(toUserId);
        cmdMsg.addBody(cmdBody);
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }

    private void initView() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        recy_chat = (RecyclerView) findViewById(R.id.recy_chat);
        input_word = (EditText) findViewById(R.id.input_word);
        btn_send = (Button) findViewById(R.id.btn_send);
        send_img = findViewById(R.id.send_img);


        btn_send.setOnClickListener(this);
        send_img.setOnClickListener(this);
        initData();

        initMsgListner();


    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("touserid")) {
                toUserId = intent.getStringExtra("touserid");
                txt_title.setText(toUserId);
            }
        }
        selfId = EMClient.getInstance().getCurrentUser();
        recy_chat.setLayoutManager(new LinearLayoutManager(this));
        msgsList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, msgsList, toUserId);
        recy_chat.setAdapter(chatAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                sendMsg();
                input_word.setText("");
                break;
            case R.id.send_img:
                openPhoto();
                break;
        }
    }

    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(9)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for (LocalMedia item : selectList) {
                    sendMsgByImage(item.getPath());
                }
//                sendTouchuan();
                break;
            default:
                break;
        }
    }

    private void sendMsgByImage(String path) {
        Uri uri = Uri.parse(path);
        EMMessage msg = EMMessage.createImageSendMessage(uri, false, toUserId);
        /*EMImageMessageBody body = new EMImageMessageBody(uri);
        msg.addBody(body);*/
        //如果是群聊，设置chattype，默认是单聊
        EMClient.getInstance().chatManager().sendMessage(msg);
        msgsList.add(msg);
        chatAdapter.notifyDataSetChanged();
    }

    private void sendMsg() {
        // validate
        String content = input_word.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        EMMessage message = EMMessage.createTxtSendMessage(content, toUserId);

        //消息状态的监听
        /* message.setMessageStatusCallback(new EMCallBack(){
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(int code, String error) {

            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });*/

        //如果是群聊，设置chattype，默认是单聊
        //message.setChatType(EMMessage.ChatType.GroupChat);

        message.setChatType(EMMessage.ChatType.ChatRoom);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(toUserId);
        int num = conversation.getUnreadMsgCount();
        Intent intent = new Intent();
        intent.setAction("com.a.a");
        intent.putExtra("num", num);
        sendBroadcast(intent);
        msgsList.add(message);
        chatAdapter.notifyDataSetChanged();
        // TODO validate success, do something
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);


    }

    private void initMsgListner() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }
    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            Toast.makeText(ChatActivity.this, "收到", Toast.LENGTH_SHORT).show();
            EMTextMessageBody body = (EMTextMessageBody) messages.get(0).getBody();
            Toast.makeText(ChatActivity.this, body.getMessage() + "", Toast.LENGTH_SHORT).show();
            msgsList.addAll(messages);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chatAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            msgsList.addAll(messages);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chatAdapter.notifyDataSetChanged();
                }
            });

        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
//            EMTextMessageBody body = (EMTextMessageBody) message.get(0).getBody();
//            Toast.makeText(ChatActivity.this, body.getMessage()+"", Toast.LENGTH_SHORT).show();
//            msgsList.addAll(message);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    chatAdapter.notifyDataSetChanged();
//                }
//            });
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动

        }
    };
}