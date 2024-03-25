package com.example.oblig3.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.oblig3.database.AppDatabase;
import com.example.oblig3.database.ImageDAO;
import com.example.oblig3.model.ImageEntity;

import java.util.List;

public class ImageRepository {
    private MutableLiveData<List<ImageEntity>> searchResults = new MutableLiveData<>();
    private ImageDAO imageDao;
    private LiveData<List<ImageEntity>> allImages;
    private MutableLiveData<ImageEntity> liveDataImage = new MutableLiveData<>();

    public LiveData<List<ImageEntity>> getAllImages() {
        return allImages;
    }

    public ImageRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        imageDao = db.imageDao();
        allImages = imageDao.getAllImages();
    }

    public void insertImage(ImageEntity newimage) {
        InsertAsyncTask task = new InsertAsyncTask(imageDao);
        task.execute(newimage);
    }

    public void deleteImage(String name) {
        DeleteAsyncTask task = new DeleteAsyncTask(imageDao);
        task.execute(name);
    }

    public LiveData<ImageEntity> findImageByPosition(int position) {
        QueryAsyncTask task = new QueryAsyncTask(imageDao, liveDataImage);
        task.execute(position);
        return liveDataImage;
    }

    private void asyncFinished(List<ImageEntity> results) {
        searchResults.setValue(results);
    }

    private static class QueryAsyncTask extends AsyncTask<Integer, Void, ImageEntity> {
        private ImageDAO asyncTaskDao;
        private MutableLiveData<ImageEntity> liveDataImage;


        QueryAsyncTask(ImageDAO dao, MutableLiveData<ImageEntity> liveDataImage) {
            asyncTaskDao = dao;
            this.liveDataImage = liveDataImage;
        }

        @Override
        protected ImageEntity doInBackground(Integer... positions) {
            int position = positions[0];
            return asyncTaskDao.findImageByPosition(position);
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
}