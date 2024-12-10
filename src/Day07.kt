import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Long {
        val calibrations = emptyList<Pair<Long, List<Int>>>().toMutableList()
        var accu : Long = 0
        input.forEach() {
            val numbers = it.split(" ")
            val calibration = numbers.first().dropLast(1).toLong() to numbers.drop(1).map{ it.toInt()}
            calibrations.add(calibration)
            println("result = ${calibration.first}, numbers(${calibration.second.size}) = ${calibration.second}")
            for(i in 0..(2.toDouble().pow(calibration.second.lastIndex)-1).toInt()) {
                val operations = i.toString(2).padStart(calibration.second.lastIndex, '0')
                var result = calibration.second[0].toLong()
                for (j in 1..calibration.second.lastIndex) {
                    if (operations[j-1] == '0') {
                        result += calibration.second[j]
                    } else {
                        result *= calibration.second[j]
                    }
                }
                if (result == calibration.first) {
                    accu += result
                    break
                }
            }
            println()
        }
        println(calibrations)
        return accu
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day07_test.txt` file:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749.toLong())

    // Read the input from the `src/Day07.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
