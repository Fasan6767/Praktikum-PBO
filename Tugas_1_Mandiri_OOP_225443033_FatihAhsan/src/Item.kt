/**
 * Kelas induk abstrak yang merepresentasikan item umum di perpustakaan digital.
 *
 * @property id Identifier unik untuk item.
 * @property title Judul item.
 * @property year Tahun rilis atau terbit item.
 */
abstract class Item(
    val id: String,
    val title: String,
    val year: Int
) {
    /**
     * Status ketersediaan item untuk dipinjam.
     * Pengubahan status hanya dapat dilakukan secara internal di dalam kelas ini atau turunan/metodenya.
     */
    var isAvailable: Boolean = true
        private set

    /**
     * Menghitung besaran denda keterlambatan per hari.
     * Harus diimplementasikan oleh subclass sesuai jenis item.
     *
     * @return Nominal denda per hari (Rupiah).
     */
    abstract fun calculateFinePerDay(): Double

    /**
     * Mengembalikan nama jenis item.
     *
     * @return Jenis item ("Buku", "Jurnal", atau "DVD").
     */
    abstract fun getItemType(): String

    /**
     * Mengembalikan durasi maksimal peminjaman item dalam hari.
     *
     * @return Batas peminjaman (hari).
     */
    abstract fun getMaxBorrowDays(): Int

    /**
     * Melakukan proses peminjaman item.
     *
     * @return `true` jika peminjaman berhasil, `false` jika item sedang tidak tersedia.
     */
    open fun borrow(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            println(" Berhasil meminjam ${getItemType()}: '$title' (ID: $id).")
            true
        } else {
            println(" Gagal meminjam: Item '$title' (ID: $id) sedang tidak tersedia.")
            false
        }
    }

    /**
     * Melakukan proses pengembalian item dan menghitung denda jika ada keterlambatan.
     *
     * @param daysLate Jumlah hari keterlambatan pengembalian (default: 0).
     * @return Total nominal denda yang harus dibayar.
     */
    open fun returnItem(daysLate: Int = 0): Double {
        if (!isAvailable) {
            isAvailable = true
            val totalFine = daysLate * calculateFinePerDay()
            print(" Item '$title' berhasil dikembalikan.")
            if (totalFine > 0) {
                println(" Terlambat $daysLate hari. Total Denda: Rp ${totalFine.toLong()}")
            } else {
                println(" Dikembalikan tepat waktu.")
            }
            return totalFine
        } else {
            println(" Peringatan: Item '$title' (ID: $id) belum/tidak sedang dipinjam.")
            return 0.0
        }
    }

    /**
     * Menampilkan informasi terstruktur dari item.
     * Dapat di-override oleh subclass untuk menambahkan detail spesifik.
     */
    open fun displayInfo() {
        println("----------------------------------------")
        println("ID Item          : $id")
        println("Judul            : $title")
        println("Jenis            : ${getItemType()}")
        println("Tahun            : $year")
        println("Status           : ${if (isAvailable) "Tersedia" else "Dipinjam"}")
        println("Denda / Hari     : Rp ${calculateFinePerDay().toLong()}")
        println("Maks Pinjam      : ${getMaxBorrowDays()} Hari")
    }
}