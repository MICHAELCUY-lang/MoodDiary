package com.example.mooddiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.mooddiary.database.DatabaseHelper;
import com.example.mooddiary.model.MoodEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import androidx.fragment.app.Fragment;import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private DatabaseHelper db;
    private TextView tvTodayMood;
    private TextView tvDailyQuote;

    // Daftar Quote Motivasi
    private final String[] quotes = {
            "Setiap hari mungkin tidak baik, tetapi ada hal baik di setiap hari.",
            "Kekuatan tidak datang dari kemenangan. Perjuangan Anda mengembangkan kekuatan Anda.",
            "Bukan seberapa keras Anda memukul, tapi seberapa keras Anda bisa dipukul dan terus maju.",
            "Hal-hal hebat tidak pernah datang dari zona nyaman.",
            "Satu-satunya cara untuk melakukan pekerjaan hebat adalah mencintai apa yang Anda lakukan."
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DatabaseHelper(getContext());
        tvTodayMood = view.findViewById(R.id.tv_today_mood);
        tvDailyQuote = view.findViewById(R.id.tv_daily_quote);
        FloatingActionButton fabRecordMood = view.findViewById(R.id.fab_record_mood);

        displayTodayMood();
        displayDailyQuote();

        fabRecordMood.setOnClickListener(v -> {
            // Pindah ke Input Mood Fragment
            if (getActivity() != null) {
                ((MainActivity) getActivity()).navigateToFragment(new InputMoodFragment());
            }
        });

        return view;
    }

    private void displayTodayMood() {
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        MoodEntry entry = db.getMoodEntry(todayDate);

        if (entry != null) {
            String moodDesc;
            // Konversi Mood Level ke deskripsi
            switch (entry.getMoodLevel()) {
                case 5: moodDesc = "🌟 Fantastis"; break;
                case 4: moodDesc = "😊 Senang"; break;
                case 3: moodDesc = "😐 Biasa Saja"; break;
                case 2: moodDesc = "😔 Sedih"; break;
                case 1: moodDesc = "😡 Buruk"; break;
                default: moodDesc = "Mood Error"; break;
            }
            tvTodayMood.setText(moodDesc + "\n(Catatan: " + entry.getNotes() + ")");
        } else {
            tvTodayMood.setText("Belum dicatat. Klik tombol di bawah untuk mencatat mood Anda!");
        }
    }

    // Fungsi sederhana untuk memilih quote secara acak
    private void displayDailyQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        tvDailyQuote.setText(quotes[index]);
    }
}