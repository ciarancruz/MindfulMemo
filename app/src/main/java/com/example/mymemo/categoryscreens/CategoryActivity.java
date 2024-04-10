package com.example.mymemo.categoryscreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.Login;
import com.example.mymemo.R;
import com.example.mymemo.User;
import com.example.mymemo.diaryscreens.MyDiaryMain;
import com.example.mymemo.galleryscreens.GalleryActivity;

public class CategoryActivity extends AppCompatActivity {

    private Button galleryButton;
    private ImageView addImageView;
    private Button viewButton;
    private TextView helloUser;

    private Button viewFitnessBtn;

    private Button viewStudyBtn;

    private Button viewFamilyBtn;

    private Button viewHobbiesBtn;

    private Button viewMoodBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        galleryButton = findViewById(R.id.gallery_btn);
        addImageView = findViewById(R.id.add);
        viewButton = findViewById(R.id.view_btn);
        viewFitnessBtn = findViewById(R.id.view_Fitness);
        viewStudyBtn = findViewById(R.id.view_Study);
        viewFamilyBtn = findViewById(R.id.view_Family);
        viewHobbiesBtn = findViewById(R.id.view_Hobbies);
        viewMoodBtn = findViewById(R.id.view_Mood);
        helloUser = findViewById(R.id.hello);


        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery();
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatetoDiary();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMeeting();
            }
        });

        viewFitnessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFitnessBtn();
            }
        });

        viewStudyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStudyBtn();
            }
        });

        viewFamilyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navFamily();
            }
        });

        viewHobbiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navHobbie();
            }
        });

        viewMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navMood();
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                int userID = intent.getIntExtra("user",-1);
                User user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
                helloUser.setText("Hello " + user.getF_name() + "!");
            }
        });
        thread.start();


    }

    public void gallery(){
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void navigatetoDiary(){
        Intent intent = new Intent(this, MyDiaryMain.class);
        startActivity(intent);

    }

    public void viewMeeting(){
        Intent intent = new Intent(this, Meetings.class);
        startActivity(intent);
    }

    public void viewFitnessBtn(){
        Intent intent = new Intent(this, Fitness.class);
        startActivity(intent);
    }

    public void viewStudyBtn(){
        Intent intent = new Intent(this, Study.class);
        startActivity(intent);
    }

    public void navFamily(){
        Intent intent = new Intent(this, Family.class);
        startActivity(intent);
    }

    public void navHobbie(){
        Intent intent = new Intent(this, Hobbies.class);
        startActivity(intent);
    }

    public void navMood(){
        Intent intent = new Intent(this, Moods.class);
        startActivity(intent);
    }







}