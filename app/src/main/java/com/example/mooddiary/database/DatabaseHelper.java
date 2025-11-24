package com.example.mooddiary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mooddiary.model.MoodEntry;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "moodsManager";

    public static final String TABLE_MOODS = "mood_entries";
    public static final String KEY_ID = "id";
    public static final String KEY_DATE = "entry_date";
    public static final String KEY_LEVEL = "mood_level";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_ACTIVITIES = "activities";

    private static final String CREATE_MOODS_TABLE =
            "CREATE TABLE " + TABLE_MOODS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_DATE + " TEXT UNIQUE,"
                    + KEY_LEVEL + " INTEGER,"
                    + KEY_NOTES + " TEXT,"
                    + KEY_ACTIVITIES + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOODS);
        onCreate(db);
    }

    // --------------------------
    // CREATE
    // --------------------------
    public void addMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE, entry.getDate());
        values.put(KEY_LEVEL, entry.getMoodLevel());
        values.put(KEY_NOTES, entry.getNotes());
        values.put(KEY_ACTIVITIES, entry.getActivities());

        db.insertWithOnConflict(TABLE_MOODS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    // --------------------------
    // READ ONE
    // --------------------------
    public MoodEntry getMoodEntry(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOODS,
                new String[]{KEY_ID, KEY_DATE, KEY_LEVEL, KEY_NOTES, KEY_ACTIVITIES},
                KEY_DATE + "=?",
                new String[]{date},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            MoodEntry entry = new MoodEntry(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            cursor.close();
            return entry;
        }
        return null;
    }

    // --------------------------
    // READ ALL
    // --------------------------
    public List<MoodEntry> getAllMoodEntries() {
        List<MoodEntry> moodList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_MOODS + " ORDER BY " + KEY_DATE + " DESC",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                MoodEntry entry = new MoodEntry(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                moodList.add(entry);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return moodList;
    }

    // --------------------------
    // UPDATE
    // --------------------------
    public int updateMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LEVEL, entry.getMoodLevel());
        values.put(KEY_NOTES, entry.getNotes());
        values.put(KEY_ACTIVITIES, entry.getActivities());

        return db.update(TABLE_MOODS, values, KEY_DATE + " = ?", new String[]{entry.getDate()});
    }

    // --------------------------
    // DELETE
    // --------------------------
    public void deleteMoodEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOODS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
