# LAPORAN PRAKTIKUM PAPB - MODUL 3
## Event, State, dan Implisit Intent pada Jetpack Compose

**Nama**: Muhammad Ahmad Rofius Suud  
**NIM**: 245150600111038  
**Kelas**: PAPB  

---

### A. Perbandingan Imperatif dan Deklaratif dalam Perubahan Teks

#### Aktivitas Percobaan

1. **Jalankan kedua aplikasi dan ambil screenshot kondisi sebelum serta sesudah tombol diklik.**
   - **Project A – ImperativeXmlApp (XML / Imperatif)**:
     - *Sebelum diklik*: Menampilkan teks "Belum diklik".
     - *Sesudah diklik*: Menampilkan teks "Tombol sudah diklik".
   - **Project B – DeclarativeComposeApp (Compose / Deklaratif)**:
     - *Sebelum diklik*: Menampilkan teks "Belum diklik".
     - *Sesudah diklik*: Menampilkan teks "Tombol sudah diklik".

2. **Pada project XML/View, objek apa yang diubah secara langsung ketika tombol diklik? Tunjukkan baris kodenya.**
   - **Jawab**: Pada project XML/View (imperatif), objek yang diubah secara langsung ketika tombol diklik adalah komponen `TextView` melalui variabel `tvMessage`. Secara spesifik, properti `.text` milik objek `TextView` tersebut dimodifikasi nilainya secara manual oleh kode program agar menampilkan teks baru.
   - **Baris kode**: `tvMessage.text = "Tombol sudah diklik"`

3. **Pada project Compose, nilai apa yang diubah ketika tombol diklik? Tunjukkan baris kodenya.**
   - **Jawab**: Pada project Jetpack Compose (deklaratif), hal yang diubah ketika tombol diklik bukanlah objek atau komponen UI secara langsung, melainkan nilai dari variabel State yaitu `message`. Ketika aksi klik dipicu, variabel ini diperbarui nilainya dan Compose akan merespons perubahan data tersebut dengan merender ulang tampilan secara otomatis.
   - **Baris kode**: `message = "Tombol sudah diklik"`

4. **Mengapa pada Compose tidak terdapat perintah seperti `tvMessage.text` atau `setText()`?**
   - **Jawab**: Pada Jetpack Compose tidak terdapat perintah seperti `tvMessage.text` atau `setText()` karena Compose mengusung paradigma deklaratif berbasis *State-Driven UI*. Dalam pendekatan ini, elemen UI berupa fungsi `@Composable Text()` bersifat immutable dan tidak memiliki instansi objek terpisah di memori yang bisa diakses atau diubah menggunakan metode setter. Tampilan UI di Compose murni merupakan proyeksi dari data (State), sehingga perubahan tampilan terjadi secara otomatis melalui proses *Recomposition* saat State berubah, bukan melalui manipulasi manual objek UI.

5. **Jelaskan alur perubahan pada XML/View dengan pola: `event -> View -> perubahan tampilan`.**
   - **Jawab**: Alur perubahan pada pendekatan XML/View diawali dari **event** saat pengguna melakukan interaksi, seperti mengklik tombol yang memicu callback `btnChange.setOnClickListener`. Selanjutnya, sistem mengarahkan eksekusi ke **View**, di mana kode mengakses objek `TextView` (`tvMessage`) yang telah dihubungkan sebelumnya dari ID layout menggunakan `findViewById`. Tahap terakhir adalah **perubahan tampilan**, di mana kode program menginstruksikan perubahan nilai properti secara langsung lewat `tvMessage.text = "Tombol sudah diklik"`, yang secara instan memerintahkan sistem Android untuk menggambar ulang piksel komponen tersebut di layar.

6. **Jelaskan alur perubahan pada Compose dengan pola: `event -> state -> recomposition -> perubahan tampilan`.**
   - **Jawab**: Alur perubahan pada Jetpack Compose dimulai dari **event**, yaitu saat pengguna mengklik tombol yang menjalankan blok perintah di dalam parameter `onClick`. Aksi tersebut memperbarui nilai **state** `message` dari "Belum diklik" menjadi "Tombol sudah diklik". Perubahan nilai state ini langsung dideteksi oleh Compose Runtime untuk memicu proses **recomposition**, yaitu pemanggilan ulang fungsi `@Composable ChangeTextScreen()` yang relevan. Hasil akhir dari proses ini adalah **perubahan tampilan**, di mana komponen `Text` digambar ulang ke layar menggunakan nilai state `message` yang paling baru.

7. **Apa yang terjadi jika `message` diganti menjadi variabel biasa: `var message = "Belum diklik"`? Jelaskan penyebabnya.**
   - **Jawab**: Jika variabel `message` diganti menjadi variabel biasa `var message = "Belum diklik"`, maka tampilan teks di layar emulator tidak akan pernah berubah dan tetap menampilkan tulisan "Belum diklik" meskipun tombol ditekan berulang kali. Hal ini disebabkan karena variabel `var` biasa tidak memiliki mekanisme State yang dapat dipantau oleh Compose Runtime, sehingga perubahan nilainya di dalam memori tidak akan pernah memicu proses *Recomposition* untuk menggambar ulang UI. Selain itu, tanpa adanya fungsi pembungkus `remember`, nilai variabel biasa tersebut akan selalu ter-reset kembali ke nilai inisialisasi awal setiap kali fungsi komponen dieksekusi ulang.

8. **Tuliskan kesimpulan perbedaan cara berpikir imperatif dan deklaratif menggunakan kalimat Anda sendiri.**
   - **Jawab**: Perbedaan utama antara cara berpikir imperatif dan deklaratif terletak pada fokus pengelolaan UI saat terjadi perubahan data. Pendekatan **imperatif** berfokus pada *bagaimana cara mengubah tampilan* (*how*), di mana pengembang harus menuliskan instruksi langkah demi langkah secara manual untuk mengambil objek UI dan mengubah propertinya ketika ada aksi pengguna. Sebaliknya, pendekatan **deklaratif** berfokus pada *apa bentuk tampilan yang diinginkan berdasarkan kondisi data* (*what*), di mana pengembang cukup mendefinisikan struktur UI yang terikat dengan State, lalu membiarkan framework yang secara otomatis mengurus pembaruan tampilan di layar setiap kali terjadi perubahan data.

---

### B. Event Handling State

#### Aktivitas Percobaan

1. **Jalankan program (variabel biasa `var count = 0`), buka LogCat, filter `TEST`. Klik tombol "Tambah" beberapa kali.**
   - *(Tempat Screenshot Tampilan App + LogCat TEST)*

2. **Apa yang muncul di LogCat setiap kali tombol "Tambah" diklik?**
   - **Jawab**: Di LogCat muncul teks pesan log bertuliskan `TEST : Counter berubah : 1`, `TEST : Counter berubah : 2`, `TEST : Counter berubah : 3`, dan seterusnya sesuai dengan jumlah klik yang dilakukan.

3. **Apa yang terjadi pada tampilan aplikasi setiap kali tombol "Tambah" diklik?**
   - **Jawab**: Pada tampilan aplikasi di layar emulator, teks `Counter : 0` sama sekali tidak mengalami perubahan dan tetap menampilkan angka `0` meskipun tombol telah diklik berulang kali.

4. **Apa yang menyebabkan hal tersebut terjadi?**
   - **Jawab**: Hal tersebut terjadi karena variabel `count` dideklarasikan sebagai variabel biasa (`var count = 0`), sehingga Jetpack Compose Runtime tidak memiliki mekanisme pemantauan (*observable*) untuk mendeteksi perubahan nilainya saat tombol diklik. Tanpa adanya fungsi pembungkus `mutableStateOf`, perubahan nilai variabel di memori tidak dapat memicu proses penderetan ulang UI (*Recomposition*). Selain itu, karena variabel tersebut tidak dibungkus dengan `remember`, nilai variabel akan selalu diinisialisasi ulang kembali ke nilai awal 0 setiap kali fungsi `@Composable` dipanggil ulang oleh sistem.

5. **Ubah kode program menggunakan state (`var count by remember { mutableStateOf(0) }`).**

6. **Kode lengkap `EventStateTest`:**
   ```kotlin
   @Composable
   fun EventStateTest() {
       var count by remember { mutableStateOf(0) }
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxSize()
       ) {
           Text("Counter : $count")
           Button(onClick = {
               count++
               Log.d("TEST", "Counter berubah : $count")
           }) {
               Text("Tambah")
           }
       }
   }
   ```

7. **Jalankan program kembali, buka LogCat filter `TEST`, klik tombol "Tambah" beberapa kali.**
   - *(Tempat Screenshot Tampilan App + LogCat TEST)*

8. **Apa yang muncul di LogCat setiap kali tombol "Tambah" diklik?**
   - **Jawab**: Di LogCat tetap muncul pesan log bertuliskan `TEST : Counter berubah : 1`, `TEST : Counter berubah : 2`, dst.

9. **Apa yang terjadi pada tampilan aplikasi setiap kali tombol "Tambah" diklik?**
   - **Jawab**: Pada tampilan aplikasi di layar emulator, teks Counter langsung berubah dan bertambah angkanya secara otomatis (`Counter : 1`, `Counter : 2`, `Counter : 3`, dst) setiap kali tombol "Tambah" ditekan.

10. **Apa yang menyebabkan hal tersebut terjadi dan berbeda dengan percobaan sebelumnya?**
    - **Jawab**: Hal tersebut dapat terjadi karena variabel `count` kini dibungkus menggunakan `mutableStateOf` dan `remember`, yang mengubahnya menjadi variabel berbasis State yang dilacak secara otomatis oleh Jetpack Compose Runtime. Perbedaannya dengan percobaan sebelumnya terletak pada mekanisme pemicuan UI; pada percobaan awal, perubahan nilai variabel biasa tidak mampu memberi tahu sistem untuk menggambar ulang layar. Dengan penggunaan State, setiap kali nilai `count` bertambah saat tombol diklik, Jetpack Compose secara otomatis mendeteksi perubahan tersebut dan memicu proses *Recomposition* untuk memperbarui tampilan teks di layar secara langsung.

11. **Apa yang dimaksud recomposition pada Compose?**
    - **Jawab**: *Recomposition* pada Jetpack Compose adalah proses pemanggilan atau eksekusi ulang fungsi-fungsi `@Composable` secara otomatis oleh sistem untuk memperbarui tampilan UI ketika terjadi perubahan nilai pada State yang diamati. Pada proses ini, Compose secara cerdas hanya menggambar dan memperbarui bagian komponen UI yang nilainya mengalami perubahan (*intelligent recomposition*), tanpa harus membangun kembali seluruh struktur layar aplikasi dari awal secara manual.

12. **Penambahan state `text` dan `OutlinedTextField` pada `EventStateTest`:**
    ```kotlin
    @Composable
    fun EventStateTest() {
        var count by remember { mutableStateOf(0) }
        var text by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Counter : $count")
            Button(onClick = {
                count++
                Log.d("TEST", "Counter berubah : $count")
            }) {
                Text("Tambah")
            }
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    Log.d("TEST", "Text berubah : $it")
                },
                label = { Text("Text Field") }
            )
        }
    }
    ```

13. **Jalankan program kembali, amati LogCat dan tampilan aplikasi.**
    - *(Tempat Screenshot Tampilan App + LogCat TEST)*

14. **Apa yang muncul di LogCat setiap kali menginputkan huruf di Text Field?**
    - **Jawab**: Di LogCat muncul log bertuliskan `TEST : Text berubah : [karakter_input]`, seperti `TEST : Text berubah : a`, `TEST : Text berubah : ab`, dst.

15. **Apa yang terjadi pada tampilan aplikasi setiap kali menginputkan huruf di Text Field?**
    - **Jawab**: Karakter/huruf yang diketikkan pengguna di keyboard muncul secara langsung di dalam kotak input `OutlinedTextField`, sementara nilai Counter di atasnya tidak terpengaruh dan tetap pada nilainya saat itu.

16. **Apa yang menyebabkan hal tersebut terjadi?**
    - **Jawab**: Hal tersebut terjadi karena variabel `count` dan `text` dikelola sebagai dua variabel State yang terpisah dan independen (`mutableStateOf`). Setiap event hanya memperbarui state miliknya masing-masing; interaksi pengetikan pada `OutlinedTextField` hanya memicu pembaruan pada state `text`, sementara penekanan tombol "Tambah" hanya memicu pembaruan pada state `count`. Karena nilai `count` tidak diubah di dalam blok callback `onValueChange` milik `OutlinedTextField`, maka perubahan input teks tidak akan memengaruhi tampilan angka Counter.

17. **Sebutkan state apa saja yang ada pada program yang terakhir dibuat!**
    - **Jawab**:
      - `count` (`var count by remember { mutableStateOf(0) }`): State bertipe data integer yang menyimpan dan mengontrol nilai angka pada tampilan Counter.
      - `text` (`var text by remember { mutableStateOf("") }`): State bertipe data string yang menyimpan dan mengontrol nilai teks pada komponen `OutlinedTextField`.

18. **Sebutkan event apa saja yang ada pada program yang terakhir dibuat!**
    - **Jawab**:
      - `onClick`: Event aksi klik yang didefinisikan pada komponen `Button`.
      - `onValueChange`: Event perubahan nilai teks yang didefinisikan pada komponen `OutlinedTextField`.

19. **Kapan event-event tersebut dijalankan?**
    - **Jawab**:
      - `onClick` dijalankan ketika pengguna menekan atau mengetuk (*tap*) tombol "Tambah" pada layar.
      - `onValueChange` dijalankan setiap kali ada perubahan karakter pada komponen `OutlinedTextField`, seperti saat pengguna mengetik, menghapus, atau menempelkan (*paste*) teks via keyboard.

---

### C. Implisit Intent

#### Aktivitas Percobaan

1. **Jalankan program `ImplicitIntentTest` dan lakukan pengujian pada setiap tombol.**
   - *(Tempat Screenshot Tampilan Buka Browser, Telepon, Email, dan Maps)*

2. **Perbedaan fungsi dari masing-masing action intent (`ACTION_VIEW`, `ACTION_DIAL`, `ACTION_SENDTO`):**
   - **`ACTION_VIEW`**: Berfungsi umum untuk menampilkan (*display*) informasi ke pengguna. Perilakunya berubah tergantung skema URI (`https://` untuk membuka web browser, `geo:` untuk membuka peta/maps).
   - **`ACTION_DIAL`**: Berfungsi khusus untuk menyiapkan panggilan telepon dengan menampilkan nomor di layar dialer tanpa langsung menelpon secara otomatis.
   - **`ACTION_SENDTO`**: Berfungsi khusus untuk mengirim pesan/email ke tujuan tertentu (`mailto:`) tanpa memerlukan lampiran biner umum, dan biasanya memunculkan pemilih aplikasi (*chooser*) jika ada lebih dari satu aplikasi email.

3. **Apa fungsi `data` pada intent (`Uri.parse(...)`)?**
   - **Jawab**: Fungsi `data` pada Intent adalah untuk menentukan muatan informasi atau sumber daya (berupa objek `Uri`) yang akan diproses oleh aplikasi penerima. `data` memberikan konteks spesifik berupa rujukan/target (seperti alamat situs, alamat email, nomor telepon, atau titik koordinat) yang perlu diolah saat tindakan (*action*) dijalankan.

4. **Apa hubungan Action dan Data pada pilihan aplikasi yang dimunculkan oleh sistem Android?**
   - **Jawab**: Hubungan antara Action dan Data sangat erat dalam proses pencocokan (*Intent Resolution*) oleh sistem Android. Action menentukan jenis tindakan yang ingin dilakukan (misalnya `ACTION_VIEW` atau `ACTION_SENDTO`), sedangkan Data menentukan skema/tipe sumber daya yang akan ditangani (misalnya `https://`, `mailto:`, atau `geo:`). Sistem Android mencocokkan kombinasi Action dan skema Data tersebut dengan kriteria yang terdaftar pada *Intent Filter* milik aplikasi-aplikasi yang terinstall di perangkat. Jika terdapat lebih dari satu aplikasi yang terdaftar mampu menangani kombinasi Action dan skema Data tersebut, sistem Android akan memunculkan dialog pemilih (*app chooser*) agar pengguna dapat memilih aplikasi yang ingin digunakan.

---

### D. Tugas Halaman Tiket

**Kode Program**: `PemesananTiketScreen.kt`  
**Fitur**:
- Card Harga Tiket (Rp25.000)
- Card Jumlah Tiket (Tombol `-` & `+` dengan counter)
- Card Total Bayar (Otomatis terhitung `Jumlah * Rp25.000`)
- Tombol RESET (Mengembalikan jumlah tiket ke 1)

*(Tempat Screenshot Tampilan Pemesanan Tiket saat Jumlah Tiket = 3 dan Total = Rp75.000)*
