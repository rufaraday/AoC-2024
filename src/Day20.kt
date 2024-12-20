fun main() {
    val directions = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
    fun findMoves(pos: Pair<Int, Int>, area: List<List<Char>>) : List<Pair<Int, Int>> {
        val moves = mutableListOf<Pair<Int, Int>>()
        try {
            directions.forEach {
                val p = pos.plus(it)
                if (area[p.second][p.first] in arrayOf('.', 'E')) {
                    moves.add(p)
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of racetrack
        }
        return moves.toList()
    }

    fun findCheats(pos: Pair<Int, Int>, area: List<List<Char>>) : List<Pair<Int, Int>> {
        val chaets = mutableListOf<Pair<Int, Int>>()
        try {
            directions.forEach {
                val p = pos.plus(it)
                val pp = p.plus(it)
                if (area[p.second][p.first] == '#' && area[pp.second][pp.first] in arrayOf('.', 'E')) {
                    chaets.add(p)
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of racetrack
        }
        return chaets.toList()
    }

    fun trackLength(
        pos: Pair<Int, Int>,
        area: MutableList<MutableList<Char>>,
        trackLength: Int
    ): Int {
        var p = pos
        var finish : Boolean
        var length = trackLength
        do {
            val moves = findMoves(p, area)
            if (moves.isEmpty()) {
                return 0
            }
            p = moves.first()
            finish = area[p.second][p.first] == 'E'
            area[p.second][p.first] = 'X'
            printCharMatrix(area)
            Thread.sleep(200)
            length++
        } while (!finish)
        return length
    }

    fun part1(input: List<String>, threshold: Int): Int {
        var area = readCharMatrixInput(input)
        printCharMatrix(area)
        var start = findPosition('S', area)
        val end = findPosition('E', area)
        var pos = start
        var trackLength = 0

        println("Looking for route from start $bold$start$reset to end $bold$end$reset")
        trackLength = trackLength(pos, area, trackLength)
        println("Total length: $trackLength")

        val cheats = mutableSetOf<Triple<Int, Int, Int>>()
        println("Looking for possible cheats")
        area = readCharMatrixInput(input)
        start = findPosition('S', area)

        return cheats.count {it.third >= threshold}
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day20_test")
    check(part1(testInput, 0) == 44)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day20")
    part1(input, 100).println()
    part2(input).println()
}
