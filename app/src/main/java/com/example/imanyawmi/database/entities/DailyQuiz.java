package com.example.imanyawmi.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_quiz")
public class DailyQuiz {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question;
    private boolean isCorrect;

    public DailyQuiz(String question, boolean isCorrect) {
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
}

