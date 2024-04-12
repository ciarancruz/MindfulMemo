package com.example.mymemo.homescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mymemo.R;
import com.example.mymemo.RecordingDiary;
import com.example.mymemo.diaryscreens.MyDiaryMain;

public class HomeActivity extends AppCompatActivity {

    private ImageView addImageView;
    private ImageView micImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addImageView = findViewById(R.id.add);
        micImageView = findViewById(R.id.mic);


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


    }

    public void navigatetoDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }

    public void navMic(){
        Intent intent = new Intent(this, RecordingDiary.class);
        startActivity(intent);

    }

}