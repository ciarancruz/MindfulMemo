package com.example.mymemo.categoryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mymemo.R;
import com.example.mymemo.diaryscreens.MyDiaryMain;

public class Meetings extends AppCompatActivity {

    private ImageView backImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        backImageView = findViewById(R.id.backarrow);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meeting();
            }
        });
    }

    public void meeting(){
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);

    }
}