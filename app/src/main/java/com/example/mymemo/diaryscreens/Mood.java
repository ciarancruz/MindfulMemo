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
import com.example.mymemo.HomeActivity;
import com.example.mymemo.Login;
import com.example.mymemo.PictureDiary;
import com.example.mymemo.R;
import com.example.mymemo.RecordingDiary;
import com.example.mymemo.User;

public class Mood extends AppCompatActivity {

    private static final String TAG = "Users";
    private User user;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        Intent intent = getIntent();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
            }
        });
        thread.start();

        // Determine what page to redirect to
        page = intent.getIntExtra("page", -1);

        //skip button option if user does not want to enter mood and tag
        Button skipButton = findViewById(R.id.skip_btn);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to NewEntry class
                Intent intent;
                if(page == 0) {
                    intent = new Intent(Mood.this, NewEntry.class);
                }
                else if(page == 1) {
                    intent = new Intent(Mood.this, RecordingDiary.class);
                }
                else {
                    intent = new Intent(Mood.this, PictureDiary.class);
                }
                intent.putExtra("user", user.getUser_id());
                intent.putExtra("mood", 3);
                startActivity(intent);
                finish();
            }
        });

    }

    public void okayface(View view) {
        Toast.makeText(Mood.this, "Okay", Toast.LENGTH_SHORT).show();
        Intent intent;
        if(page == 0) {
            intent = new Intent(Mood.this, NewEntry.class);
        }
        else if(page == 1) {
            intent = new Intent(Mood.this, RecordingDiary.class);
        }
        else {
            intent = new Intent(Mood.this, PictureDiary.class);
        }
        intent.putExtra("user", user.getUser_id());
        intent.putExtra("mood", 1);
        startActivity(intent);
        finish();
    }

    public void sadface(View view) {
        Toast.makeText(Mood.this, "Sad", Toast.LENGTH_SHORT).show();
        Intent intent;
        if(page == 0) {
            intent = new Intent(Mood.this, NewEntry.class);
        }
        else if(page == 1) {
            intent = new Intent(Mood.this, RecordingDiary.class);
        }
        else {
            intent = new Intent(Mood.this, PictureDiary.class);
        }
        intent.putExtra("user", user.getUser_id());
        intent.putExtra("mood", 0);
        startActivity(intent);
        finish();
    }

    public void happyface(View view) {
        Toast.makeText(Mood.this, "Happy", Toast.LENGTH_SHORT).show();
        Intent intent;
        if(page == 0) {
            intent = new Intent(Mood.this, NewEntry.class);
        }
        else if(page == 1) {
            intent = new Intent(Mood.this, RecordingDiary.class);
        }
        else {
            intent = new Intent(Mood.this, PictureDiary.class);
        }
        intent.putExtra("user", user.getUser_id());
        intent.putExtra("mood", 2);
        startActivity(intent);
        finish();
    }

    public void backToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
        finish();
    }
}
