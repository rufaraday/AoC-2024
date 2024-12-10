fun main() {
    fun checkTrail(
        area: List<List<Int>>,
        trailStart: Pair<Int, Int>,
        trails: MutableSet<Pair<Int, Int>>
    ): Set<Pair<Int, Int>> {
        val startX = trailStart.first
        val startY = trailStart.second
        val startVal = area[startY][startX]
        var dX: Int
        var dY: Int
        println("check ${startX to startY}")
        try {
            dX = startX + 1
            dY = startY
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    println("add ${dX to dY}")
                    trails.add(dX to dY)
                } else {
                    trails.addAll(checkTrail(area, dX to dY, trails))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX - 1
            dY = startY
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkTrail(area, dX to dY, trails))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX
            dY = startY + 1
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkTrail(area, dX to dY, trails))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX
            dY = startY - 1
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkTrail(area, dX to dY, trails))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        return trails
    }

    fun trailScore(area: List<List<Int>>, trailStart: Pair<Int, Int>): Int {
        println("trailStart = $trailStart")
        val tops : MutableSet<Pair<Int, Int>> = emptySet<Pair<Int, Int>>().toMutableSet()
        tops += checkTrail(area, trailStart, tops)
        println("trailStart = $trailStart : $tops")
        return tops.size
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        val trailStarts : MutableSet<Pair<Int, Int>> = emptySet<Pair<Int, Int>>().toMutableSet()
        val area : MutableList<List<Int>> = emptyList<List<Int>>().toMutableList()
        println(input.size)
        for (i in 0..input.lastIndex) {
            println(input[i])
            area.add(input[i].map { it.digitToInt() })
            for (j in 0..area[i].lastIndex) {
                if (area[i][j] == 0) {
                    trailStarts.add(j to i)
                }
            }
        }
        trailStarts.forEach { sum += trailScore(area.toList(), it) }
        return sum
    }

    fun checkDistinctTrail(
        area: List<List<Int>>,
        trailStart: Pair<Int, Int>
    ): List<Pair<Int, Int>> {
        val startX = trailStart.first
        val startY = trailStart.second
        val startVal = area[startY][startX]
        var dX: Int
        var dY: Int
        val trails : MutableList<Pair<Int, Int>> = emptyList<Pair<Int, Int>>().toMutableList()
        println("check ${startX to startY}")
        try {
            dX = startX + 1
            dY = startY
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    println("add ${dX to dY}")
                    trails.add(dX to dY)
                } else {
                    trails.addAll(checkDistinctTrail(area, dX to dY))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX - 1
            dY = startY
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkDistinctTrail(area, dX to dY))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX
            dY = startY + 1
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkDistinctTrail(area, dX to dY))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        try {
            dX = startX
            dY = startY - 1
            if (area[dY][dX] == startVal + 1) {
                if (area[dY][dX] == 9) {
                    trails.add(dX to dY)
                    println("add ${dX to dY}")
                } else {
                    trails.addAll(checkDistinctTrail(area, dX to dY))
                }
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            // out of area
        }
        return trails
    }

    fun trailDistinctScore(area: List<List<Int>>, trailStart: Pair<Int, Int>): Int {
        println("trailStart = $trailStart")
        val tops : MutableList<Pair<Int, Int>> = emptyList<Pair<Int, Int>>().toMutableList()
        tops += checkDistinctTrail(area, trailStart)
        println("trailStart = $trailStart : $tops")
        return tops.size
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val trailStarts : MutableSet<Pair<Int, Int>> = emptySet<Pair<Int, Int>>().toMutableSet()
        val area : MutableList<List<Int>> = emptyList<List<Int>>().toMutableList()
        println(input.size)
        for (i in 0..input.lastIndex) {
            println(input[i])
            area.add(input[i].map { it.digitToInt() })
            for (j in 0..area[i].lastIndex) {
                if (area[i][j] == 0) {
                    trailStarts.add(j to i)
                }
            }
        }
        trailStarts.forEach { sum += trailDistinctScore(area.toList(), it) }
        return sum
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 36)
    check(part2(testInput) == 81)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
