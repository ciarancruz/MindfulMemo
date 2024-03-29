package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymemo.categoryscreens.CategoryActivity;

import java.util.Locale;

public class Login extends AppCompatActivity {

    private EditText EmailAddressText;
    private EditText PasswordText;

    private Button Loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailAddressText = findViewById(R.id.EmailAddressText);
        PasswordText = findViewById(R.id.PasswordText);
        Loginbutton = findViewById(R.id.Loginbutton);

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginUser();}
        });
    }

    private void loginUser() {
        String useremail = EmailAddressText.getText().toString().trim();
        String password = PasswordText.getText().toString().trim();

        //to ensure user enters their details before logging in
        if (useremail.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        //to check if the user already exists (in other words check database if user details is stored)
        //if details found in database user can login amd leads to the category page a.k.a home screen
        //if not found user should register.
    }

    //when user has successfully logged in.
    public void navigatetoCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }


    //when user clicks on register link it should lead to register page.
    public void navigatetoRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    // when user clicks on back button it leads them to the main page
    public void navigatetoMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}