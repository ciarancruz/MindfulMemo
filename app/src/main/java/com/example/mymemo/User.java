package com.example.mymemo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    @NonNull
    int user_id;

    @ColumnInfo(name = "Firstname")
    @NonNull
    String f_name;

    @ColumnInfo(name = "Lastname")
    @NonNull
    String l_name;

    @ColumnInfo(name = "email")
    @NonNull
    String email;

    @ColumnInfo(name = "password")
    @NonNull
    String user_password;

    public User(@NonNull String f_name, @NonNull String l_name, @NonNull String email, @NonNull String user_password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.user_password = user_password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @NonNull
    public String getF_name() {
        return f_name;
    }

    public void setF_name(@NonNull String f_name) {
        this.f_name = f_name;
    }

    @NonNull
    public String getL_name() {
        return l_name;
    }

    public void setL_name(@NonNull String l_name) {
        this.l_name = l_name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(@NonNull String user_password) {
        this.user_password = user_password;
    }
}


