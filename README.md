# Kalkulator Operasi Kotlin (Higher-Order Function) 

Nama Kelompok 7 :

- Muhammad Habibi	235150201111063

- Roynaldo Saroha Sinaga	235150200111050

- Harry Phalosa Telaumbanua	235150200111052

Proyek sederhana ini adalah implementasi kalkulator dasar menggunakan bahasa pemrograman **Kotlin**. Tujuan utama dari proyek ini bukan sekadar menghitung angka, melainkan untuk mendemonstrasikan penerapan konsep **Higher-Order Function (HOF)** , **Lambda Expression** , dan penanganan *error* (*Exception Handling*) di Kotlin.

##  Konsep Utama yang Digunakan

Proyek ini dibangun menggunakan empat pilar utama dalam fungsionalitas Kotlin:

1. **Higher-Order Function (HOF)**
   Berbeda dengan fungsi biasa, HOF adalah fungsi yang "naik level" karena ia bisa menerima fungsi lain sebagai parameter, atau bahkan mengembalikan sebuah fungsi. 
   *Pada proyek ini, fungsi `applyOperation` bertindak sebagai HOF yang menerima dua angka dan satu fungsi operasi untuk mengeksekusi angka tersebut. Dengan pendekatan ini, kita tidak perlu membuat fungsi `tambah()`, `kurang()`, atau `bagi()` secara statis dan terpisah. Kita cukup membuat satu fungsi universal, lalu menyuntikkan perilakunya secara dinamis melalui parameter fungsi. Ini membuat arsitektur kode jauh lebih modular dan fleksibel.*

2. **Lambda Expression**
   Lambda adalah fungsi tanpa nama (anonim) yang ditulis dengan sangat ringkas di dalam kurung kurawal `{ }`. Ini membuat kode menjadi lebih bersih dan mudah dibaca.
   *Pada proyek ini, operasi penjumlahan, pengurangan, perkalian, dan pembagian ditulis sebagai variabel yang menyimpan lambda expression.*

3. **Exception Handling dengan ArithmeticException (Penanganan Edge Case)**
   Dalam matematika, pembagian dengan angka nol tidak terdefinisi dan merupakan *edge case* yang berisiko membuat aplikasi *crash* atau *Force Close*. 
   *Pada proyek ini, pembagian dengan nol ditangani secara aman menggunakan `ArithmeticException`. Di dalam lambda pembagian (`div`), terdapat logika pencegahan: jika angka pembagi bernilai `0`, maka program tidak akan melakukan eksekusi matematika. Sebagai gantinya, kode akan secara sengaja melempar (*throw*) `ArithmeticException` beserta pesan "ERROR (division by zero)". Cara ini mengubah error sistem yang fatal menjadi sinyal peringatan yang bisa kita kendalikan.*

4. **Data Class dan Try-Catch untuk Uji Kasus Pengujian**
   Untuk memastikan kalkulator berjalan baik, program harus melewati berbagai simulasi perhitungan, termasuk pembagian dengan nol.
   *Proyek ini menggunakan `data class` untuk membungkus parameter pengujian (angka pertama, angka kedua, fungsi lambda, dan simbol operasi) menjadi satu objek yang rapi. Kumpulan objek ini kemudian dimasukkan ke dalam sebuah `List` untuk diiterasi secara berurutan.*
   *Saat iterasi berjalan, pemanggilan HOF dieksekusi di dalam blok `try-catch`. Blok `try` akan menjalankan perhitungan normal. Jika ia mendeteksi skenario pembagian dengan nol, blok `catch` akan langsung menangkap `ArithmeticException` tersebut, mencetak pesan error-nya, dan membiarkan program melanjutkan pengujian ke kasus berikutnya dengan aman tanpa berhenti di tengah jalan.*

## Bagaimana Kode Ini Bekerja?

Kalkulator ini menggunakan satu fungsi utama untuk semua jenis perhitungan:

```kotlin
fun applyOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}
