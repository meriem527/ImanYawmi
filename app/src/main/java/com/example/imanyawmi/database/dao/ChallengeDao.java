package com.example.imanyawmi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.Challenge;

import java.util.List;

@Dao
public interface ChallengeDao {
    @Insert
    void insert(Challenge challenge);

    @Update
    void update(Challenge challenge);

    @Query("SELECT * FROM challenges")
    List<Challenge> getAllChallenges();
}
