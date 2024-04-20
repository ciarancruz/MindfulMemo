package com.example.mymemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface DiaryEntryDao {
    @Insert
    void insertDiaryEntry(DiaryEntry diaryEntry);

    @Update
    void updateDiaryEntry(DiaryEntry diaryEntry);

    @Delete
    void deleteDiaryEntry(DiaryEntry diaryEntry);

    @Query("Select * from DiaryEntry where entry_id = :entry_id")
    DiaryEntry getEntryById(int entry_id);

    @Query("Select entry_id from DiaryEntry where date = :date")
    List<Integer> getEntryByDate(Long date);

    @Query("Select * from DiaryEntry where user_id  = :user_id order by date desc")
    LiveData<List<DiaryEntry>> getAllDiaries(int user_id);
}
