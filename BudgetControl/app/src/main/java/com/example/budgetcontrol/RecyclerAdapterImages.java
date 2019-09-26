package com.example.budgetcontrol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapterImages extends RecyclerView.Adapter<RecyclerAdapterImages.ImageViewHolder> {

    private List<Image> images = MainActivity.imageDatabase.imageDao().getImages();

    public RecyclerAdapterImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int i) {
        byte[] image_id = images.get(i).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image_id, 0, image_id.length);
        holder.album.setImageBitmap(bitmap);
        holder.albumTitle.setText(images.get(i).getDay() + "/" + images.get(i).getMonth() + "/" + images.get(i).getYear());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView album;
        TextView albumTitle;

        public ImageViewHolder(View itemView) {
            super(itemView);
            album = itemView.findViewById(R.id.album);
            albumTitle = itemView.findViewById(R.id.album_title);
        }
    }
}
