package com.example.mymemo.categoryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.mymemo.DiaryActivity;
import com.example.mymemo.GalleryActivity;

import android.view.View;

import com.example.mymemo.MainActivity;

import com.example.mymemo.R;
import com.example.mymemo.diaryscreens.MyDiaryMain;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//
//        Button galleryBtn = findViewById(R.id.gallery_btn);
//        Button viewBtn = findViewById(R.id.view_btn);
//
//
//
//
//        galleryBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(CategoryActivity.this, GalleryActivity.class);
//            startActivity(intent);
//        });
//
//        viewBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(CategoryActivity.this, Meetings.class);
//            startActivity(intent);
//        });




    }

//    public void navigatetoDiary(View view) {
//        Intent intent = new Intent(this, MyDiaryMain.class);
//        startActivity(intent);
//    }


}