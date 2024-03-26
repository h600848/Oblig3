package com.example.oblig3.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.oblig3.R;
import com.example.oblig3.database.AppDatabase;
import com.example.oblig3.database.ImageDAO;
import com.example.oblig3.model.ImageEntity;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository {
    private ImageDAO imageDao;
    private LiveData<List<ImageEntity>> allImages;
    private MutableLiveData<ImageEntity> liveDataImage = new MutableLiveData<>();

    public ImageRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        imageDao = db.imageDao();
        allImages = imageDao.getAllImages();
    }

    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    public void insertImage(ImageEntity newimage) {
        new InsertAsyncTask(imageDao).execute(newimage);
    }

    public void deleteImage(String name) {
        new DeleteAsyncTask(imageDao).execute(name);
    }

    // Antar at vi henter basert på en unik ID istedenfor en posisjon
    public LiveData<ImageEntity> findImageById(int id) {
        new QueryAsyncTask(imageDao, liveDataImage).execute(id);
        return liveDataImage;
    }

    private static class QueryAsyncTask extends AsyncTask<Integer, Void, ImageEntity> {
        private ImageDAO asyncTaskDao;
        private MutableLiveData<ImageEntity> liveDataImage;

        QueryAsyncTask(ImageDAO dao, MutableLiveData<ImageEntity> liveDataImage) {
            asyncTaskDao = dao;
            this.liveDataImage = liveDataImage;
        }

        @Override
        protected ImageEntity doInBackground(Integer... ids) {
            List<ImageEntity> result = asyncTaskDao.findImageById(ids[0]);
            if (!result.isEmpty()) {
                return result.get(0); // Henter det første bildet i listen, antar at ID er unik
            }
            return null;
        }

        @Override
        protected void onPostExecute(ImageEntity imageEntity) {
            liveDataImage.setValue(imageEntity);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<ImageEntity, Void, Void> {
        private ImageDAO asyncTaskDao;

        InsertAsyncTask(ImageDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ImageEntity... params) {
            asyncTaskDao.insertImage(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        private ImageDAO asyncTaskDao;

        DeleteAsyncTask(ImageDAO dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteImage(params[0]);
            return null;
        }
    }

    public void initializeDefaultImages(Application application) {
        // Sjekk om databasen er tom
        if (allImages.getValue() == null || allImages.getValue().isEmpty()) {
            // Definer stiene til standardbilder
            List<ImageEntity> defaultImages = new ArrayList<>();
            defaultImages.add(new ImageEntity("Fox", "android.resource://" + application.getPackageName() + "/" + R.drawable.fox));
            defaultImages.add(new ImageEntity("Polar bear", "android.resource://" + application.getPackageName() + "/" + R.drawable.isbjorn));
            defaultImages.add(new ImageEntity("Gorilla", "android.resource://" + application.getPackageName() + "/" + R.drawable.gorilla));

            // Legger til hvert standardbilde i databasen
            for (ImageEntity image : defaultImages) {
                insertImage(image);
            }
        }
    }
}