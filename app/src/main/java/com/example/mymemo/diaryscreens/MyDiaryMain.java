package com.example.mymemo.diaryscreens;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymemo.DiaryEntry;
import com.example.mymemo.R;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.User;
import com.example.mymemo.ViewModal;
import com.example.mymemo.homescreens.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class MyDiaryMain extends AppCompatActivity {
    private User user;
    private DiaryAdapter adapter;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private ViewModal viewModal;

    // User Model
    private int user_ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        // Instantiate database
        db = Room.databaseBuilder(this, AppDatabase.class, "APP_DB")
                .allowMainThreadQueries()
                .build();

        Log.d("Debug", "onCreate: Called");

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
                user_ID = user.getUser_id();
            }
        });
        thread.start();

        // Recycler View
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiaryAdapter();
        recyclerView.setAdapter(adapter);
        viewModal = new ViewModelProvider(this).get(ViewModal.class);

    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModal.getDiaryByUser(user_ID).observe(this, diaryEntries -> {
            Log.d("Debug", "onCreate: userID "+ user_ID);
            Log.d("Debug", "onCreate: diary "+ diaryEntries.size());
            adapter.setDataList(diaryEntries);
        });

    }



    public void navigateToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
        finish();
    }

    public void navigateMood(View view) {
        Intent intent = new Intent(this, Mood.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
    }
}






    //public void showPopupMenu(View view) {
        //PopupMenu popupMenu = new PopupMenu(this, view);
        //popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            //@Override
            //public boolean onMenuItemClick(MenuItem item) {
                //switch (item.getItemId()) {
                    //case R.id.action_edit:
                        // Handle Edit action
                        //return true;
                    //case R.id.action_delete:
                        // Handle Delete action
                        //return true;
                    // Add cases for other menu items if needed
                    //default:
                        //return false;
                //}
            //}
        //});
        //popupMenu.inflate(R.menu.dropdown_diary);
        //popupMenu.show();
    //}


