package com.example.mymemo;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class DiaryRepository {
    private DiaryEntryDao diaryEntryDao;
    private LiveData<List<DiaryEntry>> allDiaries;

    public DiaryRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        diaryEntryDao = db.diaryEntryDao();
    }

    public LiveData<List<DiaryEntry>> getDiaryByUser(int user_id) {
        return diaryEntryDao.getAllDiaries(user_id);
    }
}
