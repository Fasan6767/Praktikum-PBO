/**
 * Kelas utama pengelola koleksi, keanggotaan, dan transaksi sistem perpustakaan.
 *
 * @property name Nama perpustakaan.
 */
class Library(
    val name: String
) {
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    val totalItems: Int get() = items.size
    val availableItems: Int get() = items.count { it.isAvailable }
    val totalMembers: Int get() = members.size
    val totalTransactions: Int get() = transactions.size

    fun addItem(item: Item) {
        items.add(item)
        println(" Item '${item.title}' (${item.getItemType()}) berhasil ditambahkan ke pustaka.")
    }

    fun addItems(vararg newItems: Item) {
        newItems.forEach { addItem(it) }
    }

    fun findItem(id: String): Item? {
        return items.find { it.id.equals(id, ignoreCase = true) }
    }

    fun searchItems(keyword: String): List<Item> {
        return items.filter {
            it.title.contains(keyword, ignoreCase = true) || it.id.contains(keyword, ignoreCase = true)
        }
    }

    fun registerMember(id: String, name: String, email: String, phone: String): Boolean {
        if (members.any { it.id.equals(id, ignoreCase = true) }) {
            println(" Gagal Registrasi: Member ID '$id' sudah terdaftar.")
            return false
        }
        val member = Member(id, name, email, phone)
        members.add(member)
        println(" Member '${name}' ($id) berhasil didaftarkan.")
        return true
    }

    fun findMember(id: String): Member? {
        return members.find { it.id.equals(id, ignoreCase = true) }
    }

    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println(" Transaksi Gagal: Member ID '$memberId' tidak ditemukan.")
            return null
        }
        if (item == null) {
            println(" Transaksi Gagal: Item ID '$itemId' tidak ditemukan.")
            return null
        }

        val trx = member.borrowItem(item)
        if (trx != null) {
            transactions.add(trx)
        }
        return trx
    }

    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double {
        val member = findMember(memberId)
        val item = findItem(itemId)

        if (member == null) {
            println(" Transaksi Gagal: Member ID '$memberId' tidak ditemukan.")
            return 0.0
        }
        if (item == null) {
            println(" Transaksi Gagal: Item ID '$itemId' tidak ditemukan.")
            return 0.0
        }

        return member.returnItem(item, daysLate)
    }

    fun displayAllItems() {
        println("\n========================================")
        println("         DAFTAR SELURUH ITEM ($totalItems)")
        println("========================================")
        items.forEach { it.displayInfo() }
    }

    fun displayAvailableItems() {
        println("\n========================================")
        println("       DAFTAR ITEM TERSEDIA ($availableItems)")
        println("========================================")
        items.filter { it.isAvailable }.forEach {
            println("  [${it.id}] ${it.title} - ${it.getItemType()} (${it.year})")
        }
    }

    fun displayAllMembers() {
        println("\n========================================")
        println("       DAFTAR SELURUH ANGGOTA ($totalMembers)")
        println("========================================")
        members.forEach { it.displayInfo() }
    }

    fun displayAllTransactions() {
        println("\n========================================")
        println("     DAFTAR TRANSAKSI SISTEM ($totalTransactions)")
        println("========================================")
        transactions.forEach { it.displayTransaction() }
    }

    fun displayReport() {
        val grandTotalFines = members.sumOf { it.totalFines }
        val borrowedCount = totalItems - availableItems

        println("\n========================================")
        println("    LAPORAN RINGKASAN PERPUSTAKAAN")
        println("========================================")
        println("Nama Perpustakaan : $name")
        println("Total Katalog     : $totalItems Item")
        println("  ├─ Tersedia     : $availableItems Item")
        println("  └─ Dipinjam     : $borrowedCount Item")
        println("Total Member      : $totalMembers Anggota")
        println("Total Transaksi   : $totalTransactions Transaksi")
        println("Akumulasi Denda   : Rp ${grandTotalFines.toLong()}")
        println("========================================")
    }
}