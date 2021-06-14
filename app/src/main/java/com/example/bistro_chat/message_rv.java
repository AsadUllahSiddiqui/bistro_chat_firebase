package com.example.bistro_chat;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class message_rv extends RecyclerView.Adapter<message_rv.MyViewHolder> {
    ArrayList<message> ls;
    Context c;
    String img;
    public static final int r1 = 0;
    public static final int r2 = 1;

    public message_rv(ArrayList<message> ls, Context c, String img) {
        this.c = c;
        this.ls = ls;
        this.img = img;
    }

    @NonNull
    @Override
    public message_rv.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == r1) {
            View itemRow = LayoutInflater.from(c).inflate(R.layout.msg_right, parent, false);
            return new MyViewHolder(itemRow);
        } else {
            View itemRow = LayoutInflater.from(c).inflate(R.layout.msg_left, parent, false);
            return new MyViewHolder(itemRow);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contact_img.setImageURI(Uri.parse(img));
        holder.msg.setText(ls.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView msg;
        CircleImageView contact_img;

        public MyViewHolder(@NonNull View itemRow) {
            super(itemRow);
            contact_img = itemRow.findViewById(R.id.msngr_pic);
            msg = itemRow.findViewById(R.id.mmm);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (ls.get(position).getSender().equals(profile.myData.getId())) {
            return r1;
        } else {
            return r2;
        }
    }
}