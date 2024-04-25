package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


        buttonLogin = findViewById(R.id.Loginbutton);

        // Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        initializeViews();
    }


    //when user clicks on register link it should lead to register page.
    public void navigatetoRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }

   // when user clicks on back button it leads them to the main page
   public void navigatetoMain(){
            Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);
    }

    private void initializeViews() {
        editTextEmail = findViewById(R.id.EmailAddressText);
        editTextPassword = findViewById(R.id.PasswordText);
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

                    // Check if password matches account
                    if (password.equals(user.getUser_password())) {
                        Intent intent = new Intent(Login.this, HomeActivity.class);
                        intent.putExtra("user", user.getUser_id());
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Login.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                else {
                    Login.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Login.this, "Could not find user", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        thread.start();


    }

}