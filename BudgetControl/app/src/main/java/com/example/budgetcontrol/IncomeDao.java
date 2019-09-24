package com.example.budgetcontrol;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface IncomeDao {

    @Insert
    public void addIncome(Income income);

    @Query("select * from income")
    public List<Income> getIncomes();
}
