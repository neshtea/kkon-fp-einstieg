// Ein Computer besteht aus
// - Prozessor
// - Hauptspeicher-Kapazität in Gbyte
// - Festplattenspeicher-Kapazität in Gbyte

data class Computer(
    val processor: String,
    val ram: Int,
    val hardDrive: Int)

/**
 * berechne den gesamtspeicher von einem computer
 */
fun totalMemory(computer: Computer): Int =
    computer.ram + computer.hardDrive