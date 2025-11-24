package com.example.mooddiary.model;

public class MoodEntry {
<<<<<<< HEAD

    private int id;
    private String date;
    private int moodLevel;
    private String notes;
    private String activities;

=======
    private int id;
    private String date; // Format: YYYY-MM-DD
    private int moodLevel; // 1 sampai 5
    private String notes;
    private String activities; // Koma-dipisahkan

    // Constructor Penuh
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
    public MoodEntry(int id, String date, int moodLevel, String notes, String activities) {
        this.id = id;
        this.date = date;
        this.moodLevel = moodLevel;
        this.notes = notes;
        this.activities = activities;
    }

<<<<<<< HEAD
=======
    // Constructor tanpa ID (untuk CREATE)
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
    public MoodEntry(String date, int moodLevel, String notes, String activities) {
        this.date = date;
        this.moodLevel = moodLevel;
        this.notes = notes;
        this.activities = activities;
    }

<<<<<<< HEAD
    public int getId() { return id; }
    public String getDate() { return date; }
    public int getMoodLevel() { return moodLevel; }
    public String getNotes() { return notes; }
    public String getActivities() { return activities; }

    public void setId(int id) { this.id = id; }
    public void setDate(String date) { this.date = date; }
    public void setMoodLevel(int moodLevel) { this.moodLevel = moodLevel; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setActivities(String activities) { this.activities = activities; }

    // Helpers
    public String[] getActivitiesArray() {
        if (activities == null || activities.isEmpty()) return new String[]{};
        return activities.split(",");
    }

    public String getMoodLabel() {
        switch (moodLevel) {
            case 5: return "Amazing";
            case 4: return "Happy";
            case 3: return "Neutral";
            case 2: return "Low";
            case 1: return "Bad";
            default: return "Unknown";
        }
    }
}
=======
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
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
