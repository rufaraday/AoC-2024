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

    fun calculateGpsSum(area: MutableList<MutableList<Char>>): Int {
        var sum = 0
        for (y in 0..area.lastIndex) {
            for (x in 0..area[y].lastIndex) {
                if (area[y][x] == 'O') {
                    sum += 100 * y + x
                }
            }
        }
        return sum
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
//        printCharMatrix(area)
//        println("moves sequence: $moves")
        var robotPos = findPosition('@', area)
//        println(robotPos)
        // Move
        moves.forEach {move ->
//            println("$robotPos $move")
            val vector = when (move) {
                'v' -> 0 to 1
                '^' -> 0 to -1
                '>' -> 1 to 0
                '<' -> -1 to 0
                else -> 0 to 0
            }
            val movingElements = mutableListOf(robotPos)
            var nextPos = robotPos.plus(vector)
            while(area[nextPos.second][nextPos.first] !in listOf('.', '#')) {
                movingElements.add(nextPos)
                nextPos = nextPos.plus(vector)
            }
//            println(movingElements)
//            println("$nextPos : ${area[nextPos.second][nextPos.first]}")
            if (area[nextPos.second][nextPos.first] == '.') {
//                println("moving elements $movingElements in direction $move ($vector)")
                movingElements.reversed().forEach {
                    val next = it.plus(vector)
//                    println("  moving $it (${area[it.second][it.first]}) to $next (${area[next.second][next.first]})")
                    area[next.second][next.first] = area[it.second][it.first]
//                    println("  changing $it (${area[it.second][it.first]}) to \'.\'")
                    area[it.second][it.first] = '.'
                }
//                robotPos = findPosition('@', area)
//                println("old robot position $robotPos")
                robotPos = robotPos.plus(vector)
//                println("new robot position $robotPos")
            } else {
//                println("leaving elements $movingElements in place")
            }
//            printCharMatrix(area)
//            println()
        }
        // Calculate sum of all boxes' GPS coordinates
        return calculateGpsSum(area)
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
    check(part2(testInput) == 9021)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}
