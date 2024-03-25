package com.example.oblig3.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.oblig3.model.ImageEntity;
import com.example.oblig3.repository.ImageRepository;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {
    private ImageRepository repository;
    private LiveData<List<ImageEntity>> allImages;

    public ImageViewModel(Application application) {
        super(application);
        repository = new ImageRepository(application);
        allImages = repository.getAllImages();
    }

    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    public void insertImage(ImageEntity image) {
        repository.insertImage(image);
    }

    public void findImage(String name) {
        repository.findImage(name);
    }

    public void deleteImage(String name) {
        repository.deleteImage(name);
    }
}