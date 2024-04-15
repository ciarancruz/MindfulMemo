package com.example.mymemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import java.io.File;
import java.io.IOException;

public class RecordingDiary extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder mediaRecorder;
    private String outputFile;
    private ImageView playBtn;
    private ImageView pauseBtn;
    private ImageView recordMic;
    private boolean isRecording;
    private AppDatabase db;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_diary);

        db = Room.databaseBuilder(this, AppDatabase.class, "APP_DB")
                .allowMainThreadQueries()
                .build();

        recordMic = findViewById(R.id.record_mic);
        playBtn = findViewById(R.id.play_btn);
        pauseBtn = findViewById(R.id.pause_btn);

        recordMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRecording)
                {
                    if (ContextCompat.checkSelfPermission(RecordingDiary.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                        // Request the permission
                        ActivityCompat.requestPermissions(RecordingDiary.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
                    }
                    else {
                        startRecording("recorded_audio.mp3");
                        isRecording = true;
                    }
                } else {
                    stopRecording();
                    isRecording = false;
                }

            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording)
                {
                    stopRecording();
                    isRecording = false;
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outputFile != null) {
                    playAudio(outputFile);
                } else {
//                    Toast.makeText(this, "No audio recording found", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void startRecording(String fileName) {
        outputFile = getExternalCacheDir().getAbsolutePath(); // Get the cache directory
        outputFile += "/" + fileName; // Specify file name

        long currentTimeMillis = System.currentTimeMillis();
//        DiaryEntry newDiary = new DiaryEntry(currentTimeMillis, "Audio Recording", outputFile, user.getUser_id());
//        db.diaryEntryDao().insertDiaryEntry(newDiary);

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
            Log.d("AudioRecord", "Recording started. File path: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to start recording", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            try {
                // Stop recording
                mediaRecorder.stop();
            } catch (RuntimeException stopException) {
                // Handle the case when stop fails
                stopException.printStackTrace();
            }
            String filePath = "/storage/emulated/0/Android/data/com.example.mymemo/cache/recorded_audio.mp3";

            playAudio(filePath);
            // Release resources
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
            verifyRecording();
        }
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

    private void verifyRecording() {
        boolean isSaved = isRecordingSaved(outputFile);
        if (isSaved) {
            Log.d("AudioRecord", "Recording saved successfully.");
        } else {
            Log.d("AudioRecord", "Recording not saved properly.");
        }
    }

    private boolean isRecordingSaved(String filePath) {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            long fileSize = file.length();
            return fileSize > 0; // Check if the file size is greater than 0
        } else {
            return false;
        }
    }
}


