package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dua_notifications")
public class DuaNotification {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String duaText;
    private String notificationTime;

    public DuaNotification(String duaText, String notificationTime) {
        this.duaText = duaText;
        this.notificationTime = notificationTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDuaText() { return duaText; }
    public void setDuaText(String duaText) { this.duaText = duaText; }

    public String getNotificationTime() { return notificationTime; }
    public void setNotificationTime(String notificationTime) { this.notificationTime = notificationTime; }
}
