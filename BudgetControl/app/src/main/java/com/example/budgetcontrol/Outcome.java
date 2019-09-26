package com.example.budgetcontrol;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "outcome")
public class Outcome {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "value")
    private double value;
    @ColumnInfo(name = "type")
    private String type;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
