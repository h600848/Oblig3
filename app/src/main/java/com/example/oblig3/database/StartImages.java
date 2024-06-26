package com.example.oblig3.database;

import android.app.Application;
import android.net.Uri;

import com.example.oblig3.R;
import com.example.oblig3.model.ImageEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class StartImages extends Application {

    ArrayList<ImageEntity> imageEntity;

    public StartImages() {
        imageEntity = new ArrayList<>(Arrays.asList(
                new ImageEntity("Gorilla", Uri.parse("android.resource://com.example.oblig3/" + R.drawable.gorilla)),
                new ImageEntity("Isbjørn", Uri.parse("android.resource://com.example.oblig3/" + R.drawable.isbjorn)),
                new ImageEntity("Fox", Uri.parse("android.resource://com.example.oblig3/" + R.drawable.fox))
        ));
    }

    public ArrayList<ImageEntity> getImageEntity() {
        return imageEntity;
    }
}

