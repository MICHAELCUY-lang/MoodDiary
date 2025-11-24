package com.example.mooddiary;

<<<<<<< HEAD
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class SettingsFragment extends Fragment {

    private Switch switchDailyReminder;
    private Switch switchDarkTheme;
    private TextView tvReminderTime;
    private LinearLayout layoutReminderTime;

    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchDailyReminder = view.findViewById(R.id.switch_daily_reminder);
        switchDarkTheme = view.findViewById(R.id.switch_dark_theme);
        tvReminderTime = view.findViewById(R.id.tv_reminder_time);
        layoutReminderTime = view.findViewById(R.id.layout_reminder_time);

        SharedPreferences prefs =
                requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE);

        // ==== DARK MODE ====
        boolean isDark = prefs.getBoolean("dark_mode", false);
        switchDarkTheme.setChecked(isDark);

        switchDarkTheme.setOnCheckedChangeListener((buttonView, checked) -> {
            prefs.edit().putBoolean("dark_mode", checked).apply();

            if (checked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // refresh seluruh activity biar theme benar-benar ke-apply
            requireActivity().recreate();
        });

        // ==== REMINDER ====
        boolean reminderEnabled = prefs.getBoolean("reminder_enabled", false);
        switchDailyReminder.setChecked(reminderEnabled);

        int savedHour = prefs.getInt("reminder_hour", 20);
        int savedMinute = prefs.getInt("reminder_minute", 0);
        updateReminderText(savedHour, savedMinute);
        setReminderRowEnabled(reminderEnabled);

        // klik row "Reminder time" ATAU teks "Change"
        View.OnClickListener timeClickListener = v -> {
            if (!switchDailyReminder.isChecked()) {
                return; // kalau reminder off, jangan buka picker
            }

            Calendar cal = Calendar.getInstance();
            int hour = prefs.getInt("reminder_hour", cal.get(Calendar.HOUR_OF_DAY));
            int minute = prefs.getInt("reminder_minute", cal.get(Calendar.MINUTE));

            TimePickerDialog dialog = new TimePickerDialog(
                    requireContext(),
                    (tpView, selectedHour, selectedMinute) -> {
                        prefs.edit()
                                .putInt("reminder_hour", selectedHour)
                                .putInt("reminder_minute", selectedMinute)
                                .apply();
                        updateReminderText(selectedHour, selectedMinute);
                    },
                    hour,
                    minute,
                    true
            );

            dialog.show();
        };

        layoutReminderTime.setOnClickListener(timeClickListener);
        tvReminderTime.setOnClickListener(timeClickListener);

        switchDailyReminder.setOnCheckedChangeListener((buttonView, checked) -> {
            prefs.edit().putBoolean("reminder_enabled", checked).apply();
            setReminderRowEnabled(checked);
        });
    }

    private void updateReminderText(int hour, int minute) {
        String formatted = String.format("Reminder time: %02d:%02d", hour, minute);
        tvReminderTime.setText(formatted);
    }

    private void setReminderRowEnabled(boolean enabled) {
        float alpha = enabled ? 1f : 0.4f;
        layoutReminderTime.setAlpha(alpha);
        layoutReminderTime.setClickable(enabled);
    }
}
=======
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
>>>>>>> 52986eb77ee08787b4f102365a269556124a8377
