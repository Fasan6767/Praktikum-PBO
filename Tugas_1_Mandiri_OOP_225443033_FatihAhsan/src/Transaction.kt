import java.time.LocalDate

/**
 * Kelas yang memodelkan satu entitas transaksi peminjaman.
 *
 * @property id ID unik transaksi.
 * @property item Objek item yang dipinjam.
 * @property member Objek anggota peminjam.
 * @property borrowDate Tanggal dimulainya peminjaman.
 * @property status Status terkini dari transaksi.
 */
class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString(),
    var status: TransactionStatus = TransactionStatus.Borrowed
) {
    /**
     * Memproses pengembalian item transaksi.
     *
     * @param daysLate Jumlah hari Keterlambatan.
     * @return Nominal denda yang dihasilkan.
     */
    fun returnItem(daysLate: Int): Double {
        if (status.isFinal()) {
            println(" Transaksi $id sudah ditutup ($status), tidak dapat diproses lagi.")
            return 0.0
        }

        val fine = item.returnItem(daysLate)
        status = if (daysLate > 0) {
            TransactionStatus.Overdue(daysLate)
        } else {
            TransactionStatus.Returned
        }
        return fine
    }

    /**
     * Membatalkan transaksi peminjaman.
     */
    fun cancel() {
        if (status.isFinal()) {
            println(" Gagal membatalkan: Transaksi $id sudah final.")
            return
        }

        status = TransactionStatus.Cancelled
        item.returnItem(0)
        println(" Transaksi $id berhasil dibatalkan.")
    }

    /**
     * Menampilkan detail informasi transaksi secara visual.
     */
    fun displayTransaction() {
        println("----------------------------------------")
        println("ID Transaksi : $id")
        println("Tanggal      : $borrowDate")
        println("Peminjam     : ${member.name} (${member.id})")
        println("Item         : ${item.title} [${item.getItemType()}]")
        println("Status       : ${status.display()}")
    }
}