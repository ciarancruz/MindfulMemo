package com.example.mymemo.homescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.PictureDiary;
import com.example.mymemo.R;
import com.example.mymemo.RecordingDiary;
import com.example.mymemo.User;
import com.example.mymemo.diaryscreens.MyDiaryMain;

public class HomeActivity extends AppCompatActivity {

    private ImageView addImageView;
    private ImageView micImageView;
    private ImageView cameraImageview;
    private TextView helloText;

    private final String TAG = "Debug";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addImageView = findViewById(R.id.add);
        micImageView = findViewById(R.id.mic);
        cameraImageview = findViewById(R.id.camera);
        helloText = findViewById(R.id.hello);

        // Get user id
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
                helloText.setText("Hello " + user.getF_name() + "!");

            }
        });
        thread.start();

//        Log.d(TAG, "onCreate: "+ user.getF_name());

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatetoDiary();
            }
        });

        micImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navMic();
            }
        });

        cameraImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navPic();
            }
        });


    }

    public void navigatetoDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);

    }

    public void navMic(){
        Intent intent = new Intent(this, RecordingDiary.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
    }

    public void navPic(){
        Intent intent = new Intent(this, PictureDiary.class);
        startActivity(intent);
    }

}