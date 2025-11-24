package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mooddiary.database.DatabaseHelper;
import com.example.mooddiary.model.MoodEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsFragment extends Fragment {

    private TextView tvAverageMood, tvActivitySummary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        tvAverageMood = view.findViewById(R.id.tv_average_mood);
        tvActivitySummary = view.findViewById(R.id.tv_activity_summary);

        loadStatistics();

        return view;
    }

    private void loadStatistics() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<MoodEntry> allEntries = db.getAllMoodEntries();

        if (allEntries.isEmpty()) {
            tvAverageMood.setText("-- / 5.0");
            tvActivitySummary.setText("No activity data yet.");
            return;
        }

        // ===============================
        // 1. Hitung rata-rata 7 hari terakhir
        // ===============================
        double sum = 0;
        int count = 0;

        for (MoodEntry entry : allEntries) {
            if (count < 7) {
                sum += entry.getMoodLevel();
                count++;
            }
        }

        double average = sum / count;
        tvAverageMood.setText(String.format("%.1f / 5.0", average));

        // ===============================
        // 2. Hitung aktivitas paling sering dicatat
        // ===============================
        Map<String, Integer> activityMap = new HashMap<>();

        for (MoodEntry entry : allEntries) {
            if (entry.getActivities() != null && !entry.getActivities().trim().isEmpty()) {
                String[] acts = entry.getActivities().split(",");
                for (String a : acts) {
                    a = a.trim();
                    if (!a.isEmpty()) {
                        activityMap.put(a, activityMap.getOrDefault(a, 0) + 1);
                    }
                }
            }
        }

        if (activityMap.isEmpty()) {
            tvActivitySummary.setText("No activity records yet.");
        } else {
            String mostFrequent = "";
            int max = 0;
            for (String key : activityMap.keySet()) {
                if (activityMap.get(key) > max) {
                    max = activityMap.get(key);
                    mostFrequent = key;
                }
            }
            tvActivitySummary.setText("Most frequent activity: " + mostFrequent + " (" + max + "x)");
        }
    }
}
=======
import androidx.fragment.app.Fragment;

public class StatsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
}
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
