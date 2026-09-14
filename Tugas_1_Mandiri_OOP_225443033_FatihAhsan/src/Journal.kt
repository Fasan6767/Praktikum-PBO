/**
 * Kelas yang merepresentasikan item berupa Jurnal.
 *
 * @param id ID unik jurnal.
 * @param title Judul jurnal.
 * @param year Tahun rilis.
 * @property publisher Penerbit jurnal.
 * @property volume Volume edisi jurnal.
 * @property issueNumber Nomor edisi jurnal.
 */
class Journal(
    id: String,
    title: String,
    year: Int,
    val publisher: String,
    val volume: Int,
    val issueNumber: Int
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 3000.0

    override fun getItemType(): String = "Jurnal"

    override fun getMaxBorrowDays(): Int = 7

    override fun displayInfo() {
        super.displayInfo()
        println("Penerbit         : $publisher")
        println("Volume           : $volume")
        println("Edisi            : $issueNumber")
    }
}