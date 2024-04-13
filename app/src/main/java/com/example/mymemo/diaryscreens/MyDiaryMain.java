package com.example.mymemo.diaryscreens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymemo.R;
import android.content.Intent;
import android.view.View;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.User;
import com.example.mymemo.homescreens.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class MyDiaryMain extends AppCompatActivity {
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

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

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("Item " + (i + 1));
        }

        DiaryAdapter adapter = new DiaryAdapter(dataList);
        recyclerView.setAdapter(adapter);
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


