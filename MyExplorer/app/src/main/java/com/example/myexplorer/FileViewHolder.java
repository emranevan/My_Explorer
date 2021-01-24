package com.example.myexplorer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FileViewHolder extends RecyclerView.ViewHolder{
    public TextView tvFileName;
    public CardView container;
    public ImageView imageView;
    public FileViewHolder(@NonNull View itemView) {
        super(itemView);

        tvFileName = itemView.findViewById(R.id.txtsongname);
        imageView = itemView.findViewById(R.id.imgsong);
        container = itemView.findViewById(R.id.cardview_container);
    }
}
