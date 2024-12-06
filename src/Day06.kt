fun main() {
    fun inArea(
        position: Triple<Int, Int, Char>,
        area: MutableList<CharArray>
    ) = position.first >= 0 && position.first < area[0].size && position.second >= 0 && position.second < area.size

    fun printMap(
        area: MutableList<CharArray>,
        position: Triple<Int, Int, Char>,
        score: Int,
        sleep: Boolean = true,
        loops: Boolean = false
    ) {
        if (inArea(position, area)) {
            area[position.second][position.first] = position.third
        }
        for (y in 0..<area.size) {
            for (x in 0..<area[y].size) {
                print(area[y][x])
            }
            println()
        }
        println("position = (${position.first}, ${position.second})")
        if (loops) {
            println("loops = $score")
        } else {
            println("distance = $score")
        }
        if (sleep) Thread.sleep(100)
    }

    fun part1(input: List<String>): Int {
        val area: MutableList<CharArray> = emptyList<CharArray>().toMutableList()
        input.forEach() {
            area.add(it.toCharArray())
        }
        var distance = 0
        val directions = arrayOf('^', '>', 'v', '<')
        lateinit var position : Triple<Int, Int, Char>
        for (y in 0..<area.size) {
            for (x in 0..<area[y].size) {
                print(area[y][x])
                if (directions.contains(area[y][x])) {
                    position = Triple(x, y, area[y][x])
                }
            }
            println()
        }

        println("position = (${position.first}, ${position.second})")
        println("distance = $distance")
        Thread.sleep(1000)

        while (inArea(position, area)) {
            System.out.flush()
            // move
            var nextPos : Triple<Int, Int, Char>
            var collision : Boolean
            do {
                // next potential move
                nextPos = when (position.third) {
                    '^' -> Triple(position.first, position.second - 1, position.third)
                    'v' -> Triple(position.first, position.second + 1, position.third)
                    '<' -> Triple(position.first - 1, position.second, position.third)
                    '>' -> Triple(position.first + 1, position.second, position.third)
                    else -> position
                }
                // collision detection
                try {
                    if (area[nextPos.second][nextPos.first] == '#') {
                        collision = true
                        // rotation
                        position = Triple(position.first, position.second, directions[(directions.indexOf(position.third) + 1).rem(4)])
                    } else {
                        collision = false
                    }
                } catch (e: java.lang.IndexOutOfBoundsException) {
                    collision = false
                }
            } while (collision)
            // move position
            if (!inArea(nextPos, area) || area[nextPos.second][nextPos.first] != 'X') {
                distance++
            }
            area[position.second][position.first] = 'X'
            position = nextPos
            // print
            printMap(area, position, distance)
        }
        return distance
    }

    fun part2(input: List<String>): Int {
        val area: MutableList<CharArray> = emptyList<CharArray>().toMutableList()
        input.forEach() {
            area.add(it.toCharArray())
        }
        var loops = 0
        val directions = arrayOf('^', '>', 'v', '<')
        lateinit var position : Triple<Int, Int, Char>
        for (y in 0..<area.size) {
            for (x in 0..<area[y].size) {
                print(area[y][x])
                if (directions.contains(area[y][x])) {
                    position = Triple(x, y, area[y][x])
                }
            }
            println()
        }

        println("position = (${position.first}, ${position.second})")
        println("loops = $loops")
        Thread.sleep(1000)

        while (inArea(position, area)) {
            System.out.flush()
            // move
            var nextPos : Triple<Int, Int, Char>
            var collision : Boolean
            do {
                // next potential move
                nextPos = when (position.third) {
                    '^' -> Triple(position.first, position.second - 1, position.third)
                    'v' -> Triple(position.first, position.second + 1, position.third)
                    '<' -> Triple(position.first - 1, position.second, position.third)
                    '>' -> Triple(position.first + 1, position.second, position.third)
                    else -> position
                }
                // collision detection
                try {
                    if (area[nextPos.second][nextPos.first] == '#') {
                        collision = true
                        // rotation
                        position = Triple(position.first, position.second, directions[(directions.indexOf(position.third) + 1).rem(4)])
                    } else {
                        collision = false
                    }
                } catch (e: java.lang.IndexOutOfBoundsException) {
                    collision = false
                }
            } while (collision)
            // move position
            if (!inArea(nextPos, area) || area[nextPos.second][nextPos.first] != 'X') {
//                distance++
            }
            area[position.second][position.first] = 'X'
            position = nextPos
            // print
            // printMap(area, position, distance, true)
        }
        return loops
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
