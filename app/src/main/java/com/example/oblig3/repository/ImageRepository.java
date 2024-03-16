package com.example.oblig3.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.oblig3.R;
import com.example.oblig3.database.AppDatabase;
import com.example.oblig3.database.ImageDAO;
import com.example.oblig3.model.ImageEntity;

import java.util.List;

public class ImageRepository {
    private ImageDAO imageDao;
    private LiveData<List<ImageEntity>> allImages;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ImageRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        imageDao = db.imageDao();
        allImages = imageDao.getAllImages();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(ImageEntity image) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            imageDao.insert(image);
        });
    }

    public void deleteImageWithId(long id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            imageDao.deleteImageWithId(id);
        });
    }

    public void initializeStartImages() {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (imageDao.getCountOfImages() == 0) {
                imageDao.insert(new ImageEntity("Gorilla", R.drawable.gorilla, null));
                imageDao.insert(new ImageEntity("Polar Bear", R.drawable.isbjorn, null));
            }
        });
    }
}