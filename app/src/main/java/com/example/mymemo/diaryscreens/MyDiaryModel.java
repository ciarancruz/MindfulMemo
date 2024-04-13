package com.example.mymemo.diaryscreens;

public class MyDiaryModel {
    String diaryentryTitle;
    String diaryentryDate;
    int blackLineImage;
    int moreOptionImage;
    int heartImage;


    //contructor for variables declared above
    public MyDiaryModel(String diaryentryTitle, String diaryentryDate, int blackLineImage,
                        int moreOptionImage, int heartImage) {
        this.diaryentryTitle = diaryentryTitle;
        this.diaryentryDate = diaryentryDate;
        this.blackLineImage = blackLineImage;
        this.moreOptionImage = moreOptionImage;
        this.heartImage = heartImage;
    }

    //setting getters for each variable
    public String getDiaryentryTitle() {
        return diaryentryTitle;
    }

    public String getDiaryentryDate() {
        return diaryentryDate;
    }

    public int getBlackLineImage() {
        return blackLineImage;
    }

    public int getMoreOptionImage() {
        return moreOptionImage;
    }

    public int getHeartimage() {
        return heartImage;
    }
}
