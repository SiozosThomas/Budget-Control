package com.example.budgetcontrol;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyUserAccessObject {

    @Insert
    public void addUser(UserInfo user);

    @Query("select * from userInfo")
    public List<UserInfo> getUsers();

    @Update
    public void updateUserInfo(UserInfo user);

}
