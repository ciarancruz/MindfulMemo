package com.example.mymemo.diaryscreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymemo.R;

public class Mood extends AppCompatActivity {

    private static final String TAG = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

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

    public void okayface(View view) {
        Log.d(TAG, "run: Okay face clicked");
    }

    public void sadface(View view) {
        Log.d(TAG, "run: Sad face clicked");
    }

    public void happyface(View view) {
        Log.d(TAG, "run: Happy face clicked");
    }
}
