package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mooddiary.database.DatabaseHelper;
import com.example.mooddiary.model.MoodEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputMoodFragment extends Fragment {

    private DatabaseHelper db;
    private RadioGroup rgMoodLevel;
    private EditText etNotes;
    private Button btnSaveMood;

    private View rootView;

    // checklist IDs
    private final int[] activityCheckboxIds = {
            R.id.cb_work,
            R.id.cb_social,
            R.id.cb_sport,
            R.id.cb_relax
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_input_mood, container, false);

        db = new DatabaseHelper(requireContext());

        rgMoodLevel = rootView.findViewById(R.id.rg_mood_level);
        etNotes = rootView.findViewById(R.id.et_notes);
        btnSaveMood = rootView.findViewById(R.id.btn_save_mood);

        btnSaveMood.setOnClickListener(v -> saveMoodEntry());

        return rootView;
    }

    private void saveMoodEntry() {
        // 1. mood level
        int selectedId = rgMoodLevel.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(getContext(),
                    "Pilih level mood Anda terlebih dahulu.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int moodLevel;
        if (selectedId == R.id.rb_mood5) {
            moodLevel = 5;
        } else if (selectedId == R.id.rb_mood4) {
            moodLevel = 4;
        } else if (selectedId == R.id.rb_mood3) {
            moodLevel = 3;
        } else if (selectedId == R.id.rb_mood2) {
            moodLevel = 2;
        } else {
            moodLevel = 1;
        }

        // 2. notes
        String notes = etNotes.getText().toString().trim();
        if (notes.isEmpty()) {
            Toast.makeText(getContext(),
                    "Catatan tidak boleh kosong.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. aktivitas (checkbox)
        StringBuilder activitiesBuilder = new StringBuilder();
        for (int id : activityCheckboxIds) {
            CheckBox cb = rootView.findViewById(id);
            if (cb != null && cb.isChecked()) {
                if (activitiesBuilder.length() > 0) {
                    activitiesBuilder.append(", ");
                }
                activitiesBuilder.append(cb.getText().toString());
            }
        }
        String activities = activitiesBuilder.toString();

        // 4. tanggal hari ini
        String todayDate = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault()).format(new Date());

        // insert / update
        MoodEntry entry = new MoodEntry(0, todayDate, moodLevel, notes, activities);

        if (db.getMoodEntry(todayDate) != null) {
            db.updateMoodEntry(entry);
            Toast.makeText(getContext(),
                    "Mood hari ini di-update.",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.addMoodEntry(entry);
            Toast.makeText(getContext(),
                    "Mood berhasil disimpan!",
                    Toast.LENGTH_SHORT).show();
        }

        // kembali ke Home
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }
}
