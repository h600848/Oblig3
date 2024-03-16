package com.example.oblig3.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "image_table")
public class ImageEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private int imageResource;

    @NonNull
    private String imageName;

    @Nullable
    private String imagePath;

    public ImageEntity(@NonNull String imageName, int imageResource, @Nullable String imagePath) {
        this.imageName = imageName;
        this.imageResource = imageResource;
        this.imagePath = imagePath;
    }

    protected ImageEntity(Parcel in) {
        id = in.readInt();
        imageResource = in.readInt();
        imageName = in.readString();
        imagePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(imageResource);
        dest.writeString(imageName);
        dest.writeString(imagePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageEntity> CREATOR = new Creator<ImageEntity>() {
        @Override
        public ImageEntity createFromParcel(Parcel in) {
            return new ImageEntity(in);
        }

        @Override
        public ImageEntity[] newArray(int size) {
            return new ImageEntity[size];
        }
    };

    // Getters and setters
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
    public String getImageName() {
        return imageName;
    }

    @Nullable
    public String getImagePath() {
        return imagePath;
    }
}