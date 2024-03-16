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

    private int imageResource;

    @NonNull
    public String imageName;

    @Nullable
    private String imagePath;

    public ImageEntity(@NonNull String imageName, int imageResource, @Nullable String imagePath) {
        this.imageName = imageName;
        this.imageResource = imageResource;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResource() {
        return imageResource;
    }

    @NonNull
    public String getImageText() {
        return imageName;
    }

    @Nullable
    public String getImagePath() {
        return imagePath;
    }
}