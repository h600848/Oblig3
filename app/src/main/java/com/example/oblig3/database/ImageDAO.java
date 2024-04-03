package com.example.oblig3.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.oblig3.model.ImageEntity;

@Dao
public interface ImageDAO {
    @Insert
    void insertImage(ImageEntity image);

    @Query("SELECT * FROM image_table WHERE imageId = :id")
    List<ImageEntity> findImageById(int id);

    @Delete
    void delete (ImageEntity image);

//    Fjernet denne foreløpig
//    @Query("DELETE FROM image_table WHERE imageName = :name")
//    void deleteImage(String name);

    @Query("SELECT * FROM image_table")
    LiveData<List<ImageEntity>> getAllImages();
}