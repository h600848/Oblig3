package com.example.oblig3.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.oblig3.model.ImageEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Legg til flere entiteter som argumenter til entities hvis databasen din skal inneholde flere tabeller
@Database(entities = {ImageEntity.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ImageDAO imageDao();

    // Singleton mønster for å unngå multiple instanser av databasen åpnes samtidig.
    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    //run database operations async on a background thread
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Opprett databasen her
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "image_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}