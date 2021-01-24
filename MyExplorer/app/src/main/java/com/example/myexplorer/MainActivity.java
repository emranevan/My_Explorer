package com.example.myexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFileSelectListener {

    private FileAdapter fileAdapter;

    private List<File> fileList;
    private RecyclerView recyclerView;
    String file_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.maintoolbar);
        toolbar.setTitle(R.string.app_name);

        try {
            file_name = getIntent().getStringExtra("FILE_NAME");
            File fIle = new File(file_name);
            displayPdf(fIle);
        }catch (Exception e){
            e.printStackTrace();
        }

        runtimePermission();
    }

    public void runtimePermission()
    {
        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displayPdf(new File(Environment.getExternalStorageState()));
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(MainActivity.this, "Permission Required!!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

    }

    public ArrayList<File> findFile (File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for (File singlefile: files)
        {
            arrayList.add(singlefile);

        }
        return arrayList;
    }

    public void displayPdf(File path){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        fileList = new ArrayList<>();
        fileList.addAll(findFile(path));
        fileAdapter = new FileAdapter(this,fileList, this);
        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onFileSelected(File file) {
        Toast.makeText(this, file.getName(), Toast.LENGTH_SHORT).show();
        if (file.isDirectory()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("FILE_NAME", file.getAbsolutePath());
            startActivity(intent);
        }

    }


    /*@Override
    public void onFi(File file) {
        //Toast.makeText(MainActivity.this, file.getName(), Toast.LENGTH_SHORT).show();
        *//*startActivity(new Intent(MainActivity.this, DocumentActivity.class)
                .putExtra("path",file.getAbsolutePath()));*//*
    }*/
}