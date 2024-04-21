package com.example.mymemo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    @PrimaryKey(autoGenerate = true)
    int entry_id;

    @TypeConverters(DateConverter.class)
    Long date;

    String title;

    String text_content;

    String audio;

    String image;

    int mood;

    int user_id;


    public DiaryEntry(@NonNull Long date, String title, String text_content, int user_id) {
        this.date = date;
        this.title = title;
        this.text_content = text_content;
        this.user_id = user_id;
    }

    @Ignore
    public DiaryEntry(@NonNull Long date, String title, String text_content, String image, String audio, int mood, int user_id) {
        this.date = date;
        this.title = title;
        this.text_content = text_content;
        this.image = image;
        this.audio = audio;
        this.mood = mood;
        this.user_id = user_id;
    }
    @Ignore
    public DiaryEntry(@NonNull Long date, String text_content, int user_id)
    {
        this.date = date;
        this.text_content = text_content;
        this.user_id = user_id;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }
}
