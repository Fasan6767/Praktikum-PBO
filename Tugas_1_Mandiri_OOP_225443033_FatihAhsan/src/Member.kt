/**
 * Kelas yang merepresentasikan Anggota Perpustakaan.
 *
 * @property id ID unik anggota.
 * @property name Nama lengkap anggota.
 * @param email Email anggota (dikapsulasi).
 * @param phone Nomor telepon anggota (dikapsulasi).
 */
class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
) {
    private val transactions: MutableList<Transaction> = mutableListOf()

    /** Menghitung total akumulasi transaksi yang pernah dibuat oleh anggota. */
    val transactionCount: Int
        get() = transactions.size

    /** Menghitung total denda dari transaksi yang berstatus Overdue. */
    val totalFines: Double
        get() = transactions.sumOf { trx ->
            val currentStatus = trx.status
            if (currentStatus is TransactionStatus.Overdue) {
                currentStatus.daysLate * trx.item.calculateFinePerDay()
            } else {
                0.0
            }
        }

    /** Menghitung total transaksi peminjaman yang sedang aktif. */
    val activeBorrows: Int
        get() = transactions.count { it.status is TransactionStatus.Borrowed }

    /** Getter email terenkapsulasi. */
    fun getEmail(): String = email

    /** Getter telepon terenkapsulasi. */
    fun getPhone(): String = phone

    /**
     * Meminjam item perpustakaan.
     *
     * @param item Objek item yang dipinjam.
     * @return Objek [Transaction] jika berhasil, `null` jika tidak memenuhi batasan.
     */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable) {
            println(" Gagal: Item '${item.title}' sedang tidak tersedia.")
            return null
        }

        if (activeBorrows >= 3) {
            println(" Gagal: Member ${name} sudah mencapai batas maksimum peminjaman (3 item).")
            return null
        }

        if (item.borrow()) {
            val transactionId = "TRX-${System.currentTimeMillis()}"
            val newTransaction = Transaction(transactionId, item, this)
            transactions.add(newTransaction)
            return newTransaction
        }

        return null
    }

    /**
     * Mengembalikan item yang dipinjam.
     *
     * @param item Objek item yang dikembalikan.
     * @param daysLate Keterlambatan pengembalian dalam hari.
     * @return Total denda.
     */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val trx = transactions.find { it.item.id == item.id && it.status is TransactionStatus.Borrowed }
        if (trx == null) {
            println(" Gagal: Tidak ditemukan riwayat pinjaman aktif untuk item '${item.title}' pada member $name.")
            return 0.0
        }

        return trx.returnItem(daysLate)
    }

    /**
     * Mengembalikan list transaksi anggota secara imutabel.
     */
    fun getTransactions(): List<Transaction> = transactions.toList()

    /**
     * Menampilkan profil ringkas anggota.
     */
    fun displayInfo() {
        println("----------------------------------------")
        println("ID Member    : $id")
        println("Nama         : $name")
        println("Email        : $email")
        println("Telepon      : $phone")
        println("Total Pinjam : $transactionCount Kali")
        println("Pinjam Aktif : $activeBorrows Item")
        println("Total Denda  : Rp ${totalFines.toLong()}")
    }

    /**
     * Menampilkan riwayat transaksi peminjaman milik anggota.
     */
    fun displayTransactions() {
        println("\n=== Riwayat Transaksi Member: $name ($id) ===")
        if (transactions.isEmpty()) {
            println("Belum ada riwayat transaksi.")
        } else {
            transactions.forEach { it.displayTransaction() }
        }
    }
}