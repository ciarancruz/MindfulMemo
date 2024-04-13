package com.example.mymemo.diaryscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.mymemo.R;
import com.example.mymemo.categoryscreens.CategoryActivity;

import java.util.ArrayList;

public class MyDiaryMain extends AppCompatActivity {

    //private boolean isImage1 = true;
    //private ImageButton imageButton;

    //creating a model array list to store the data for the cardview layout
    //to send to recycler view adapter
    ArrayList<MyDiaryModel> myDiaryModels = new ArrayList<>();
    int[] dividerlineimage = {R.drawable.line2};
    int[] moreoptimage = {R.drawable.blackdots};

    int[] likeimage = {R.drawable.heart};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        RecyclerView recyclerView = findViewById(R.id.diary_recyclerview);

        //calling the setupMyDiaryModels() method to display on the page.
        setupMyDiaryModels();

        MD_RecyclerviewAdapter adapter = new MD_RecyclerviewAdapter(this,
                myDiaryModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ImageButton imageButton = findViewById(R.id.more_options);

        //imageButton = findViewById(R.id.like_diary);


    }

    //method to be able to create model classes for each item in recycler view
    //so we need a method to store each item in an array within main of diary page.
    private void setupMyDiaryModels() {
        String[] diaryTitleNames = getResources().getStringArray(R.array.Title_Diary);
        String[] diaryDate = getResources().getStringArray(R.array.Date_Diary);

        //to iterate over each item and store the data.
        for(int i = 0; i < diaryTitleNames.length; i++){
            myDiaryModels.add(new MyDiaryModel(diaryTitleNames[i],
                    diaryDate[i],
                    dividerlineimage[i],
                    moreoptimage[i],
                    likeimage[i]));

        }
    }

    //public void changeImage(View view) {
        //if (isImage1) {
            //imageButton.setImageResource(R.drawable.heart);
        //} else {
            //imageButton.setImageResource(R.drawable.liked_button);
        //}
        //isImage1 = !isImage1;
    //}

    public void navigatetoCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void navigateMood(View view) {
        Intent intent = new Intent(this, Mood.class);
        startActivity(intent);
        finish();
    }


    //public boolean onCreateOptionsMenu(Menu menu) {

        //MenuInflater menuInflater = new MenuInflater(this);
        //menuInflater.inflate(R.menu.dropdown_diary, menu);
        //return true;
    //}
    //ImageButton imageButton = findViewById(R.id.more_options);
    //public void showPopupOption(View v) {
        //PopupMenu popup = new PopupMenu(this, v);
        //popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            //@Override
            //public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item clicks
                //switch (item.getItemId()) {
                    //case R.id.action_edit:
                        // Handle item 1 click
                        // Directly creating and displaying the toast message
                        //Toast.makeText(getApplicationContext(), "Edit action clicked!", Toast.LENGTH_SHORT).show();
                        //return true;


                   // case R.id.action_delete:
                        // Handle item 2 click
                        //Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                        //return true;

                //}
            //}
        //});
        //popup.inflate(R.menu.dropdown_diary);
        //popup.show();
    //}




}