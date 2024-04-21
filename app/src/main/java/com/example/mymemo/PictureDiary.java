package com.example.mymemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.mymemo.diaryscreens.MyDiaryMain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureDiary extends AppCompatActivity {

    private final String TAG = "Debug";

    private ImageView backImageView;
    private AppDatabase db;
    private User user;

    // Variables for images
    private String imageLink = "";
    private ImageView imageEdt;
    private EditText textEdt;
    private String title;
    private int mood;

//    // Request Camera
//    ActivityResultLauncher<Intent> cameraRequestLauncher =
//            registerForActivityResult(
//                    new ActivityResultContracts.StartActivityForResult(),
//                    new ActivityResultCallback<ActivityResult>() {
//                        @Override
//                        public void onActivityResult(ActivityResult activityResult) {
//                            int resultCode = activityResult.getResultCode();
//                            Intent data = activityResult.getData();
//
//                            // Reference: https://developer.android.com/training/camera-deprecated/photobasics
//                            if (resultCode == RESULT_OK) {
//                                Bundle extras = data.getExtras();
//                                Bitmap image = (Bitmap)extras.get("data");
//                                Log.d("Debug", "Image link/path" + image);
//                                imageEdt.setImageBitmap(image);
//                                bitmapToURI(image);
//
//                            }
//                            // End reference
//                        }
//                    }
//            );

    // Open gallery
    ActivityResultLauncher<Intent> openGalleryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult activityResult) {
                            int resultCode = activityResult.getResultCode();
                            Intent data = activityResult.getData();

                            if (resultCode == RESULT_OK) {
                                Uri selectedImage = data.getData();
                                imageLink = selectedImage.toString();
                                imageEdt.setImageURI(selectedImage);
                                Log.d(TAG, "Image link: " + imageLink);
                                Log.d(TAG, "path image: " + stringToPath(imageLink));
                                try {
                                    storeImageInDirectory(selectedImage);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_diary);

        backImageView = findViewById(R.id.backbtn1);
        imageEdt = (ImageView) findViewById(R.id.insertedImage);
        textEdt = findViewById(R.id.titleEditText);

        // Instantiate db
        db = Room.databaseBuilder(this, AppDatabase.class, "APP_DB")
                .allowMainThreadQueries()
                .build();

        // Get user id
        Intent intent = getIntent();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int userID = intent.getIntExtra("user",-1);
                user = AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserById(userID);
            }
        });
        thread.start();

        mood = intent.getIntExtra("mood", -1);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PictureDiary.this, HomeActivity.class);
                intent.putExtra("user", user.getUser_id());
                startActivity(intent);
                finish();
            }
        });

    }

    // Taking a photo Reference: https://developer.android.com/training/camera-deprecated/photobasics
    public void pickPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        openGalleryLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
//
//    // Picking a photo from gallery https://developer.android.com/training/data-storage/shared/documents-files
//    public void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
//        openGalleryLauncher.launch(Intent.createChooser(intent, "Select Picture"));
//    }

    // Convert image link to path
    public String stringToPath(String imageLink) {
        String root = getApplication().getExternalFilesDir("").getAbsolutePath();
        String id = imageLink.substring(imageLink.length() - 4, imageLink.length());
        String link = root + "/pictures/diaryPic_" + id + ".jpeg";
        return link;
    }


    // Storing image in a directory for future use
    private void storeImageInDirectory(Uri imageURI) throws IOException {
        // Find root folder
        String root = getApplication().getExternalFilesDir("").getAbsolutePath();
        Log.d(TAG, "" + root);

        // Create a folder in that root folder
        File rootDir = new File(root + "/pictures");
        rootDir.mkdir();

        Bitmap image = MediaStore.Images.Media.getBitmap(
                getApplication().getContentResolver(),
                imageURI
        );

        String stringImageId = imageURI.toString();
        String id = stringImageId.substring(stringImageId.length() - 4, stringImageId.length());
        Log.d(TAG, "Last 4: " + id);

        // Create file to store image
        File myNewImage = new File(root + "/pictures/diaryPic_" + id + ".jpeg");
        Log.d(TAG, "New file: " + myNewImage);

        FileOutputStream out = new FileOutputStream(myNewImage);
        image.compress(
                Bitmap.CompressFormat.JPEG,
                100,
                out
        );
        Log.d(TAG, "Out:" + out);

        out.close();
    }

    public void saveImage(View view) {
        title = textEdt.getText().toString();
        if (!(title.isEmpty())) {
            long currentTimeMillis = System.currentTimeMillis();
            DiaryEntry newDiary = new DiaryEntry(currentTimeMillis, title, null, stringToPath(imageLink), null, mood, user.getUser_id());
            db.diaryEntryDao().insertDiaryEntry(newDiary);
            Log.d(TAG, "Image saved");
            finish();
        }
        else {
            Toast.makeText(PictureDiary.this, "Please enter a title", Toast.LENGTH_SHORT).show();
        }

    }
}