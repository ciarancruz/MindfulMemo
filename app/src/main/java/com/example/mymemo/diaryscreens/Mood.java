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

import com.example.mymemo.AppDatabase;
import com.example.mymemo.R;
import com.example.mymemo.User;

public class Mood extends AppCompatActivity {

    private static final String TAG = "Users";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
            }
        });
        thread.start();

        //skip button option if user does not want to enter mood and tag
        Button skipButton = findViewById(R.id.skip_btn);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to NewEntry class
                Intent intent = new Intent(Mood.this, NewEntry.class);
                intent.putExtra("user", user.getUser_id());
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
