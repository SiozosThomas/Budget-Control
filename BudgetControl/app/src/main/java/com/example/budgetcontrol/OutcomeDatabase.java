package com.example.budgetcontrol;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Outcome.class}, version = 1, exportSchema = false)
public abstract class OutcomeDatabase extends RoomDatabase {

    public abstract OutcomeDao outcomeDao();
}
