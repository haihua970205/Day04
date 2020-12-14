package com.example.day04.ui.easemob;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.day04.R;
import com.hyphenate.chat.EMImageMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<EMMessage> msgsList;
    private String toUserId;

    public ChatAdapter(Context context, List<EMMessage> msgsList, String toUserId) {
        this.context = context;
        this.msgsList = msgsList;
        this.toUserId = toUserId;
    }


    public static final int TYPE_MY = 0;
    public static final int TYPE_YOU = 1;

    @Override
    public int getItemViewType(int position) {
        if (!toUserId.equals(msgsList.get(position).getFrom())) {
            return TYPE_MY;
        } else {
            return TYPE_YOU;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_MY) {
            View root = LayoutInflater.from(context).inflate(R.layout.myuser_item, parent, false);
            return new ViewShow(root);
        } else {
            View root = LayoutInflater.from(context).inflate(R.layout.youuser_item, parent, false);
            return new ViewShow(root);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewShow show = (ViewShow) holder;
        EMMessage emMessage = msgsList.get(position);
        if (emMessage.getType() == EMMessage.Type.TXT) {
            show.tv_title_show.setVisibility(View.VISIBLE);
            show.iv_image_show.setVisibility(View.GONE);

            EMTextMessageBody body = (EMTextMessageBody) emMessage.getBody();
            String message = body.getMessage();
            show.tv_title_show.setText(message);
        } else if (emMessage.getType() == EMMessage.Type.IMAGE) {
            show.iv_image_show.setVisibility(View.VISIBLE);
            show.tv_title_show.setVisibility(View.GONE);

            EMImageMessageBody imgBody = (EMImageMessageBody) emMessage.getBody();
            //获取图片缩略图在服务器的路径

            //获取图片文件在服务器的路径
            String imgRemoteUrl = imgBody.getRemoteUrl();
            //获取图片缩略图在服务器的路径
            String thumbnailUrl = imgBody.getThumbnailUrl();
            //本地图片文件的资源路径
            Uri imgLocalUri = imgBody.getLocalUri();
            //本地图片缩略图资源路径
            Uri thumbnailLocalUri = imgBody.thumbnailLocalUri();
            String path = imgBody.getThumbnailUrl();

            if (!toUserId.equals(msgsList.get(position).getFrom())) {
                Glide.with(context).load(thumbnailLocalUri).into(show.iv_image_show);
            } else {
                Glide.with(context).load(path).into(show.iv_image_show);
            }


        }

    }

    @Override
    public int getItemCount() {
        return msgsList.size();
    }

    public class ViewShow extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title_show;
        private ImageView iv_image_show;

        public ViewShow(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title_show = (TextView) rootView.findViewById(R.id.tv_word);
            this.iv_image_show = (ImageView) rootView.findViewById(R.id.iv_msg);
        }
    }
}
