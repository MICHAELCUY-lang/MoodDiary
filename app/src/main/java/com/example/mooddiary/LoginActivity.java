package com.example.mooddiary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;import android.content.SharedPreferences;import android.content.SharedPreferences;import android.widget.EditText;import android.widget.Button;import android.widget.Toast;import android.content.Intent;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private static final String PREF_PASSWORD = "app_password";
    private static final String PREF_NAME = "MyMoodDiaryPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);

        final String savedPassword = prefs.getString(PREF_PASSWORD, null);

        if (savedPassword == null) {
            // Pengaturan Password Pertama Kali
            etPassword.setHint("Setel Password Baru");
            btnLogin.setOnClickListener(v -> {
                String newPass = etPassword.getText().toString();
                if (newPass.length() >= 4) {
                    prefs.edit().putString(PREF_PASSWORD, newPass).apply(); // Simpan Password
                    Toast.makeText(this, "Password berhasil disetel!", Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                } else {
                    Toast.makeText(this, "Password minimal 4 karakter.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Proses Login
            etPassword.setHint("Masukkan Password");
            btnLogin.setOnClickListener(v -> {
                String inputPass = etPassword.getText().toString();
                if (inputPass.equals(savedPassword)) { // Perlu hashing yang lebih baik!
                    goToMainActivity();
                } else {
                    Toast.makeText(this, "Password salah.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}