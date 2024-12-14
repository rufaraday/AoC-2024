fun main() {
    fun part1(input: List<String>, width: Int, height: Int, seconds: Int): Int {
        // Predict the motion of the robots in your list within a space which is 101 tiles wide and 103 tiles tall.
        // What will the safety factor be after exactly 100 seconds have elapsed?
        val robots = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        input.forEach {
            var (pX, pY, vX, vY) = """p=(\d+),(\d+) v=(-?\d+),(-?\d+)""".toRegex().find(it)!!.destructured.toList().map {it.toInt()}
            println("$pX, $pY, $vX, $vY")
            robots += (Math.floorMod(pX + vX * seconds, width) to Math.floorMod(pY + vY * seconds, height)) to (vX to vY)
        }
        // 0...49 50 51..100
        var firstQuadrant = 0
        var secondQuadrant = 0
        var thirdQuadrant = 0
        var fourthQuadrant = 0
        robots.forEach {r ->
            println(r)
            if (r.first.first in 0..<width/2 && r.first.second in 0..<height/2) {
                firstQuadrant++
            } else if (r.first.first in width/2+1..<width && r.first.second in 0..<height/2) {
                secondQuadrant++
            } else if (r.first.first in 0..<width/2 && r.first.second in height/2+1..<height) {
                thirdQuadrant++
            } else if (r.first.first in width/2+1..<width && r.first.second in height/2+1..<height) {
                fourthQuadrant++
            }
        }
        println("$firstQuadrant * $secondQuadrant * $thirdQuadrant * $fourthQuadrant")
        return firstQuadrant * secondQuadrant * thirdQuadrant * fourthQuadrant
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("p=2,4 v=2,-3"), 11, 7, 5) == 0)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day14_test")
    check(part1(testInput, 11, 7, 100) == 12)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day14")
    part1(input, 101, 103, 100).println()
    part2(input).println()
}
