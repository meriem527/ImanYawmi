package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quran_reading")
public class QuranReading {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int surahNumber;
    private int verseCount;

    public QuranReading(int surahNumber, int verseCount) {
        this.surahNumber = surahNumber;
        this.verseCount = verseCount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSurahNumber() { return surahNumber; }
    public void setSurahNumber(int surahNumber) { this.surahNumber = surahNumber; }

    public int getVerseCount() { return verseCount; }
    public void setVerseCount(int verseCount) { this.verseCount = verseCount; }
}

