# LAPORAN JAWABAN PRAKTIKUM MODUL 2
## Event, State, dan Implisit Intent pada Jetpack Compose

---

### A. Percobaan 1: Perbandingan Imperatif (XML) dan Deklaratif (Compose)

1. **Pada project XML/View, objek apa yang diubah secara langsung ketika tombol diklik? Tunjukkan baris kodenya.**
   - **Jawaban**: Objek `TextView` (`tvMessage`).
   - **Baris Kode**:
     ```kotlin
     tvMessage.text = "Tombol sudah diklik"
     ```

2. **Pada project Compose, nilai apa yang diubah ketika tombol diklik? Tunjukkan baris kodenya.**
   - **Jawaban**: Nilai dari variabel State `message`.
   - **Baris Kode**:
     ```kotlin
     message = "Tombol sudah diklik"
     ```

3. **Mengapa pada Compose tidak terdapat perintah seperti `tvMessage.text` atau `setText()`?**
   - **Jawaban**: Karena Jetpack Compose menggunakan pendekatan **Deklaratif**, di mana antarmuka pengguna (UI) merupakan fungsi dari State ($UI = f(State)$). Pengembang tidak memanipulasi objek View secara langsung, melainkan memperbarui nilai State. Compose secara otomatis akan melakukan *Recomposition* untuk memperbarui tampilan UI yang bergantung pada State tersebut.

4. **Jelaskan alur perubahan pada XML/View dengan pola: `event -> View -> perubahan tampilan`!**
   - **Jawaban**: `User menekan tombol (onClick Event)` $\rightarrow$ `Program mendapatkan referensi objek View via findViewById()` $\rightarrow$ `Program mengubah properti text pada TextView secara langsung` $\rightarrow$ `Tampilan teks pada layar berubah`.

5. **Jelaskan alur perubahan pada Compose dengan pola: `event -> state -> recomposition -> perubahan tampilan`!**
   - **Jawaban**: `User menekan tombol (onClick Event)` $\rightarrow$ `Nilai variabel State (message) diperbarui` $\rightarrow$ `Compose mendeteksi perubahan State dan memicu Recomposition` $\rightarrow$ `Composable dipanggil ulang untuk merender UI terbaru di layar`.

6. **Apa yang terjadi jika `message` diganti menjadi variabel biasa: `var message = "Belum diklik"`? Jelaskan penyebabnya!**
   - **Jawaban**: Tampilan di layar tidak akan berubah meskipun nilai variabel `message` telah berubah di dalam memori. Penyebabnya adalah variabel biasa tidak memiliki mekanisme *observable state* yang dapat ditrack oleh Compose untuk memicu proses *Recomposition*.

7. **Tuliskan kesimpulan perbedaan cara berpikir imperatif dan deklaratif menggunakan kalimat Anda sendiri!**
   - **Jawaban**:
     - **Imperatif (XML/View)**: Berfokus pada *"Bagaimana cara mengubah UI"*, di mana developer secara manual mencari komponen UI dan mengubah propertinya langkah demi langkah.
     - **Deklaratif (Compose)**: Berfokus pada *"Bagaimana tampilan UI berdasarkan State saat ini"*, di mana framework secara otomatis memperbarui tampilan ketika State berubah.

---

### B. Percobaan 2: Event Handling & State

#### Bagian 1: Variabel Biasa (`var count = 0`)
8. **Apa yang muncul di LogCat setiap kali tombol "Tambah" diklik?**
   - **Jawaban**: LogCat menampilkan nilai `count` yang bertambah, contoh: `TEST: Counter berubah : 1`, `TEST: Counter berubah : 2`, dst.

9. **Apa yang terjadi pada tampilan aplikasi setiap kali tombol "Tambah" diklik?**
   - **Jawaban**: Tampilan di layar tidak berubah dan tetap menunjukkan `Counter : 0`.

10. **Apa yang menyebabkan hal tersebut terjadi?**
    - **Jawaban**: Karena `count` adalah variabel biasa, bukan merupakan State Compose yang dapat diamati (*observable*), sehingga perubahan nilainya tidak memicu *Recomposition*.

#### Bagian 2: Menggunakan State (`var count by remember { mutableStateOf(0) }`)
11. **Apa yang muncul di LogCat setiap kali tombol "Tambah" diklik?**
    - **Jawaban**: LogCat menampilkan nilai `count` yang bertambah (`TEST: Counter berubah : 1`, `TEST: Counter berubah : 2`, dst).

12. **Apa yang terjadi pada tampilan aplikasi setiap kali tombol "Tambah" diklik?**
    - **Jawaban**: Tampilan angka pada layar secara otomatis diperbarui bertambah (`Counter : 1`, `Counter : 2`, dst).

13. **Apa yang menyebabkan hal tersebut terjadi dan berbeda dengan percobaan sebelumnya?**
    - **Jawaban**: Penggunaan `mutableStateOf` menjadikan `count` sebagai State yang diamati oleh Compose, dan `remember` menjaga nilainya tetap tersimpan selama komposisi. Ketika nilainya berubah, Compose secara otomatis memicu *Recomposition*.

14. **Apa yang dimaksud recomposition pada Compose?**
    - **Jawaban**: *Recomposition* adalah proses di mana Jetpack Compose menjalankan ulang fungsi Composable yang terpengaruh oleh perubahan State untuk merender pembaruan antarmuka antarmuka pengguna sesuai dengan kondisi State terbaru.

#### Bagian 3: Dengan `OutlinedTextField`
15. **Apa yang muncul di LogCat setiap kali menginputkan huruf di Text Field?**
    - **Jawaban**: LogCat menampilkan karakter yang diinputkan (`TEST: Text berubah : [input_karakter]`).

16. **Apa yang terjadi pada tampilan aplikasi setiap kali menginputkan huruf di Text Field?**
    - **Jawaban**: Teks yang diketikkan pengguna langsung tampil di dalam `OutlinedTextField`.

17. **Apa yang menyebabkan hal tersebut terjadi?**
    - **Jawaban**: Event `onValueChange` memperbarui variabel State `text`, yang kemudian memicu *Recomposition* sehingga `OutlinedTextField` merender nilai `text` yang terbaru.

18. **Sebutkan state apa saja yang ada pada program yang terakhir dibuat!**
    - **Jawaban**:
      - `count` (tipe Data: `Int`)
      - `text` (tipe Data: `String`)

19. **Sebutkan event apa saja yang ada pada program yang terakhir dibuat!**
    - **Jawaban**:
      - Event `onClick` pada tombol `Button`.
      - Event `onValueChange` pada `OutlinedTextField`.

20. **Kapan event-event tersebut dijalankan?**
    - **Jawaban**:
      - `onClick`: Dijalankan saat pengguna menekan/mengetuk tombol "Tambah".
      - `onValueChange`: Dijalankan setiap kali pengguna mengetikkan atau menghapus karakter di dalam `OutlinedTextField`.

---

### C. Percobaan 3: Implisit Intent

21. **Perbedaan fungsi dari masing-masing action intent (`ACTION_VIEW`, `ACTION_DIAL`, `ACTION_SENDTO`):**
    - **`Intent.ACTION_VIEW`**: Digunakan untuk menampilkan data kepada pengguna (misalnya membuka URL di Browser atau koordinat lokasi di Maps).
    - **`Intent.ACTION_DIAL`**: Digunakan untuk membuka aplikasi Dial Telepon dengan nomor tujuan sudah terisi (tanpa melakukan panggilan otomatis).
    - **`Intent.ACTION_SENDTO`**: Digunakan untuk mengirimkan pesan/email ke alamat penerima tertentu (misalnya membuka aplikasi Email).

22. **Apa fungsi `data` pada intent (`Uri.parse(...)`)?**
    - **Jawaban**: Properti `data` berfungsi mendefinisikan URI (Uniform Resource Identifier) serta *data scheme* (seperti `https://`, `tel:`, `mailto:`, `geo:`) yang menentukan target data spesifik yang akan diproses oleh aplikasi penerima.

23. **Apa hubungan Action dan Data pada pilihan aplikasi yang dimunculkan oleh sistem Android?**
    - **Jawaban**: Sistem Android mencocokkan kombinasi **Action** dan **Data Scheme** dengan *Intent Filter* yang didaftarkan oleh aplikasi di perangkat. Kombinasi ini menentukan aplikasi mana yang kompatibel untuk menangani request tersebut (misal: `ACTION_VIEW` + `https://` dimunculkan Browser, sedangkan `ACTION_DIAL` + `tel:` dimunculkan aplikasi Telepon).
