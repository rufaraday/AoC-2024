fun main() {
    fun printMap(
        arrea: MutableList<CharArray>,
        position: Triple<Int, Int, Char>,
        distance: Int,
        sleep: Boolean
    ) {
        for (y in 0..<arrea.size) {
            for (x in 0..<arrea[y].size) {
                print(arrea[y][x])
            }
            println()
        }
        println("position = (${position.first}, ${position.second})")
        println("distance = $distance")
        if (sleep) Thread.sleep(1000)
    }

    fun part1(input: List<String>): Int {
        val arrea: MutableList<CharArray> = emptyList<CharArray>().toMutableList()
        input.forEach() {
            arrea.add(it.toCharArray())
        }
        var distance = 0
        val directions = arrayOf('^', '>', 'v', '<')
        lateinit var position : Triple<Int, Int, Char>
        for (y in 0..<arrea.size) {
            for (x in 0..<arrea[y].size) {
                print(arrea[y][x])
                if (directions.contains(arrea[y][x])) {
                    position = Triple(x, y, arrea[y][x])
                }
            }
            println()
        }

        println("position = (${position.first}, ${position.second})")
        println("distance = $distance")
        Thread.sleep(1000)

        while (position.first >= 0 && position.first < arrea[0].size && position.second >= 0 && position.second < arrea.size) {
            System.out.flush()
            // mark old position
            arrea[position.second][position.first] = 'X'
            distance++
            // move
            val nextPos : Triple<Int, Int, Char>
//            while () {
                // next potential move
                nextPos = when (position.third) {
                    '^' -> Triple(position.first, position.second - 1, position.third)
                    'v' -> Triple(position.first, position.second + 1, position.third)
                    '<' -> Triple(position.first - 1, position.second, position.third)
                    '>' -> Triple(position.first + 1, position.second, position.third)
                    else -> position
                }
            // collision detection
            // rotation
//            }
            // move position
            position = nextPos
            // print
            printMap(arrea, position, distance, true)
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
