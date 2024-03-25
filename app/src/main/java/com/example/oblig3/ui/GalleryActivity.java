package com.example.oblig3.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oblig3.R;
import com.example.oblig3.adapter.RecyclerViewAdapter;
import com.example.oblig3.adapter.RecyclerViewInterface;
import com.example.oblig3.database.AppDatabase;
import com.example.oblig3.model.ImageEntity;
import com.example.oblig3.viewmodel.ImageViewModel;

public class GalleryActivity extends AppCompatActivity implements RecyclerViewInterface {
    private static final int GALLERY_REQUEST = 1; // Class constant for gallery request
    private ImageViewModel imageViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        // TODO
        // Må finne ut av hva jeg skal ha der det står AppDatabase
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, AppDatabase, this); // Oppdatert for å reflektere den nye konstruktøren
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * @param view The view that triggered this method, typically a sort button in the user interface.
     */
    public void sortButton(View view){
        // Refresh the RecyclerView
        refreshRecyclerView();

        Button sortButton = (Button) findViewById(R.id.sort_btn);
        // Må fikse her TODO
        if (true) {
            sortButton.setText("Sort A-Z");
        } else {
            sortButton.setText("Sort Z-A");
        }
    }

    public void addButton(View view){
        // Launch the gallery to pick an image
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    /**
     * Handles the result from launching the gallery for image selection.
     * If the result is OK and the request code matches, prompts the user to enter a name for the new image.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    allowing identification of who this result came from.
     * @param resultCode  The integer result code returned by the child activity through its setResult().
     * @param data        An Intent, which can return result data to the caller (various data can be attached as Extras).
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST) {
            Uri imageUri = data.getData();
            // Prompt for entering a name
            promptForImageName(imageUri);
        }
    }

    /**
     * Displays a dialog prompting the user to enter a name for the new image selected from the gallery.
     * If a name is entered and confirmed with "OK", a new ImageEntity instance is created and added to the database.
     * The RecyclerView is then refreshed to include the newly added animal.
     *
     * @param imageUri The URI of the selected image to be associated with the new image.
     */
    private void promptForImageName(Uri imageUri) {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        new AlertDialog.Builder(this)
                .setTitle("Add a name for the picture")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty()) {
                        ImageEntity newImage = new ImageEntity(name, imageUri.toString());
                        imageViewModel.insertImage(newImage);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void refreshRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged(); // Notify the adapter to refresh the view
        }
    }

    @Override
    public void onItemClick(int position) {
        //Code for on click in recycleView
        Intent intent = new Intent(this, DeleteImageActivity.class);

        //Gives information about the Image by the RecyclerviewAdapter based on the position.
        intent.putExtra("NAME", AppDatabase.getDatabase(getApplicationContext()).getImage(position).getName());

        Uri imageUri = AppDatabase.getDatabase(getApplicationContext()).getImage(position).getImage();
        intent.putExtra("IMAGE", imageUri.toString());

        startActivity(intent);
    }
}