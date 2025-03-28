package com.example.imanyawmi.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.QuranReading;

import java.util.List;

@Dao
public interface QuranReadingDao {
    @Insert
    void insert(QuranReading quranReading);

    @Update
    void update(QuranReading quranReading);

    @Query("SELECT * FROM quran_reading")
    LiveData<List<QuranReading>> getAllReadings();  // Correction ici
}
