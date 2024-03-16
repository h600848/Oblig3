package com.example.oblig3.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.oblig3.R;
import com.example.oblig3.model.ImageEntity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ImageEntity> images; // Endret til å initialiseres som en tom liste
    private RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.images = new ArrayList<>(); // Initialiserer som tom liste for å unngå NullPointerException
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageEntity image = images.get(position);
        holder.textView.setText(image.getImageName());
        if (image.getImagePath() != null && !image.getImagePath().isEmpty()) {
            holder.imageView.setImageURI(Uri.parse(image.getImagePath()));
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textView = itemView.findViewById(R.id.image_nameView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(v -> {
                if (recyclerViewInterface != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
        notifyDataSetChanged(); // Varsler om endring i datasettet
    }

    public ImageEntity getImageAtPosition(int position) {
        return images.get(position);
    }
}