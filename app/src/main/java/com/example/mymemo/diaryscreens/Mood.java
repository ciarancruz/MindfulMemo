package com.example.mymemo.diaryscreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymemo.R;

public class Mood extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        //when user clicks happy image on mood
        ImageView happyIcon = findViewById(R.id.happyicon);

        happyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message when the image is clicked
                Toast.makeText(Mood.this, "Happy face clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //skip button option if user does not want to enter mood and tag
        Button skipButton = findViewById(R.id.skip_btn);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to NewEntry class
                Intent intent = new Intent(Mood.this, NewEntry.class);
                startActivity(intent);
            }
        });
    }
}
