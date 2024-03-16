package com.example.oblig3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.oblig3.model.ImageEntity;

@Dao
public interface ImageDAO {
    @Insert
    void insert(ImageEntity image);

    @Query("SELECT * FROM image_table")
    LiveData<List<ImageEntity>> getAllImages();

    @Query("DELETE FROM image_table WHERE  id = :id")
    void deleteImageWithId(long id);

    @Query("SELECT * FROM image_table")
    LiveData<List<ImageEntity>> getAllDogs();

    @Query("DELETE FROM image_table")
    void deleteAll();

    @Query("SELECT COUNT(id) FROM image_table")
    int getCountOfImages();
}