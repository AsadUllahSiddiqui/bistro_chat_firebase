package com.example.bistro_chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatting_terminal extends AppCompatActivity {
    public static final String first_namej = "first_name";
    public static final String agej = "email";
    public static final String numberj = "number";
    public static final String genderj = "gender";
    public static final String urij = "uri";
    public static final String bioj = "bio";
    String pname, page, ppic, pnumber, pbio;
    Intent i, ii;
    ImageButton send, back;
    Button check_profile;
    RecyclerView rv;
    String IImg;
    message_rv messageAdapter;
    RecyclerView recyclerview;
    ArrayList<message> mchat;
    TextView contact_name, status;
    EditText msgET;
    CircleImageView img;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference("chatting");
    String rec, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_terminal);
        chat_terminal_setup();

        readMessages(profile.myData.getId(), rec, "");
        setRecyclerview_configuration();
        configure_send_Button();
        configure_click_here();
        configure_check_profile();
    }

    public void configure_send_Button() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String currentDateTimeString = sdf.format(d);
                message msg = new message(profile.myData.getId(), rec, msgET.getText().toString(), currentDateTimeString);
                reference.push().setValue(msg);
                mchat.clear();
                messageAdapter.notifyDataSetChanged();

            }
        });
    }

    private void readMessages(final String myid, final String userid, String imgurl) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //mchat.add(snapshot.getValue(message.class));
                    message msg = snapshot.getValue(message.class);
                    if (msg.getReceiver().equals(myid) && msg.getSender().equals(userid) ||
                            msg.getReceiver().equals(userid) && msg.getSender().equals(myid)) {
                        mchat.add(msg);
                        messageAdapter.notifyDataSetChanged();
                    } else {
                        messageAdapter = new message_rv(mchat, chatting_terminal.this, IImg);
                        recyclerview.setAdapter(messageAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void setRecyclerview_configuration() {
        //recyclerview
        recyclerview = findViewById(R.id.chat_terminal_rv);
        recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerview.setLayoutManager(linearLayoutManager);
        message m1 = new message("asad",
                "unBfQa4VjhcYnOY4OkaqWpysuns1", "m1");
        message m2 = new message("unBfQa4VjhcYnOY4OkaqWpysuns1",
                "V6PG3VYoopex4vbTMJwaI75YqiT2", "m2");
        message m3 = new message("asad",
                "unBfQa4VjhcYnOY4OkaqWpysuns1", "m3");
        // mchat.add(m1);
        // mchat.add(m2);
        //  mchat.add(m3);
        rv = findViewById(R.id.chat_terminal_rv);
        messageAdapter = new message_rv(mchat, chatting_terminal.this, IImg);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(messageAdapter);
        //messageAdapter=new message_rv(mchat,chatting_terminal.this,IImg);
        recyclerview.setAdapter(messageAdapter);
    }

    public void chat_terminal_setup() {
        msgET = findViewById(R.id.msgET);
        mchat = new ArrayList<>();
        send = findViewById(R.id.send);
        i = getIntent();
        contact_name = findViewById(R.id.contact_name);
        status = findViewById(R.id.status);
        img = findViewById(R.id.profile_img);
        contact_name.setText(i.getStringExtra(contact_rv.first_namex));
        status.setText(i.getStringExtra(contact_rv.genderx));
        IImg = i.getStringExtra(contact_rv.urix);
        Uri uri = Uri.parse(IImg);
        img.setImageURI(uri);
        rec = i.getStringExtra(contact_rv.idx);
        ii = new Intent(chatting_terminal.this, contact_profile.class);
        ii.putExtra(first_namej, i.getStringExtra(contact_rv.first_namex));
        ii.putExtra(numberj, i.getStringExtra(contact_rv.numberx));
        ii.putExtra(agej, i.getStringExtra(contact_rv.agex));
        ii.putExtra(genderj, i.getStringExtra(contact_rv.genderx));
        ii.putExtra(bioj, i.getStringExtra(contact_rv.biox));
        ii.putExtra(urij, i.getStringExtra(contact_rv.urix));

    }

    private void configure_click_here() {
        back = findViewById(R.id.user_chat_back_Button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                startActivity(new Intent(chatting_terminal.this, chat_container.class));
                finish();
            }
        });
    }

    private void configure_check_profile() {
        check_profile = findViewById(R.id.checkprofile);
        check_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(ii);
                finish();
            }
        });
    }
}