import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Long {
        val disc = emptyList<Int>().toMutableList()
        // read disc
        var id = 0
        for(i in 0..input[0].lastIndex) {
            for(j in 0..<input[0][i].digitToInt()) {
                if (i.rem(2) == 0) {
                    disc.add(id)
                } else {
                    disc.add(-1)
                }
            }
            if (i.rem(2) == 0) {
                id++
            }
        }
//        println(disc)
        // defrag disc
        for (i in 0..disc.lastIndex) {
            if (disc[i] >= 0) continue
            for (j in disc.lastIndex downTo i) {
                if (disc[j] >= 0) {
                    disc[i] = disc[j]
                    disc[j] = -1
                    break
                }
            }
//            println(disc)
        }
        // calculate checksum
        var checksum : Long = 0
        for (i in 0..disc.lastIndex) {
            if (disc[i] > 0) {
                checksum += i * disc[i]
            }
        }
        return checksum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 1928.toLong())

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
