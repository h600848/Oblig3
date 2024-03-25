package com.example.oblig3.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image_table")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String imageName;

    @Nullable
    private String imagePath;

    public ImageEntity(@NonNull String imageName, @Nullable String imagePath) {
        this.id = id;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getImageName() {
        return imageName;
    }

    @Nullable
    public String getImagePath() {
        return imagePath;
    }
}