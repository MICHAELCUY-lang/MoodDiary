package com.example.mooddiary;

<<<<<<< HEAD
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

=======
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.mooddiary.HomeFragment;
import com.example.mooddiary.InputMoodFragment;
import com.example.mooddiary.MoodCalendarFragment;
import com.example.mooddiary.StatsFragment;
import com.example.mooddiary.SettingsFragment;

>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
<<<<<<< HEAD

        // Apply dark / light mode based on saved settings
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);

        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Show HomeFragment on first launch
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_input) {
                    selectedFragment = new InputMoodFragment();
                } else if (itemId == R.id.nav_calendar) {
                    selectedFragment = new MoodCalendarFragment();
                } else if (itemId == R.id.nav_stats) {
                    selectedFragment = new StatsFragment();
                } else if (itemId == R.id.nav_settings) {
                    selectedFragment = new SettingsFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }

                return true;
            };

    // Optional helper for navigating from inside fragments if needed
    public void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
=======
        super.onCreate(savedInstanceState);
        // Menghubungkan Activity dengan layout utama
        setContentView(R.layout.activity_main);

        // Inisialisasi Bottom Navigation View
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Tampilkan Home Fragment saat aplikasi pertama dibuka
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                // Mengganti Fragment berdasarkan item menu
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new HomeFragment(); // Dashboard, Quote
                } else if (item.getItemId() == R.id.nav_input) {
                    selectedFragment = new InputMoodFragment(); // Form Input
                } else if (item.getItemId() == R.id.nav_calendar) {
                    selectedFragment = new MoodCalendarFragment(); // Mood History
                } else if (item.getItemId() == R.id.nav_stats) {
                    selectedFragment = new StatsFragment(); // Statistik Mood
                } else if (item.getItemId() == R.id.nav_settings) {
                    selectedFragment = new SettingsFragment(); // Dark Theme, Reminder
                }

                // Pastikan fragment tidak null sebelum melakukan transisi
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }
                return true;
            };

    /**
     * Metode publik untuk Fragment anak berpindah ke Fragment lain.
     */
    public void navigateToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
