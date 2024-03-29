package com.example.mymemo.diaryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mymemo.R;
import com.example.mymemo.categoryscreens.CategoryActivity;

public class MyDiaryMain extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }

    public void navigatetoCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void navigatetoMood(View view) {
        Intent intent = new Intent(this, Mood.class);
        startActivity(intent);
    }
}
