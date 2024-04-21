package com.example.mymemo.diaryscreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.HomeActivity;
import com.example.mymemo.R;
import com.example.mymemo.User;

public class EditDiary extends AppCompatActivity {

    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Get user id
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Debug", "in thread: called");
                Intent intent = getIntent();
                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
            }
        });
        thread.start();

    }

    public void navigateToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
        finish();
    }
}
