package com.example.mymemo.diaryscreens;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymemo.R;

public class MyDiaryMain extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }
}


    //public void navigatetoCategory(View view) {
       // Intent intent = new Intent(this, CategoryActivity.class);
       // startActivity(intent);
    //}

    //public void navigatetoMood(View view) {
        //Intent intent = new Intent(this, Mood.class);
        //startActivity(intent);
    //}

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


