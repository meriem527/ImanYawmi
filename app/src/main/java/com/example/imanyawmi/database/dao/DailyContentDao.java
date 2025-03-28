package com.example.imanyawmi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import com.example.imanyawmi.database.entities.DailyContent;

@Dao
public interface DailyContentDao {
    @Insert
    void insert(DailyContent dailyContent);

    @Query("SELECT * FROM daily_content ORDER BY RANDOM() LIMIT 1")
    DailyContent getRandomDailyContent();
}

