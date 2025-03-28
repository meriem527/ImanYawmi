package com.example.imanyawmi.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.UserProgress;

import java.util.List;

@Dao
public interface UserProgressDao {
    @Insert
    void insert(UserProgress userProgress);

    @Update
    void update(UserProgress userProgress);

    @Query("SELECT * FROM user_progress")
    LiveData<List<UserProgress>> getAllUserProgress();
}
