package com.example.mymemo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tag")
public class Tag {
    @PrimaryKey
    @NonNull
    int tag_id;

    @NonNull
    String description;

    public Tag(int tag_id, @NonNull String description) {
        this.tag_id = tag_id;
        this.description = description;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
