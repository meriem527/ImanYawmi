package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notepad")
public class NotepadEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String date;

    public NotepadEntry(String content, String date) {
        this.content = content;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
