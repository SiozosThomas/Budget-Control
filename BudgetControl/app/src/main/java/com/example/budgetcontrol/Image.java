package com.example.budgetcontrol;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "image")
public class Image {

    @PrimaryKey
    private int id;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @ColumnInfo(name = "year")
    private int year;
    @ColumnInfo(name = "month")
    private int month;
    @ColumnInfo(name = "day")
    private int day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
