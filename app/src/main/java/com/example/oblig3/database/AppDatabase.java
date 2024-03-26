package com.example.oblig3.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.oblig3.model.ImageEntity;

@Database(entities = {ImageEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ImageDAO imageDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Oppretter databasen her
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "image_database")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "image_database").build();
                }
            }
        }
        return INSTANCE;
    }

    // Definere migrasjonen fra versjon 1 til versjon 2
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Her ville du skrevet SQL-kode for å oppdatere skjemaet, om nødvendig
            // Eksempel: database.execSQL("ALTER TABLE ImageEntity ADD COLUMN new_column INTEGER DEFAULT 0 NOT NULL");
        }
    };
}