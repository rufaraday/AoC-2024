fun main() {
    fun inBounds(ap1: Pair<Int, Int>, boundX: Int, boundY: Int) =
        ap1.first in 0..<boundX && ap1.second in 0..<boundY

    fun plusDelta(
        p1: Pair<Int, Int>,
        d: Pair<Int, Int>
    ) = (p1.first + d.first) to (p1.second + d.second)

    fun minusDelta(
        p2: Pair<Int, Int>,
        d: Pair<Int, Int>
    ) = (p2.first - d.first) to (p2.second - d.second)

    fun part1(input: List<String>): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val boundX = input[0].length
        val boundY = input.size
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != '.') {
                    if (!antennas.contains(input[y][x])) {
                        antennas[input[y][x]] = mutableListOf()
                    }
                    antennas[input[y][x]]?.add(Pair(x, y))
                }
            }
        }
        antennas.forEach() {antenna ->
            for (i in antenna.value.indices) {
                for (j in antenna.value.indices) {
                    if (i != j) {
                        val p1 = antenna.value[i]
                        val p2 = antenna.value[j]
                        val d = minusDelta(p1, p2)
                        val ap1 = plusDelta(p1, d)
                        val ap2 = minusDelta(p2, d)
                        if (inBounds(ap1, boundX, boundY)) {
                            antinodes += ap1
                        }
                        if (inBounds(ap2, boundX, boundY)) {
                            antinodes += ap2
                        }
                    }
                }
            }
        }
        println(antennas)
        return antinodes.size
    }

    fun part2(input: List<String>): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val boundX = input[0].length
        val boundY = input.size
        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] != '.') {
                    if (!antennas.contains(input[y][x])) {
                        antennas[input[y][x]] = mutableListOf()
                    }
                    antennas[input[y][x]]?.add(Pair(x, y))
                }
            }
        }
        antennas.forEach() {antenna ->
            for (i in antenna.value.indices) {
                for (j in antenna.value.indices) {
                    if (i != j) {
                        val p1 = antenna.value[i]
                        val p2 = antenna.value[j]
                        val d = p1.minus(p2)
                        val ap1 = plusDelta(p1, d)
                        val ap2 = p2.minus(d)
                        if (inBounds(ap1, boundX, boundY)) {
                            antinodes += ap1
                        }
                        if (inBounds(ap2, boundX, boundY)) {
                            antinodes += ap2
                        }
                    }
                }
            }
        }
        println(antennas)
        return antinodes.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day08_test.txt` file:
//    val testInput = readInput("Day08_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day08.txt` file.
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

private fun Pair<Int, Int>.minus(p: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first - p.first) to (this.second - p.second)
}
