package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static final String TAG = "Users";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();

        // Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void initializeViews() {
        editTextEmail = findViewById(R.id.EmailAddress);
        editTextPassword = findViewById(R.id.Password);
        buttonLogin = findViewById(R.id.Loginbutton);
    }

    // Login User
    private void login() {

        // Get input from editText views
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // New thread to look for user in database
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // If there is a match in the database
                if (AppDatabase.getInstance(getApplicationContext())
                        .userDao()
                        .getUserByEmail(email) != null) {
                    User user = AppDatabase.getInstance(getApplicationContext())
                            .userDao()
                            .getUserByEmail(email);
                    Log.d(TAG, "run: " + user.getF_name());
                    if (email.equalsIgnoreCase(user.getEmail())) {

                    }

                }
                else {
                    Log.d(TAG, "run: Could not find user!");
                }
            }
        });
        thread.start();

    }

}