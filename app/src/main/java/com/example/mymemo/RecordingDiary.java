package com.example.mymemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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
    private ImageView backBtn;
    private boolean isRecording;
    private AppDatabase db;
    private User user;
    private TextView timer;
    private Handler handler;
    private Runnable timerRunnable;
    private long startTimeMillis;


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
        backBtn = findViewById(R.id.backbtn1);
        timer = findViewById(R.id.recording_time);

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
                        startRecording();
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
                    Toast.makeText(RecordingDiary.this, "No audio recording found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

        handler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
                int seconds = (int) (elapsedTimeMillis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                String timeString = String.format("%02d:%02d", minutes, seconds);
                timer.setText(timeString);

                handler.postDelayed(this, 1000); // Update every second
            }
        };
    }

    private void startRecording() {
        String fileName = "Recording_" + System.currentTimeMillis() + ".mp3";
        outputFile = getExternalCacheDir().getAbsolutePath() + "/" + fileName;

        long currentTimeMillis = System.currentTimeMillis();
        DiaryEntry newDiary = new DiaryEntry(currentTimeMillis, "Audio Recording", null, null, outputFile, user.getUser_id());
        db.diaryEntryDao().insertDiaryEntry(newDiary);

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1); // Mono audio
        mediaRecorder.setAudioEncodingBitRate(128000); // Bit rate 128kbps
        mediaRecorder.setAudioSamplingRate(44100); // Sample rate 44.1 kHz
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

        startTimeMillis = System.currentTimeMillis();
        handler.postDelayed(timerRunnable, 0);
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
            handler.removeCallbacks(timerRunnable);
        }
    }

    private void playAudio(String filePath) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        timer.setText("00:00");

        startTimeMillis = System.currentTimeMillis();
        handler.postDelayed(timerRunnable, 0);
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Playing audio", Toast.LENGTH_SHORT).show();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Stop timer when audio playback finishes
                    handler.removeCallbacks(timerRunnable); // Stop updating timer
                }
            });
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


