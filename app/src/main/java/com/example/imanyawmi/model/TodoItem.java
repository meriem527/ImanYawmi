package com.example.imanyawmi.model;

public class TodoItem {
    private String text;
    private boolean completed;

    public TodoItem(String text) {
        this.text = text;
        this.completed = false;
    }

    public TodoItem(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void toggleCompletion() {
        this.completed = !this.completed;
    }
}