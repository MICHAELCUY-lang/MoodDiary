package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mooddiary.database.DatabaseHelper;
import com.example.mooddiary.model.MoodEntry;
=======
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mooddiary.database.DatabaseHelper; // Pastikan package ini benar
import com.example.mooddiary.model.MoodEntry; // Pastikan package ini benar
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputMoodFragment extends Fragment {

    private DatabaseHelper db;
    private RadioGroup rgMoodLevel;
    private EditText etNotes;
<<<<<<< HEAD
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
=======
    private LinearLayout llActivities;
    private Button btnSaveMood;

    // Daftar ID Checkbox yang digunakan di layout XML
    private final int[] activityCheckboxIds = {R.id.cb_work, R.id.cb_social, R.id.cb_sport};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_mood, container, false);

        db = new DatabaseHelper(getContext());

        // Inisialisasi Komponen UI
        rgMoodLevel = view.findViewById(R.id.rg_mood_level);
        etNotes = view.findViewById(R.id.et_notes);
        llActivities = view.findViewById(R.id.ll_activities);
        btnSaveMood = view.findViewById(R.id.btn_save_mood);

        btnSaveMood.setOnClickListener(v -> saveMoodEntry());

        return view;
    }

    private void saveMoodEntry() {
        // 1. Ambil Mood Level (Selection)
        int selectedId = rgMoodLevel.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(getContext(), "Pilih level mood Anda terlebih dahulu.", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedRadioButton = getView().findViewById(selectedId);
        // Misal: Ambil angka mood level dari text (Fantastis (5) -> 5)
        String moodText = selectedRadioButton.getText().toString();
        int moodLevel = Integer.parseInt(moodText.substring(moodText.indexOf('(') + 1, moodText.indexOf(')')));

        // 2. Ambil Catatan (Text Input)
        String notes = etNotes.getText().toString().trim();
        if (notes.isEmpty()) {
            Toast.makeText(getContext(), "Catatan tidak boleh kosong.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Ambil Aktivitas (Checkbox)
        StringBuilder activitiesBuilder = new StringBuilder();
        for (int id : activityCheckboxIds) {
            CheckBox cb = getView().findViewById(id);
            if (cb.isChecked()) {
                if (activitiesBuilder.length() > 0) {
                    activitiesBuilder.append(",");
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
                }
                activitiesBuilder.append(cb.getText().toString());
            }
        }
        String activities = activitiesBuilder.toString();

<<<<<<< HEAD
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
=======
        // 4. Tanggal Hari Ini
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // Cek apakah entri untuk hari ini sudah ada (hindari duplikasi)
        if (db.getMoodEntry(todayDate) != null) {
            Toast.makeText(getContext(), "Anda sudah mencatat mood hari ini. Data akan diupdate!", Toast.LENGTH_LONG).show();
            // Lakukan Update (Tambahkan fungsi updateMoodEntry di DatabaseHelper)
            MoodEntry updatedEntry = new MoodEntry(0, todayDate, moodLevel, notes, activities);
            db.updateMoodEntry(updatedEntry);
        } else {
            // Lakukan Insert (Button)
            MoodEntry newEntry = new MoodEntry(0, todayDate, moodLevel, notes, activities);
            db.addMoodEntry(newEntry);
            Toast.makeText(getContext(), "Mood berhasil disimpan!", Toast.LENGTH_SHORT).show();
        }

        // Kembali ke Home Fragment setelah menyimpan
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }
}
