package com.example.budgetcontrol;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Receipt extends Fragment {

    private static final int REQUEST_IMAGE_PICTURE = 101;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterImages recyclerAdapterImages;
    private List<Image> images = MainActivity.imageDatabase.imageDao().getImages();
    private int year;
    private int month;
    private int day;

    public Receipt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button btnTakePic;
        View view =  inflater.inflate(R.layout.fragment_receipt, container, false);
        btnTakePic = view.findViewById(R.id.btn_pic);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapterImages = new RecyclerAdapterImages(images);
        recyclerView.setAdapter(recyclerAdapterImages);
        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture(view);
            }
        });
        return view;
    }

    public void takePicture(View view) {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivityForResult(imageTakeIntent, REQUEST_IMAGE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_PICTURE && resultCode == android.app.Activity.RESULT_OK) {
            Image image = new Image();
            List<Image> images = MainActivity.imageDatabase.imageDao().getImages();
            if (images.isEmpty()) {
                image.setId(0);
            } else {
                image.setId(images.get(images.size() - 1).getId() + 1);
            }
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            image.setImage(blob.toByteArray());
            image.setYear(year);
            image.setMonth(month);
            image.setDay(day);
            MainActivity.imageDatabase.imageDao().addImage(image);
            Toast.makeText(getActivity(),"Image saved succesfully...", Toast.LENGTH_SHORT).show();
        }
    }

    private void setTodayDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

}
