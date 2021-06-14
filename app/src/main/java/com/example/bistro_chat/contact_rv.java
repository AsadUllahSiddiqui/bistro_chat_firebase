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

public class contact_rv extends RecyclerView.Adapter<contact_rv.MyViewHolder> {
    ArrayList<contact> ls;
    Context c;
    public static final String first_namex = "first_name";
    public static final String emailx = "email";
    public static final String numberx = "number";
    public static final String genderx = "gender";
    public static final String idx = "id";
    public static final String urix = "uri";
    public static final String biox = "bio";
    public static final String agex = "age";

    public contact_rv(ArrayList<contact> ls, Context c) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public contact_rv.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(c).inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contact_img.setImageURI(Uri.parse(ls.get(position).getPicture()));
        holder.name.setText(ls.get(position).getName());
        holder.gender.setText(ls.get(position).getGender());
        holder.age.setText(ls.get(position).getAge());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, ls.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c, chatting_terminal.class);
                intent.putExtra(first_namex, ls.get(position).getName());
                intent.putExtra(emailx, ls.get(position).getEmail());
                intent.putExtra(numberx, ls.get(position).getNumber());
                intent.putExtra(genderx, ls.get(position).getGender());
                intent.putExtra(idx, ls.get(position).getId());
                intent.putExtra(urix, ls.get(position).getPicture());
                intent.putExtra(biox, ls.get(position).getBio());
                intent.putExtra(agex, ls.get(position).getAge());

                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, gender, age;
        CircleImageView contact_img;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemRow) {
            super(itemRow);
            contact_img = itemRow.findViewById(R.id.contact_img);
            name = itemRow.findViewById(R.id.name);
            age = itemRow.findViewById(R.id.age);
            gender = itemRow.findViewById(R.id.gender);
            ll = itemView.findViewById(R.id.row);
        }
    }

    public void filterList(ArrayList<contact> filteredList) {
        ls = filteredList;
        notifyDataSetChanged();
    }
}
