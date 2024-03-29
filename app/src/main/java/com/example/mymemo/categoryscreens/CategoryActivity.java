package com.example.mymemo.categoryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.mymemo.DiaryActivity;
import com.example.mymemo.GalleryActivity;
import com.example.mymemo.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Button addBtn = findViewById(R.id.add_btn);
        Button galleryBtn = findViewById(R.id.gallery_btn);
        Button viewBtn = findViewById(R.id.view_btn);
        Button viewBtn2 = findViewById(R.id.view_btn_2);


        addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, DiaryActivity.class);
            startActivity(intent);

        });

        galleryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, GalleryActivity.class);
            startActivity(intent);
        });

        viewBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, Meetings.class);
            startActivity(intent);
        });

        viewBtn2.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, Fitness.class);
            startActivity(intent);
        });


    }

}