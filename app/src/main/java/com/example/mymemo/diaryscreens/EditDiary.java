package com.example.mymemo.diaryscreens;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymemo.AppDatabase;
import com.example.mymemo.HomeActivity;
import com.example.mymemo.PictureDiary;
import com.example.mymemo.R;
import com.example.mymemo.RecordingDiary;
import com.example.mymemo.User;

import java.io.IOException;

public class EditDiary extends AppCompatActivity {

    private User user;
    private final String TAG = "Debug";
    private String title;
    private String textContent;
    private String image;
    private String audio;
    private int mood;

    private TextView titleView;
    private EditText diaryContent;
    private TextView diaryAudioTitle;
    private ImageView diaryAudio;
    private TextView diaryImageTitle;
    private ImageView diaryImage;
    private ImageView diaryMood;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initializeViews();

        Intent intent = getIntent();

        // Get user id
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Debug", "in thread: called");

                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
            }
        });
        thread.start();

        title = intent.getStringExtra("diaryTitle");
        textContent = intent.getStringExtra("diaryText");
        image = intent.getStringExtra("diaryImage");
        audio = intent.getStringExtra("diaryRecording");
        mood = intent.getIntExtra("diaryMood", -1);


        Log.d(TAG, "In diary title: " + title);
        Log.d(TAG, "In diary text: " + textContent);
        Log.d(TAG, "In diary image: " + image);
        Log.d(TAG, "In diary audio: " + audio);
        Log.d(TAG, "In diary mood: " + mood);

        currentDiary();

        diaryAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio(audio);
            }
        });


    }

    private void initializeViews() {
        titleView = findViewById(R.id.titleView);
        diaryContent = findViewById(R.id.diaryText);
        diaryAudio = (ImageView) findViewById(R.id.playRecording);
        diaryImage = (ImageView) findViewById(R.id.diaryImages);
        diaryAudioTitle = findViewById(R.id.textView2);
        diaryImageTitle = findViewById(R.id.textView3);
        diaryMood = (ImageView) findViewById(R.id.moodImage);

    }

    private void currentDiary() {
        titleView.setText(title);
        if(mood == 0) {
            diaryMood.setImageResource(R.drawable.verysad);
        }
        else if(mood == 1) {
            diaryMood.setImageResource(R.drawable.okay);
        }
        else if (mood == 2) {
            diaryMood.setImageResource(R.drawable.happy);
        }
        else {
            diaryMood.setVisibility(View.GONE);
        }

        if(textContent != null) {
            diaryContent.setText(textContent);
        }
        else {
            diaryContent.setVisibility(View.GONE);
        }
        if(image != null) {
            String imageChanged = image;
            Drawable setImage = Drawable.createFromPath(imageChanged);
            diaryImage.setImageDrawable(setImage);
        }
        else {
            diaryImageTitle.setVisibility(View.GONE);
            diaryImage.setVisibility(View.GONE);
        }
        if(audio == null) {
            diaryAudioTitle.setVisibility(View.GONE);
            diaryAudio.setVisibility(View.GONE);
        }
    }

    public String stringToPath(String imageLink) {
        String root = getApplication().getExternalFilesDir("").getAbsolutePath();
        String id = imageLink.substring(imageLink.length() - 4 );
        String link = root + "/pictures/recipes_" + id + ".jpeg";
        return link;
    }

    public void navigateToDiary(View view) {
        Intent intent = new Intent(this, MyDiaryMain.class);
        intent.putExtra("user", user.getUser_id());
        startActivity(intent);
        finish();
    }

    private void playAudio(String filePath) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Playing audio", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
