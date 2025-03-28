package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "daily_content")
public class DailyContent {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String type; // "hadith", "quran", "dua"

    @NonNull
    private String content; // Texte du hadith, verset ou dua

    @NonNull
    private String reference; // Référence (ex: Sourate & verset, source du hadith)

    // ✅ **Ajout du constructeur**
    public DailyContent(@NonNull String type, @NonNull String content, @NonNull String reference) {
        this.type = type;
        this.content = content;
        this.reference = reference;
    }

    // ✅ **Ajout des Getters et Setters**
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
