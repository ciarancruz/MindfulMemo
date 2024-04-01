package com.example.mymemo.galleryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mymemo.R;
import com.example.mymemo.categoryscreens.CategoryActivity;
import com.example.mymemo.diaryscreens.MyDiaryMain;


public class GalleryActivity extends AppCompatActivity{

    private Button categoryButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        categoryButton = findViewById(R.id.category);

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navCategory();
            }
        });

    }

    public void navCategory(){
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }



    public void navigatetoDiary(View view) {
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);
    }
}
