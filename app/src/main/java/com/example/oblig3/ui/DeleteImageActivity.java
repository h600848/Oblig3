package com.example.oblig3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oblig3.R;

public class DeleteImageActivity extends AppCompatActivity {
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_picture);

        name = getIntent().getStringExtra("NAME");

        String imageUriString = getIntent().getStringExtra("IMAGE");
        Uri imageUri = Uri.parse(imageUriString);

        TextView textView = findViewById(R.id.textView_delete_picture);
        ImageView imageView = findViewById(R.id.imageView_delete_picture);

        textView.setText(name);
        imageView.setImageURI(imageUri);
    }

    /**
     *
     * @param view The view that triggered this method, typically a button in the user interface.
     */
    public void deleteButton(View view){
        // Bruker AlertDialog.Builder for å bygge og vise en dialogboks til brukeren
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete picture");
        builder.setMessage("Are you sure you want to delete the picture?");
        builder.setPositiveButton("Delete", (dialog, which) -> {
            // Sletter elementet fra listen
            // Hvordan gjør jeg det?
            // TODO
        });
        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}