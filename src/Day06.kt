fun main() {
    val directions = arrayOf('^', '>', 'v', '<')

    fun isInArea(
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
        for (y in 0..<area.size) {
            for (x in 0..<area[y].size) {
                if (x == position.first && y == position.second) {
                    print(position.third)
                } else {
                    print(area[y][x])
                }
            }
            println()
        }
        println("position = (${position.first}, ${position.second})")
        if (loops) {
            println("loops = $score")
        } else {
            println("distance = $score")
        }
        if (sleep) Thread.sleep(200)
    }

    fun newMark(
        area: MutableList<CharArray>,
        position: Triple<Int, Int, Char>,
        nextPos: Triple<Int, Int, Char>
    ): Char {
        val oldMark = area[position.second][position.first]
//        println("Marks: old = $oldMark, position = ${position.third}, next = ${position.third}")
        val newMark = when (oldMark) {
            '.' -> {
                if (nextPos.third == position.third) {
                    when (nextPos.third) {
                        '<' -> '←'
                        '>' -> '→'
                        '^' -> '↑'
                        'v' -> '↓'
                        else -> '!' // should not happen
                    }
                } else {
                    '+'
                }
            }

            '-' -> {
                when (nextPos.third) {
                    '<' -> '←'
                    '>' -> '→'
                    else -> '+'
                }
            }

            '|' -> {
                when (nextPos.third) {
                    '^' -> '↑'
                    'v' -> '↓'
                    else -> '+'
                }
            }

            '←' -> {
                when (nextPos.third) {
                    '<' -> '⥢'
                    '>' -> '↔'
                    else -> '+'
                }
            }

            '→' -> {
                when (nextPos.third) {
                    '<' -> '↔'
                    '>' -> '⥤'
                    else -> '+'
                }
            }

            '↑' -> {
                when (nextPos.third) {
                    '^' -> '⥣'
                    'v' -> '↕'
                    else -> '+'
                }
            }

            '↓' -> {
                when (nextPos.third) {
                    '^' -> '↕'
                    'v' -> '⥥'
                    else -> '+'
                }
            }

            '+' -> '+'
            '^' -> '↑'  // starting point
            else -> '?' // should not happen
        }
        return newMark
    }

    fun hasLoop(
        position: Triple<Int, Int, Char>,
        obstacle: Triple<Int, Int, Char>,
        startArea: List<CharArray>
    ): Boolean {
        var loop = false
        var pos = position
        val area = startArea.map {it.clone()}.toMutableList()
        // put obstacle and test if there will be a loop
        area[obstacle.second][obstacle.first] = 'O'
        println("obstacle: $obstacle")
        while (isInArea(pos, area) && !loop) {
            System.out.flush()
            // move
            var nextPos = pos
            var collision : Boolean
            do {
                // next potential move
                nextPos = when (nextPos.third) {
                    '^' -> Triple(pos.first, pos.second - 1, nextPos.third)
                    'v' -> Triple(pos.first, pos.second + 1, nextPos.third)
                    '<' -> Triple(pos.first - 1, pos.second, nextPos.third)
                    '>' -> Triple(pos.first + 1, pos.second, nextPos.third)
                    else -> pos
                }
                // collision detection
                try {
                    if (arrayOf('#', 'O').contains(area[nextPos.second][nextPos.first])) {
                        collision = true
                        // rotation
                        nextPos = Triple(pos.first, pos.second,
                            directions[(directions.indexOf(nextPos.third) + 1).rem(4)])
                    } else {
                        collision = false
                    }
                } catch (e: java.lang.IndexOutOfBoundsException) {
                    collision = false
                }
            } while (collision)
            // check if on the loop
            if(isInArea(nextPos, area)) {
                val oldMark = area[nextPos.second][nextPos.first]
//                println(oldMark)
                if ((oldMark == '←' && nextPos.third == '<') ||
                    (oldMark == '→' && nextPos.third == '>') ||
                    (oldMark == '↑' && nextPos.third == '^') ||
                    (oldMark == '↓' && nextPos.third == 'v')
                ) {
                    println("LOOP!")
                    loop = true
                }
            }
            // mark route
            area[pos.second][pos.first] = newMark(area, pos, nextPos)
            // move position
            pos = nextPos
            println("obstacle: $obstacle; nextPos: $nextPos")
            // print
            printMap(area, pos, -1, true, true)
        }
        return loop
    }

    fun part1(input: List<String>): Int {
        val area: MutableList<CharArray> = emptyList<CharArray>().toMutableList()
        input.forEach() {
            area.add(it.toCharArray())
        }
        var distance = 0
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
        Thread.sleep(200)

        while (isInArea(position, area)) {
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
            if (!isInArea(nextPos, area) || area[nextPos.second][nextPos.first] != 'X') {
                distance++
            }
            area[position.second][position.first] = 'X'
            position = nextPos
            // print
//            printMap(area, position, distance)
        }
        return distance
    }

    fun startPosition(area: MutableList<CharArray>): Triple<Int, Int, Char> {
        lateinit var position: Triple<Int, Int, Char>
        for (y in 0..<area.size) {
            for (x in 0..<area[y].size) {
                print(area[y][x])
                if (directions.contains(area[y][x])) {
                    position = Triple(x, y, area[y][x])
                }
            }
            println()
        }
        return position
    }

    fun part2(input: List<String>): Int {
        val area: MutableList<CharArray> = emptyList<CharArray>().toMutableList()
        input.forEach() {
            area.add(it.toCharArray())
        }
        var loops = 0

        var position: Triple<Int, Int, Char> = startPosition(area)
        val startPos : Triple<Int, Int, Char> = position

        println("position = (${position.first}, ${position.second})")
        println("loops = $loops")
        Thread.sleep(200)

        while (isInArea(position, area)) {
            System.out.flush()
            // move
            var nextPos = position
            var collision : Boolean
            do {
                // next potential move
                nextPos = when (nextPos.third) {
                    '^' -> Triple(position.first, position.second - 1, nextPos.third)
                    'v' -> Triple(position.first, position.second + 1, nextPos.third)
                    '<' -> Triple(position.first - 1, position.second, nextPos.third)
                    '>' -> Triple(position.first + 1, position.second, nextPos.third)
                    else -> position
                }
                // collision detection
                try {
                    if (arrayOf('#', 'O').contains(area[nextPos.second][nextPos.first])) {
                        collision = true
                        // rotation
                        nextPos = Triple(position.first, position.second, directions[(directions.indexOf(nextPos.third) + 1).rem(4)])
                    } else {
                        collision = false
                    }
                } catch (e: java.lang.IndexOutOfBoundsException) {
                    collision = false
                }
            } while (collision)
            // mark route
            area[position.second][position.first] = newMark(area, position, nextPos)
            // try to add obstacle
            if (isInArea(nextPos, area) && hasLoop(position, nextPos, area.toList())) {
                if (startPos.first != nextPos.first || startPos.second != nextPos.second) {
                    loops++
                }
            }
            // move position
            position = nextPos
            // print
//            printMap(area, position, loops, true, true)
        }
        return loops
    }

    // Test if implementation meets criteria from the description, like:
//    check(!hasLoop(listOf("....#.....",
//                          ".........#",
//                          "..........",
//                          "..#.......",
//                          ".....O.#..",
//                          "..........",
//                          ".#..^.....",
//                          "........#.",
//                          "#.........",
//                          "......#...")))

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
//    part1(input).println()
    part2(input).println()
}
