package com.example.mooddiary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<<<<<<< HEAD
import com.example.mooddiary.model.MoodEntry;
=======
import com.example.mooddiary.model.MoodEntry; // PASTIKAN ANDA SUDAH MEMBUAT KELAS INI!
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

<<<<<<< HEAD
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "moodsManager";

=======
    // Database Version dan Nama
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "moodsManager";

    // Nama Tabel dan Kolom
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
    public static final String TABLE_MOODS = "mood_entries";
    public static final String KEY_ID = "id";
    public static final String KEY_DATE = "entry_date";
    public static final String KEY_LEVEL = "mood_level";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_ACTIVITIES = "activities";

<<<<<<< HEAD
    private static final String CREATE_MOODS_TABLE =
            "CREATE TABLE " + TABLE_MOODS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_DATE + " TEXT UNIQUE,"
                    + KEY_LEVEL + " INTEGER,"
                    + KEY_NOTES + " TEXT,"
                    + KEY_ACTIVITIES + " TEXT"
                    + ")";
=======
    // SQL untuk membuat tabel
    private static final String CREATE_MOODS_TABLE = "CREATE TABLE " + TABLE_MOODS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_DATE + " TEXT UNIQUE," // Menambahkan UNIQUE agar tidak ada duplikasi tanggal
            + KEY_LEVEL + " INTEGER,"
            + KEY_NOTES + " TEXT,"
            + KEY_ACTIVITIES + " TEXT" + ")";
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
<<<<<<< HEAD
=======
        // Hapus tabel lama jika ada dan buat ulang
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOODS);
        onCreate(db);
    }

<<<<<<< HEAD
    // --------------------------
    // CREATE
    // --------------------------
    public void addMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

=======
    // --- FUNGSI UTAMA DATABASE (CRUD) ---

    /**
     * Menambahkan entri mood baru ke database. (CREATE)
     * @param entry Objek MoodEntry yang akan disimpan.
     */
    public void addMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
        values.put(KEY_DATE, entry.getDate());
        values.put(KEY_LEVEL, entry.getMoodLevel());
        values.put(KEY_NOTES, entry.getNotes());
        values.put(KEY_ACTIVITIES, entry.getActivities());

<<<<<<< HEAD
        db.insertWithOnConflict(TABLE_MOODS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    // --------------------------
    // READ ONE
    // --------------------------
    public MoodEntry getMoodEntry(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
=======
        // Menyisipkan Baris
        db.insert(TABLE_MOODS, null, values);
        db.close();
    }

    /**
     * Mendapatkan satu entri mood berdasarkan tanggal. (READ)
     * @param date Tanggal entri mood (format YYYY-MM-DD).
     * @return Objek MoodEntry atau null jika tidak ditemukan.
     */
    public MoodEntry getMoodEntry(String date) {
        SQLiteDatabase db = this.getReadableDatabase();

>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
        Cursor cursor = db.query(TABLE_MOODS,
                new String[]{KEY_ID, KEY_DATE, KEY_LEVEL, KEY_NOTES, KEY_ACTIVITIES},
                KEY_DATE + "=?",
                new String[]{date},
<<<<<<< HEAD
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

=======
                null, null, null, null);

        MoodEntry entry = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                entry = new MoodEntry(
                        cursor.getInt(0),      // id
                        cursor.getString(1),   // date
                        cursor.getInt(2),      // level
                        cursor.getString(3),   // notes
                        cursor.getString(4)    // activities
                );
            }
            cursor.close();
        }
        db.close();
        return entry;
    }

    /**
     * Mendapatkan semua entri mood dari database. (READ ALL)
     * @return List dari MoodEntry.
     */
    public List<MoodEntry> getAllMoodEntries() {
        List<MoodEntry> moodList = new ArrayList<>();
        // Query untuk memilih semua baris, diurutkan berdasarkan tanggal terbaru
        String selectQuery = "SELECT * FROM " + TABLE_MOODS + " ORDER BY " + KEY_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop melalui semua baris dan menambahkan ke list
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
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
<<<<<<< HEAD

        cursor.close();
        return moodList;
    }

    // --------------------------
    // UPDATE
    // --------------------------
    public int updateMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

=======
        cursor.close();
        db.close();
        return moodList;
    }

    /**
     * Mengupdate satu entri mood yang sudah ada berdasarkan tanggal. (UPDATE)
     * @param entry Objek MoodEntry yang berisi data yang diperbarui.
     * @return Jumlah baris yang diupdate.
     */
    public int updateMoodEntry(MoodEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
        values.put(KEY_LEVEL, entry.getMoodLevel());
        values.put(KEY_NOTES, entry.getNotes());
        values.put(KEY_ACTIVITIES, entry.getActivities());

<<<<<<< HEAD
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
=======
        // Mengupdate baris berdasarkan KEY_DATE
        int rowsAffected = db.update(TABLE_MOODS, values, KEY_DATE + " = ?",
                new String[]{entry.getDate()});
        db.close();
        return rowsAffected;
    }

    /**
     * Menghapus satu entri mood berdasarkan ID. (DELETE)
     * @param id ID entri yang akan dihapus.
     */
    public void deleteMoodEntry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOODS, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
