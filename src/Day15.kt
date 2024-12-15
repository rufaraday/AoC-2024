import kotlin.contracts.contract

fun main() {
    fun findPosition(c: Char, area: MutableList<MutableList<Char>>): Pair<Int, Int> {
        var x = 0
        var y = 0
        while (y < area.size) {
            x = area[y].indexOf(c)
            // check if found
            if (x >= 0) {
                break
            }
            y++
        }
        return x to y
    }

    fun part1(input: List<String>): Int {
        val area = mutableListOf<MutableList<Char>>()
        val moves = mutableListOf<Char>()
        var areaSection = true
        input.forEach {
            if (it == "") {
                areaSection = false
            } else {
                if (areaSection) {
                    area.add(it.toMutableList())
                } else {
                    moves.addAll(it.toMutableList())
                }
            }
        }
        printCharMatrix(area)
        println("moves sequence: $moves")
        var robotPos = findPosition('@', area)
        println(robotPos)
        // Move
        moves.forEach {move ->
            val vector = when (move) {
                'v' -> 0 to 1
                '^' -> 0 to -1
                '>' -> 1 to 0
                '<' -> -1 to 0
                else -> 0 to 0
            }
            var movingElements = mutableListOf(robotPos)
            val nextPos = robotPos.plus(vector)
            area[nextPos.second][nextPos.first]
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testSmallInput = readInput("Day15_test_small")
    check(part1(testSmallInput) == 2028)
    val testInput = readInput("Day15_test")
    check(part1(testInput) == 10092)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}
