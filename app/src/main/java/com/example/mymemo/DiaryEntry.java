package com.example.mymemo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(
        tableName = "DiaryEntry",
        foreignKeys =  {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index("user_id")}
)
public class DiaryEntry {
    @PrimaryKey
    @NonNull
    int entry_id;

    @NonNull
    @TypeConverters(DateConverter.class)
    Long date;

    @NonNull
    String title;

    @NonNull
    String text_content;

    @NonNull
    String entry_password;

    @NonNull
    String video;

    @NonNull
    double lon;

    @NonNull
    double lat;

    @NonNull
    int user_id;

    public DiaryEntry(int entry_id,@NonNull Long date, @NonNull String title, @NonNull String text_content, @NonNull String entry_password, @NonNull String video, double lon, double lat) {
        this.entry_id = entry_id;
        this.date = date;
        this.title = title;
        this.text_content = text_content;
        this.entry_password = entry_password;
        this.video = video;
        this.lon = lon;
        this.lat = lat;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getText_content() {
        return text_content;
    }

    public void setText_content(@NonNull String text_content) {
        this.text_content = text_content;
    }

    @NonNull
    public String getEntry_password() {
        return entry_password;
    }

    public void setEntry_password(@NonNull String entry_password) {
        this.entry_password = entry_password;
    }

    @NonNull
    public String getVideo() {
        return video;
    }

    public void setVideo(@NonNull String video) {
        this.video = video;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
