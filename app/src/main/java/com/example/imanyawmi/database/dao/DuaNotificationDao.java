package com.example.imanyawmi.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.imanyawmi.database.entities.DuaNotification;

import java.util.List;

@Dao
public interface DuaNotificationDao {
    @Insert
    void insert(DuaNotification duaNotification);

    @Update
    void update(DuaNotification duaNotification);

    @Query("SELECT * FROM dua_notifications")
    List<DuaNotification> getAllNotifications();
}
