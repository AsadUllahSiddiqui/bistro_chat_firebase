package com.example.bistro_chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class create_profile extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
    private static final int PICK_IMAGE=1;
    private StorageReference mStorageref;
    private DatabaseReference mDatabaseRef;
    ProgressBar mProgressbar;
    Uri img;
    EditText first_name,last_name,phone,bio,bithday;
    TextView tmale,tfemale,tother;
    String gender,text1,text2,PHONE,BIO,BIRTHDAY,FIRSTNAME,LASTNAME,GENDER,URI;
    CircleImageView mImageView;
    ImageButton male,female,other,save;
    contact newcontact;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference reference=database.getReference("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        //upload

        mStorageref= FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef=FirebaseDatabase.getInstance().getReference("uploads");

        //
        phone=findViewById(R.id.phone_number);
        bio=findViewById(R.id.bio);
        bithday=findViewById(R.id.birthDay);
        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        male= findViewById(R.id.gender_male);
        female= findViewById(R.id.gender_female);
        other= findViewById(R.id.gender_others);
        tmale= findViewById(R.id.text_male);
        tfemale= findViewById(R.id.text_female);
        tother= findViewById(R.id.text_other);
        save=findViewById(R.id.save);
        Intent intent=getIntent();
         text1=intent.getStringExtra(sign_up.emails);
         text2=intent.getStringExtra(sign_up.ids);
       //first_name.setText(text1);
      // last_name.setText(text2);
        gender_selection();
        configureimage();
        configureButton_save();
    }

    private void configureButton_female(){

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.GREEN);
                tfemale.setTextColor(Color.RED);
                tother.setTextColor(Color.GREEN);
                gender="female";
                Toast.makeText(create_profile.this, "Female",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void configureButton_male(){

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.RED);
                tfemale.setTextColor(Color.GREEN);
                tother.setTextColor(Color.GREEN);
                gender="male";
                Toast.makeText(create_profile.this, "Male",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void configureButton_other(){

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmale.setTextColor(Color.GREEN);
                tfemale.setTextColor(Color.GREEN);
                tother.setTextColor(Color.RED);
                gender="other";
                Toast.makeText(create_profile.this, "Other",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void gender_selection(){
        configureButton_female();
        configureButton_male();
        configureButton_other();

    }
    private void fill_data(){
        String picUri="";
        if(img!=null) {
            picUri = img.toString();
        }
        PHONE=phone.getText().toString();
           BIO=bio.getText().toString();
            BIRTHDAY=bithday.getText().toString();
            FIRSTNAME=first_name.getText().toString();
            LASTNAME=last_name.getText().toString();
            GENDER=gender;
           URI=picUri;
        //contact newcontact=new contact(FIRSTNAME,LASTNAME,PHONE,GENDER,"16",URI,text2,text1,BIO,BIRTHDAY);
          newcontact=new contact(FIRSTNAME,LASTNAME,PHONE
                ,GENDER,"16",URI,text2,text1,BIO,BIRTHDAY);
    }
    private void configureButton_save(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fill_data();
                Toast.makeText(create_profile.this, "save",
                        Toast.LENGTH_SHORT).show();
                reference.push().setValue(newcontact);
                startActivity(new Intent(create_profile.this, contact_container.class));
                finish();
            }
        });
    }

    private void configureimage(){
        mImageView=findViewById(R.id.profile_pic);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                        //permission not granted, request it
                        String[] permission={Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show toast
                        requestPermissions(permission,PERMISSION_CODE);

                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();

                    }

                }
                else {
                    //system os is less than marshmallow
                    pickImageFromGallery();

                }
            }
        });

    }
    private void pickImageFromGallery() {
        //intent to pic image
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this,"Permission denied..!",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    //handle result to pick image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE){
            mImageView.setImageURI(data.getData());
            img= data.getData();
        }
    }

}