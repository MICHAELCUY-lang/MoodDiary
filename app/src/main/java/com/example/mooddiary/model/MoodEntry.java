package com.example.mooddiary.model;

public class MoodEntry {
    private int id;
    private String date; // Format: YYYY-MM-DD
    private int moodLevel; // 1 sampai 5
    private String notes;
    private String activities; // Koma-dipisahkan

    // Constructor Penuh
    public MoodEntry(int id, String date, int moodLevel, String notes, String activities) {
        this.id = id;
        this.date = date;
        this.moodLevel = moodLevel;
        this.notes = notes;
        this.activities = activities;
    }

    // Constructor tanpa ID (untuk CREATE)
    public MoodEntry(String date, int moodLevel, String notes, String activities) {
        this.date = date;
        this.moodLevel = moodLevel;
        this.notes = notes;
        this.activities = activities;
    }

    // --- GETTER dan SETTER ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getMoodLevel() { return moodLevel; }
    public void setMoodLevel(int moodLevel) { this.moodLevel = moodLevel; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getActivities() { return activities; }
    public void setActivities(String activities) { this.activities = activities; }
}