package com.example.imanyawmi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.NotepadEntry;

import java.util.List;

@Dao
public interface NotepadDao {
    @Insert
    void insert(NotepadEntry notepadEntry);

    @Update
    void update(NotepadEntry notepadEntry);

    @Query("SELECT * FROM notepad")
    List<NotepadEntry> getAllEntries();
}
