package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mymemo.R;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Register extends AppCompatActivity {

    private static final String TAG = "Users";

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Button to navigate to the calendar page
//        Button buttonGoToCalendar = findViewById(R.id.buttonGoToCalendar);
//        buttonGoToCalendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the calendar activity
//                startActivity(new Intent(Register.this, Calendar.class));
//            }
//        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }



    private void registerUser() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        //validation
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(firstName, lastName, username, password);

        // Insert user into database
        InsertAsyncUser insertAsyncUser = new InsertAsyncUser();
        insertAsyncUser.execute(user);

        // Registration successful
        Toast.makeText(this, "Registered user: " + username, Toast.LENGTH_SHORT).show();
//        testLogin();
        finish();
    }

    class InsertAsyncUser extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {

            AppDatabase.getInstance(getApplicationContext())
                    .userDao()
                    .insertUser(users[0]);
            return null;
        }
    }

//    public void testLogin() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (AppDatabase.getInstance(getApplicationContext())
//                        .userDao()
//                        .getUserById(3) != null) {
//                    User user = AppDatabase.getInstance(getApplicationContext())
//                            .userDao()
//                            .getUserById(3);
//                    Log.d(TAG, "run: " + user.f_name.toString());
//                }
//                else {
//                    Log.d(TAG, "run: Could not find user!");
//                }
//            }
//        });
//        thread.start();
//    }






}