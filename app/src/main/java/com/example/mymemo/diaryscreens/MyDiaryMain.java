package com.example.mymemo.diaryscreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymemo.Calendar;
import com.example.mymemo.DiaryEntry;
import com.example.mymemo.R;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.User;
import com.example.mymemo.ViewModal;
import com.example.mymemo.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.allDiary);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.allDiary) {
                return true;
            } else if (itemId == R.id.calender) {
                startActivity(new Intent(getApplicationContext(), Calendar.class));
                finish();
                return true;
            } else {
                return false;
            }
        });

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


        // Deleting Recipes
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // Swiping on an item deletes it from database
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                DiaryEntry deletedDiary = adapter.getDiaryAt(position);

                // Remove item from the RecyclerView
                adapter.deleteDiary(position);

                // Delete item from the database
                db.diaryEntryDao().deleteDiaryEntry(deletedDiary);
                Toast.makeText(MyDiaryMain.this, "Diary deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
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


