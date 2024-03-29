package com.example.mymemo.galleryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mymemo.R;
import com.example.mymemo.diaryscreens.MyDiaryMain;


public class GalleryActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

    }

    public void navigatetoDiary(View view) {
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);
    }
}
