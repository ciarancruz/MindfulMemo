package com.example.mymemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, DiaryEntry.class, DiaryTag.class, Tag.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();
    public abstract DiaryEntryDao diaryEntryDao();
    public abstract DiaryTagDao diaryTagDao();
    public abstract TagDao tagDao();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext()
                            , AppDatabase.class, "APP_DB")
//                            .addMigrations(MIGRATION_1_2) // Add migration strategy
                            .build();
                }
            }
        }
        return INSTANCE;
    }

//    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            // Since we're just increasing the version number without any schema changes,
//            // there's no need for explicit migration code.
//            // Room will handle the migration for us.
//        }
//    };

}
