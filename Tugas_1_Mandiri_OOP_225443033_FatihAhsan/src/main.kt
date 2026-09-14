/**
 * Fungsi utama (main) tempat eksekusi skenario sistem perpustakaan digital (Skenario D.2).
 */
fun main() {
    println("==========================================")
    println(" DEMO SISTEM MANAJEMEN PERPUSTAKAAN DIGITAL")
    println("==========================================\n")

    // 1. Inisialisasi Perpustakaan
    println(">>> 1. Inisialisasi Perpustakaan")
    val library = Library("Perpustakaan Kampus")
    println("Perpustakaan '${library.name}' siap beroperasi.")

    // 2. Tambah Item
    println("\n>>> 2. Menambahkan Item ke Katalog")
    val b1 = Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming")
    val b2 = Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education")
    val j1 = Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2)
    val j2 = Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1)
    val d1 = DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi")
    val d2 = DVD("D002", "The Matrix", 1999, "Wachowski", 136, "Action")

    library.addItems(b1, b2, j1, j2, d1, d2)

    // 3. Registrasi Anggota
    println("\n>>> 3. Registrasi Anggota Baru")
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@email.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@email.com", "08129876543")
    library.registerMember("M003", "Rizky Pratama", "rizky@email.com", "08125678901")

    // 4. Tampilkan Semua Item
    println("\n>>> 4. Menampilkan Semua Item Katalog")
    library.displayAllItems()

    // 5. Peminjaman (Skenario A)
    println("\n>>> 5. Skenario A: Peminjaman Item")
    library.borrowItem("M001", "B001") // Ahmad pinjam Pemrograman Kotlin
    library.borrowItem("M001", "D001") // Ahmad pinjam Inception
    library.borrowItem("M002", "J001") // Dewi pinjam Jurnal Teknologi Informasi
    library.borrowItem("M003", "B002") // Rizky pinjam Dasar-Dasar OOP

    // 6. Tampilkan Item Tersedia
    println("\n>>> 6. Menampilkan Item yang Masih Tersedia")
    library.displayAvailableItems()

    // 7. Tampilkan Transaksi Anggota
    println("\n>>> 7. Menampilkan Transaksi Anggota")
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 8. Pengembalian (Skenario B)
    println("\n>>> 8. Skenario B: Pengembalian Item")
    println("--> Ahmad mengembalikan 'Pemrograman Kotlin' (Tepat waktu)")
    library.returnItem("M001", "B001", daysLate = 0)

    println("\n--> Dewi mengembalikan 'Jurnal Teknologi Informasi' (Terlambat 3 Hari)")
    library.returnItem("M002", "J001", daysLate = 3)

    // 9. Tampilkan Transaksi Setelah Pengembalian
    println("\n>>> 9. Status Transaksi Setelah Pengembalian")
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 10. Demonstrasi Polimorfisme
    println("\n>>> 10. Demonstrasi Polimorfisme")
    val polyList: List<Item> = listOf(b1, j1, d1)
    for (item in polyList) {
        println("Jenis Item: ${item.getItemType()} | Judul: '${item.title}' | Denda/hari: Rp ${item.calculateFinePerDay().toLong()}")
    }

    // 11. Demonstrasi Sealed Class
    println("\n>>> 11. Demonstrasi Sealed Class (TransactionStatus)")
    val statuses: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )
    for (st in statuses) {
        val statusText = when (st) {
            is TransactionStatus.Borrowed -> "Status: " + st.display()
            is TransactionStatus.Returned -> "Status: " + st.display()
            is TransactionStatus.Overdue -> "Status: " + st.display() + " -> Total keterlambatan " + st.daysLate + " hari"
            is TransactionStatus.Cancelled -> "Status: " + st.display()
        }
        println(statusText)
    }

    // 12. Demonstrasi Smart Casting (`is` dan `as?`)
    println("\n>>> 12. Demonstrasi Smart Casting (is & as?)")
    val targetItem: Item? = library.findItem("B001")

    // Pengecekan dengan 'is'
    if (targetItem is Book) {
        println("Smart Cast 'is': Item ${targetItem.id} dikonfirmasi bertipe Book dengan ${targetItem.pages} halaman.")
    }

    // Mencoba casting ke tipe lain dengan 'as?'
    val castToDVD = targetItem as? DVD
    if (castToDVD == null) {
        println("Safe Cast 'as?': Gagal mengonversi item B001 ke DVD (Hasil return `null`).")
    } else {
        println("Safe Cast 'as?': Berhasil dikonversi ke DVD.")
    }

    // 13. Demonstrasi Enkapsulasi (Simulasi Proteksi Pembatasan Akses)
    println("\n>>> 13. Demonstrasi Enkapsulasi Data")
    println("1. Properti `isAvailable` pada Item dilindungi oleh `private set`.")
    println("   Item.isAvailable hanya dapat diubah melalui metode .borrow() atau .returnItem().")
    println("2. Properti `email` & `phone` pada Member bersifat `private val`.")
    println("   Nilainya tidak bisa diubah langsung dari luar dan hanya diakses via getter .getEmail().")

    // 14. Laporan Akhir
    println("\n>>> 14. Tampilan Laporan Akhir Perpustakaan")
    library.displayReport()
}