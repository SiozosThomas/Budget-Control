package com.example.budgetcontrol;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    public void addImage(Image image);

    @Query("select * from image")
    public List<Image> getImages();

}
