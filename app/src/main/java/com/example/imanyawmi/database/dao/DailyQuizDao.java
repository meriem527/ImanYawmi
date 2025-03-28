package com.example.imanyawmi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.DailyQuiz;

import java.util.List;

@Dao
public interface DailyQuizDao {
    @Insert
    void insert(DailyQuiz dailyQuiz);

    @Update
    void update(DailyQuiz dailyQuiz);

    @Query("SELECT * FROM daily_quiz")
    List<DailyQuiz> getAllQuizzes();
}

