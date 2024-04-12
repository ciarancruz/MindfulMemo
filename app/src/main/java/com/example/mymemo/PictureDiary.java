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

public class PictureDiary extends AppCompatActivity {

    private ImageView backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_diary);

        backImageView = findViewById(R.id.backbtn1);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navDiary();
            }
        });

    }

    public void navDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }
}