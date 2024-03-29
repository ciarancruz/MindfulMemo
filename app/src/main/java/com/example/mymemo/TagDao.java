package com.example.mymemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TagDao {
    @Insert
    void insertTag(Tag tag);

    @Update
    void updateTag(Tag tag);

    @Delete
    void deleteTag(Tag tag);

    @Query("Select * from Tag where tag_id = :tag_id")
    Tag getTagById(int tag_id);
}
