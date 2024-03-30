package com.example.mymemo.categoryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;

import com.example.mymemo.R;
import com.example.mymemo.diaryscreens.MyDiaryMain;
import com.example.mymemo.galleryscreens.GalleryActivity;

public class CategoryActivity extends AppCompatActivity {

    private Button galleryButton;
    private ImageView addImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        galleryButton = findViewById(R.id.gallery_btn);
        addImageView = findViewById(R.id.add);


        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery();
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatetoDiary();
            }
        });


    }

    public void gallery(){
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void navigatetoDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }






}