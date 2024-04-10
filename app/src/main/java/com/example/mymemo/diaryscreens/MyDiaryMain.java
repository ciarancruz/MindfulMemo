package com.example.mymemo.diaryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.R;
import com.example.mymemo.User;
import com.example.mymemo.categoryscreens.CategoryActivity;

public class MyDiaryMain extends AppCompatActivity {
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

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
    }

    public void navigatetoCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void navigateMood(View view) {
        Intent intent = new Intent(this, Mood.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
        finish();
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


}