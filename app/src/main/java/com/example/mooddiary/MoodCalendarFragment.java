package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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