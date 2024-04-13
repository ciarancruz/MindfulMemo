package com.example.mymemo.diaryscreens;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.DiaryEntry;
import com.example.mymemo.R;
import com.example.mymemo.User;

import java.util.Calendar;


public class NewEntry extends AppCompatActivity {
    private AppDatabase db;
    private EditText diaryEntry;
    private TextView saveDiary;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        db = Room.databaseBuilder(this, AppDatabase.class, "APP_DB")
                .allowMainThreadQueries()
                .build();

        // Get user id
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

        diaryEntry = findViewById(R.id.typetext);
        saveDiary = findViewById(R.id.save_text);

        saveDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDiary();
            }
        });
    }

    private void AddDiary()
    {
        String diary = diaryEntry.getText().toString();

        long currentTimeMillis = System.currentTimeMillis();
        DiaryEntry diaryEntry = new DiaryEntry(currentTimeMillis, diary, user.getUser_id());
        db.diaryEntryDao().insertDiaryEntry(diaryEntry);
        Log.d("DiaryEntry", "Added diary to database");

        finish();
    }
}
