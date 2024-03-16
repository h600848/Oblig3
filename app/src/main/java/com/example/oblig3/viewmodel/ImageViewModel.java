package com.example.oblig3.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.oblig3.model.ImageEntity;
import com.example.oblig3.repository.ImageRepository;

public class ImageViewModel extends AndroidViewModel {
    private ImageRepository repository;
    private LiveData<List<ImageEntity>> allImages;

    public ImageViewModel(Application application) {
        super(application);
        repository = new ImageRepository(application);
        allImages = repository.getAllImages();
        repository.initializeStartImages();
    }

    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    public void insert(ImageEntity image) {
        repository.insert(image);
    }

    public void deleteImage(long id) {
        repository.deleteImageWithId(id);
    }
}