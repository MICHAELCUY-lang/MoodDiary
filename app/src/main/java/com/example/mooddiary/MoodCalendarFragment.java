package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mooddiary.database.DatabaseHelper;
import com.example.mooddiary.model.MoodEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MoodCalendarFragment extends Fragment {

    private CalendarView calendarView;
    private TextView tvSelectedMood;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mood_calendar, container, false);

        // inisialisasi
        calendarView = view.findViewById(R.id.mood_calendar_view);
        tvSelectedMood = view.findViewById(R.id.tv_selected_mood_details);
        db = new DatabaseHelper(requireContext());

        // Listener saat tanggal dipilih
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {

            // Format tanggal harus sama dengan format saat input mood
            String selectedDate = year + "-" +
                    String.format(Locale.getDefault(), "%02d", (month + 1)) + "-" +
                    String.format(Locale.getDefault(), "%02d", dayOfMonth);

            MoodEntry entry = db.getMoodEntry(selectedDate);

            if (entry == null) {
                tvSelectedMood.setText(
                        "No mood recorded on this date.\nTry selecting another day."
                );
            } else {
                String moodText = convertMoodLevel(entry.getMoodLevel());

                tvSelectedMood.setText(
                        "Date: " + entry.getDate() +
                                "\nMood Level: " + moodText +
                                "\n\nNotes:\n" + (entry.getNotes().isEmpty() ? "-" : entry.getNotes()) +
                                "\n\nActivities:\n" + (entry.getActivities().isEmpty() ? "-" : entry.getActivities())
                );
            }
        });

        return view;
    }

    // Mood level 1–5 jadi teks cantik
    private String convertMoodLevel(int level) {
        switch (level) {
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
import androidx.fragment.app.Fragment;
import com.example.mooddiary.R; // Pastikan R diimpor

public class MoodCalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Anda perlu membuat file layout ini (fragment_mood_calendar.xml) di res/layout
        return inflater.inflate(R.layout.fragment_mood_calendar, container, false);
    }
}
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
