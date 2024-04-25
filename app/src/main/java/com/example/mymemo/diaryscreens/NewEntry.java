package com.example.mymemo.diaryscreens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.DiaryEntry;
import com.example.mymemo.HomeActivity;
import com.example.mymemo.R;
import com.example.mymemo.User;


public class NewEntry extends AppCompatActivity {
    private final String TAG = "Debug";
    private AppDatabase db;
    private EditText diaryEntry;
    private EditText title;
    private TextView saveDiary;
    private User user;
    private int mood;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        db = Room.databaseBuilder(this, AppDatabase.class, "APP_DB")
                .allowMainThreadQueries()
                .build();

        Intent intent = getIntent();

        // Get user id
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

        mood = intent.getIntExtra("mood", -1);

        title = findViewById(R.id.titleEditText);
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
        String titleString = title.getText().toString();
        String diary = diaryEntry.getText().toString();

        long currentTimeMillis = System.currentTimeMillis();
        DiaryEntry diaryEntry = new DiaryEntry(currentTimeMillis, titleString, diary, null, null, mood, user.getUser_id());
        db.diaryEntryDao().insertDiaryEntry(diaryEntry);
        Log.d("DiaryEntry", "Added diary to database");

        Intent intent = new Intent(this, MyDiaryMain.class);
        intent.putExtra("user", user.getUser_id());
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
