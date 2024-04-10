package com.example.mymemo.homescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mymemo.R;
import com.example.mymemo.diaryscreens.MyDiaryMain;

public class HomeActivity extends AppCompatActivity {

    private ImageView addImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addImageView = findViewById(R.id.add);

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatetoDiary();
            }
        });

    }

    public void navigatetoDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }
}