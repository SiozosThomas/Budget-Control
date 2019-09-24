package com.example.budgetcontrol;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OutcomeDao {

    @Insert
    public void addOutcome(Outcome outcome);

    @Query("select * from outcome")
    public List<Outcome> getOutcomes();
}
