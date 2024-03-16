package com.example.oblig3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oblig3.R;
import com.example.oblig3.viewmodel.ImageViewModel;

public class DeleteImageActivity extends AppCompatActivity {
    private ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_picture);

        // Initialize your ImageViewModel
        imageViewModel = new ImageViewModel(getApplication()); // Pass correct context or use ViewModelProviders

        Button deleteButton = findViewById(R.id.delete_btn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assuming you passed the image ID as an intent extra
                int imageId = getIntent().getIntExtra("IMAGE_ID", 0);
                imageViewModel.deleteImage(imageId); // Implement deleteImage method in your ViewModel
                finish(); // Close activity once deletion is done
            }
        });
    }
}