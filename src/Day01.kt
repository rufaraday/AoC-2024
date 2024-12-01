import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val locations1 = mutableListOf<Int>()
        val locations2 = mutableListOf<Int>()
        input.forEach() {
            val tuple = it.split(" ")
//            println("${tuple.first()} - ${tuple.last()}")
            locations1.add(tuple.first().toInt())
            locations2.add(tuple.last().toInt())
        }
        locations1.sort()
        locations2.sort()
        var sum = 0
        for (i in 0..<locations1.size) {
            sum += abs(locations1[i]-locations2[i])
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        val locations1 = mutableListOf<Int>()
        val locations2 = mutableListOf<Int>()
        input.forEach() {
            val tuple = it.split(" ")
//            println("${tuple.first()} - ${tuple.last()}")
            locations1.add(tuple.first().toInt())
            locations2.add(tuple.last().toInt())
        }

        locations1.forEach() {
            sum += locations2.count{value: Int -> it == value} * it
        }

        return sum
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)
//
//    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
