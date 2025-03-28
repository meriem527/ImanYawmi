package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_progress")
public class UserProgress {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int completedChallenges;
    private int completedQuizzes;
    private int quranVersesRead;
    private String notes;

    // Constructor
    public UserProgress(int completedChallenges, int completedQuizzes, int quranVersesRead, String notes) {
        this.completedChallenges = completedChallenges;
        this.completedQuizzes = completedQuizzes;
        this.quranVersesRead = quranVersesRead;
        this.notes = notes;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCompletedChallenges() { return completedChallenges; }
    public void setCompletedChallenges(int completedChallenges) { this.completedChallenges = completedChallenges; }

    public int getCompletedQuizzes() { return completedQuizzes; }
    public void setCompletedQuizzes(int completedQuizzes) { this.completedQuizzes = completedQuizzes; }

    public int getQuranVersesRead() { return quranVersesRead; }
    public void setQuranVersesRead(int quranVersesRead) { this.quranVersesRead = quranVersesRead; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

