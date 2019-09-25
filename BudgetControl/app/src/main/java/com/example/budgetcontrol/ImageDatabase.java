package com.example.budgetcontrol;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Image.class}, version = 1, exportSchema = false)
public abstract class ImageDatabase extends RoomDatabase {

    public abstract ImageDao imageDao();
}
