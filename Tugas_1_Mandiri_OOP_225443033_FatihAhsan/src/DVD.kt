/**
 * Kelas yang merepresentasikan item berupa DVD.
 *
 * @param id ID unik DVD.
 * @param title Judul film/rekaman DVD.
 * @param year Tahun rilis.
 * @property director Sutradara film.
 * @property duration Durasi DVD dalam satuan menit.
 * @property genre Genre DVD.
 */
class DVD(
    id: String,
    title: String,
    year: Int,
    val director: String,
    val duration: Int,
    val genre: String
) : Item(id, title, year) {

    override fun calculateFinePerDay(): Double = 5000.0

    override fun getItemType(): String = "DVD"

    override fun getMaxBorrowDays(): Int = 3

    override fun displayInfo() {
        super.displayInfo()
        println("Sutradara        : $director")
        println("Durasi           : $duration menit")
        println("Genre            : $genre")
    }
}