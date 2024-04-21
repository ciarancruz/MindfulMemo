package com.example.mymemo.diaryscreens;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymemo.DiaryEntry;
import com.example.mymemo.R;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.User;
import com.example.mymemo.ViewModal;
import com.example.mymemo.HomeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyDiaryMain extends AppCompatActivity {
    private User user;
    private DiaryAdapter adapter;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private ViewModal viewModal;
    private SearchView searchView;

    // User Model
    private int user_ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        searchView = findViewById(R.id.search);
        @SuppressLint("RestrictedApi") SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);

        int textSizeInSp = 12;
        searchAutoComplete.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeInSp);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });


        // Instantiate database
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



        // Deleting entries
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


        adapter.setOnItemClickListener(new DiaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DiaryEntry diary) {
                Intent intent = new Intent(MyDiaryMain.this, EditDiary.class);
                intent.putExtra("user", user.getUser_id());
                intent.putExtra("diaryTitle", diary.getTitle());
                intent.putExtra("diaryText", diary.getText_content());
                intent.putExtra("diaryRecording", diary.getAudio());
                intent.putExtra("diaryImage", diary.getImage());
                intent.putExtra("diaryMood", diary.getMood());
                startActivity(intent);
                finish();
            }
        });
    }

    private void filterList(String input) {
        List<DiaryEntry> filteredList = new ArrayList<>();

        try {
            if (!input.isEmpty()) { // If user is searching
                viewModal.getDiaryByUser(user_ID).observe(this, diaryEntries -> {
                    filteredList.clear(); // Clear previous results
                    for (DiaryEntry entry : diaryEntries) {
                        if (entry.getTitle().toLowerCase().contains(input.toLowerCase())) {
                            filteredList.add(entry);
                        }
                    }
                    if (filteredList.isEmpty()) {
                        Toast.makeText(this, "No matching diaries found", Toast.LENGTH_SHORT).show();
                    }
                    adapter.setDataList(filteredList);
                });
            } else { // If search input is empty, show all diaries related to the users id
                viewModal.getDiaryByUser(user_ID).observe(this, diaryEntries -> {
                    filteredList.clear(); // Clear previous results
                    filteredList.addAll(diaryEntries);
                    adapter.setDataList(filteredList);
                });
            }
        } catch (Exception e) {
            // Handle exceptions
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModal.getDiaryByUser(user_ID).observe(this, diaryEntries -> {
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
        intent.putExtra("page", 0);
        startActivity(intent);
    }
}


