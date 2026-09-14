/**
 * Representasi hierarki status transaksi peminjaman menggunakan Sealed Class.
 */
sealed class TransactionStatus {
    /**
     * Menghasilkan representasi string tampilan dari status transaksi.
     *
     * @return String deskripsi status.
     */
    abstract fun display(): String

    /**
     * Memeriksa apakah transaksi sudah mencapai tahap akhir (terminal state).
     *
     * @return `true` jika status Returned atau Cancelled, `false` untuk status lainnya.
     */
    fun isFinal(): Boolean {
        return this is Returned || this is Cancelled
    }

    /** Status item sedang dipinjam. */
    object Borrowed : TransactionStatus() {
        override fun display(): String = " Dipinjam"
    }

    /** Status item sudah dikembalikan tepat waktu. */
    object Returned : TransactionStatus() {
        override fun display(): String = " Dikembalikan"
    }

    /**
     * Status peminjaman mengalami keterlambatan.
     *
     * @property daysLate Jumlah hari keterlambatan.
     */
    data class Overdue(val daysLate: Int) : TransactionStatus() {
        override fun display(): String = " Terlambat ($daysLate hari)"
    }

    /** Status peminjaman dibatalkan. */
    object Cancelled : TransactionStatus() {
        override fun display(): String = " Dibatalkan"
    }
}