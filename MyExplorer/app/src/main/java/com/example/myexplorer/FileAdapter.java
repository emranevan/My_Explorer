package com.example.myexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileViewHolder>{

    private Context context;
    private List<File> files;
    private OnFileSelectListener listener;

    public FileAdapter(Context context, List<File> pdfFiles, OnFileSelectListener listener) {
        this.context = context;
        this.files = pdfFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileViewHolder(LayoutInflater.from(context).inflate(R.layout.element_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.tvFileName.setText(""+files.get(position).getName());
        holder.tvFileName.setSelected(true);
        if (files.get(position).getName().endsWith(".png") || files.get(position).getName().endsWith(".JPG")){
            holder.imageView.setImageResource(R.drawable.image_ic);
        }
        if (files.get(position).getName().endsWith(".pdf")){
            holder.imageView.setImageResource(R.drawable.pdficon);
        }
        if (files.get(position).getName().endsWith(".apk")){
            holder.imageView.setImageResource(R.drawable.ic_app);
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onFileSelected(files.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return files.size();
    }
}
