# 📔 Mood Diary - Aplikasi Jurnal dan Pelacak Mood Harian

![Status](https://img.shields.io/badge/status-active-success.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Language](https://img.shields.io/badge/language-Java-orange.svg)

Aplikasi **Mood Diary** adalah proyek tugas akhir yang dikembangkan menggunakan Android Studio (Java) dan SQLite sebagai database lokal. Aplikasi ini bertujuan membantu pengguna melacak, mencatat, dan menganalisis pola emosi harian mereka dengan fitur keamanan (password) dan visualisasi data.

---

## 🌟 Fitur Utama Aplikasi

| Fitur | Deskripsi | Status |
|-------|-----------|--------|
| 🔒 **Layar Password (Login/Setup)** | Memastikan keamanan data dengan layar password sebelum masuk | ✅ |
| 📝 **Input Mood Harian** | Form lengkap untuk mencatat level mood, catatan, dan tag aktivitas | ✅ |
| 📅 **Mood History & Calendar** | Melihat riwayat mood yang dicatat dalam tampilan kalender | ⏳ |
| 📊 **Statistik Mood** | Visualisasi tren mood (rata-rata mood mingguan/bulanan) | ⏳ |
| ☀️ **Quote Motivasi Harian** | Menampilkan pesan inspiratif pada Dashboard | ✅ |
| 🌙 **Dark & Light Theme** | Opsi pengaturan tema tampilan | ⏳ |
| ⏰ **Reminder Harian** | Notifikasi pengingat untuk mencatat mood | ⏳ |

---

## 🛠️ Persyaratan Teknis dan Teknologi

- **Bahasa**: Java
- **IDE**: Android Studio
- **Target SDK**: API 30+ (atau sesuai kebutuhan proyek)
- **Database**: SQLite (Database Lokal)
- **Arsitektur UI**: Single Activity (MainActivity) dengan Multiple Fragments
- **Desain**: Material Design Components

---

## 📂 Struktur File dan Penjelasan Kode

### I. Java Files (Logika Aplikasi)

| File | Package | Kegunaan |
|------|---------|----------|
| `LoginActivity.java` | `.mooddiary` | Activity Pertama. Menangani proses Setel Password (pertama kali) dan Login (verifikasi password) menggunakan SharedPreferences |
| `MainActivity.java` | `.mooddiary` | Activity Utama. Bertindak sebagai wadah (container) yang memuat semua Fragments dan mengelola navigasi menggunakan BottomNavigationView |
| `HomeFragment.java` | `.mooddiary` | Dashboard. Menampilkan ringkasan mood hari ini, Quote Motivasi Harian, dan tombol akses cepat (FloatingActionButton) ke Input Mood |
| `InputMoodFragment.java` | `.mooddiary` | Form Input Utama. Mengumpulkan data mood harian, catatan (Text Input), level mood (Radio Button/Selection), dan aktivitas (Checkbox), lalu menyimpannya ke database |
| `MoodCalendarFragment.java` | `.mooddiary` | Menampilkan riwayat mood dalam format kalender dan detail entri berdasarkan tanggal yang dipilih |
| `StatsFragment.java` | `.mooddiary` | Menghitung dan menampilkan statistik (rata-rata, frekuensi) dalam bentuk teks dan visualisasi (misalnya, grafik) |
| `SettingsFragment.java` | `.mooddiary` | Mengelola pengaturan aplikasi seperti pengaktifan Dark/Light Theme dan pengaturan waktu Reminder Harian |
| `DatabaseHelper.java` | `.database` | Database Handler (SQLite). Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) untuk tabel `mood_entries` |
| `MoodEntry.java` | `.model` | Model Data. Kelas Java sederhana yang mendefinisikan struktur data untuk setiap entri mood (ID, tanggal, level, catatan, aktivitas) |

### II. Resource Files (Desain dan Sumber Daya)

| File | Lokasi | Kegunaan |
|------|--------|----------|
| `activity_main.xml` | `res/layout` | Layout utama aplikasi, mendefinisikan CoordinatorLayout dan BottomNavigationView |
| `fragment_home.xml` | `res/layout` | Desain dashboard (termasuk CardView untuk quote dan ringkasan mood) |
| `fragment_input_mood.xml` | `res/layout` | Desain form input mood, termasuk elemen TextInputLayout dan RadioGroup |
| `bottom_nav_menu.xml` | `res/menu` | Mendefinisikan item-item (Home, Input, Stats, dll.) yang muncul di Bottom Navigation Bar |
| `colors.xml` | `res/values` | Mendefinisikan palet warna kustom aplikasi (color_primary, color_accent, dll.) |
| `AndroidManifest.xml` | Root | Konfigurasi Utama. Mendeklarasikan semua Activities dan menetapkan LoginActivity sebagai Activity peluncur (android.intent.action.MAIN) |

---

## 🚀 Instalasi dan Menjalankan Proyek

### 1. Clone Repository
```bash
git clone https://github.com/MICHAELCUY-lang/MoodDiary.git
```

### 2. Buka di Android Studio
Buka folder proyek di Android Studio (disarankan versi terbaru)

### 3. Sync Gradle
Pastikan proyek melakukan sinkronisasi Gradle dengan benar (mengunduh semua dependencies)

### 4. Jalankan
Pilih perangkat Emulator atau Device fisik, lalu tekan tombol Run (▶️)


##  Acknowledgments

- Material Design Components
- Android Documentation
- SQLite Database
