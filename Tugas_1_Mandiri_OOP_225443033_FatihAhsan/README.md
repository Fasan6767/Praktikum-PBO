# Sistem Manajemen Perpustakaan Digital

Tugas Mandiri Pemrograman Berorientasi Objek (OOP) berbasis bahasa Kotlin.

## Identitas Mahasiswa
- **Nama** : Fatih Ahsan
- **NIM**  : 225443033
- **Kelas** : 2AEC2

## Deskripsi Program
Program ini merupakan aplikasi konsol Sistem Manajemen Perpustakaan Digital. Aplikasi ini mampu mengelola berbagai tipe item media (Buku, Jurnal, DVD), keanggotaan perpustakaan, alur peminjaman & pengembalian, status transaksi dinamis menggunakan `Sealed Class`, serta menghitung denda keterlambatan pengembalian secara polimorfik.

## Fitur & Konsep OOP
1. **Abstraksi**: Abstract class `Item` sebagai kerangka umum item perpustakaan.
2. **Pewarisan (Inheritance)**: Subclass `Book`, `Journal`, dan `DVD` yang memperluas atribut `Item`.
3. **Polimorfisme**: Dynamic dispatch pada pemanggilan `calculateFinePerDay()` & `getItemType()`.
4. **Enkapsulasi**: Penggunaan `private set` pada status ketersediaan item serta pembatasan akses field `email` dan `phone`.
5. **Sealed Class & Smart Casting**: Pemodelan status transaksi aman melalui `TransactionStatus` dan exhaustive `when`.

## Cara Menjalankan Program
1. Clone atau persiapkan struktur folder proyek sesuai spesifikasi.
2. Buka proyek menggunakan **IntelliJ IDEA**.
3. Pastikan SDK Java/Kotlin sudah terkonfigurasi.
4. Jalankan file `src/Main.kt`.