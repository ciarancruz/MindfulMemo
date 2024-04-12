package com.example.mymemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mymemo.diaryscreens.MyDiaryMain;

public class RecordingDiary extends AppCompatActivity {

    private ImageView backImageView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_diary);

        backImageView = findViewById(R.id.backbtn1);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBack();
            }
        });
    }


    public void navBack(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }
}
