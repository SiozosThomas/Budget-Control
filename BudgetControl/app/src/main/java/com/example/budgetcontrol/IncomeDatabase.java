package com.example.budgetcontrol;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Income.class}, version = 1, exportSchema = false)
public abstract class IncomeDatabase extends RoomDatabase {

    public abstract IncomeDao incomeDao();
}
