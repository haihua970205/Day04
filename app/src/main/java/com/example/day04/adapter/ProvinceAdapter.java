package com.example.day04.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day04.R;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> list;


    public ProvinceAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderCity(LinearLayout.inflate(context, R.layout.city_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderCity city = (ViewHolderCity) holder;
       city.txt_city.setText(list.get(position));

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myIO.IOClickListener(position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  class ViewHolderCity extends RecyclerView.ViewHolder{
        public View rootView;
        public TextView txt_city;

        public ViewHolderCity(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_city = (TextView) rootView.findViewById(R.id.txt_city);
        }
    }

    private MyIOClickListener myIO;

    public void setMyIOClickListener(MyIOClickListener myIO) {
        this.myIO = myIO;
    }

    public interface MyIOClickListener {
        void IOClickListener(int pos);
    }
}
