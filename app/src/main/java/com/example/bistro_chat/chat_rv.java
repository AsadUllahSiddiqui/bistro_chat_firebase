package com.example.bistro_chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_rv extends RecyclerView.Adapter<chat_rv.MyViewHolder> {
    ArrayList<contact> ls;
    Context c;
    public static final String first_namey = "first_name";
    public static final String emaily = "email";
    public static final String numbery = "number";
    public static final String gendery = "gender";
    public static final String idy = "id";
    public static final String uriy = "uri";

    public chat_rv(ArrayList<contact> ls, Context c) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public chat_rv.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(c).inflate(R.layout.chat_row, parent, false);
        return new MyViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contact_img.setImageURI(Uri.parse(ls.get(position).getPicture()));
        holder.name.setText(ls.get(position).getName());
        holder.msg.setText(ls.get(position).getMsg());
        holder.time.setText(ls.get(position).getTime());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, ls.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c, chatting_terminal.class);
                intent.putExtra(first_namey, ls.get(position).getName());
                intent.putExtra(emaily, ls.get(position).getEmail());
                intent.putExtra(numbery, ls.get(position).getNumber());
                intent.putExtra(gendery, ls.get(position).getGender());
                intent.putExtra(idy, ls.get(position).getId());
                intent.putExtra(uriy, ls.get(position).getPicture());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, msg, time;
        CircleImageView contact_img;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemRow) {
            super(itemRow);
            contact_img = itemRow.findViewById(R.id.Chat_contact_img);
            name = itemRow.findViewById(R.id.Chat_Name);
            msg = itemRow.findViewById(R.id.Chat_message);
            time = itemRow.findViewById(R.id.Chat_time);
            ll = itemView.findViewById(R.id.chatk_row);

        }
    }

    public void filterList(ArrayList<contact> filteredList) {
        ls = filteredList;
        notifyDataSetChanged();
    }
}