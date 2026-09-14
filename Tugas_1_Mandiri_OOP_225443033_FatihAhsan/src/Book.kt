/**
 * Kelas yang merepresentasikan item berupa Buku.
 *
 * @param id ID unik buku.
 * @param title Judul buku.
 * @param year Tahun terbit.
 * @property author Nama penulis buku.
 * @property pages Jumlah halaman buku.
 * @property genre Genre buku.
 */
class Book(
    id: String,
    title: String,
    year: Int,
    val author: String,
    val pages: Int,
    val genre: String
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 2000.0

    override fun getItemType(): String = "Buku"

    override fun getMaxBorrowDays(): Int = 14

    override fun displayInfo() {
        super.displayInfo()
        println("Penulis          : $author")
        println("Jumlah Halaman   : $pages")
        println("Genre            : $genre")
    }
}