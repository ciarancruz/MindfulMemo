package com.example.mymemo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class ViewModal extends AndroidViewModel {
    private DiaryRepository repository;
    private LiveData<List<DiaryEntry>> allDiaries;

    public ViewModal(Application application) {
        super(application);
        repository = new DiaryRepository(application);
        allDiaries = repository.getAllItems();
    }

    public LiveData<List<DiaryEntry>> getAllDiaries() {
        return allDiaries;
    }
}

