# Praktikum PAPB - Event, State, & Pemesanan Tiket

Aplikasi Android berbasis **Jetpack Compose** untuk memenuhi tugas Praktikum PAPB (Pemrograman Aplikasi Perangkat Bergerak) mengenai **Event Handling**, **State Management**, **Implicit Intent**, serta pembuatan halaman **Pemesanan Tiket**.

---

## 👤 Identitas Mahasiswa

- **Nama**: Muhammad Ahmad Rofius Suud
- **NIM**: 245150600111038
- **Kelas**: PAPB

---

## 📂 Dokumen & Berkas Lampiran

Seluruh screenshot pengujian aplikasi serta laporan PDF praktikum dapat diakses melalui tautan Google Drive di bawah ini:

🔗 **[Akses Google Drive Lampiran & Laporan PDF](https://drive.google.com/drive/folders/1H6j5d75CT73S84NKppJSSia5kxWQUrYn?usp=sharing)**

### Rincian Berkas Lampiran:
- 📄 **Laporan Jawaban Praktikum (PDF)**
- 📸 **Screenshot Pemesanan Tiket (Kondisi Awal & Sesudah Interaksi)**
- 📸 **Screenshot Hasil Percobaan State, Logcat, & Implicit Intent**

---

## 🛠️ Fitur Utama Aplikasi

1. **Halaman Pemesanan Tiket (`PemesananTiketScreen`)**:
   - Harga Tiket Rp25.000 dengan perhitungan Total Bayar otomatis berbasis State.
   - Tombol pengatur jumlah tiket (`+` dan `-`) serta tombol `RESET`.
2. **Percobaan State & Recomposition (`EventStateTest`)**:
   - Penghitung (*Counter*) serta input teks pada `OutlinedTextField` dengan pelacakan Logcat `TEST`.
3. **Percobaan Implisit Intent (`ImplicitIntentTest`)**:
   - Navigasi eksternal untuk Buka Browser, Telepon, Kirim Email, dan Google Maps.
4. **Halaman Profil Mahasiswa (`HalamanUtama`)**:
   - Tampilan profil mahasiswa berbasis Compose Layout (`Row`, `Column`, `Box`, `Icon`).

---

## 📱 Teknologi yang Digunakan

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: Single Activity (`MainActivity`) dengan Tab Navigation
- **Tooling**: Android Gradle Plugin, Compose Compiler 2.0
