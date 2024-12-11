fun main() {

    fun part1(input: List<String>): Int {
        val antinodes = mutableSetOf<Pair<Int, Int>>()
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        val bound = input[0].length to input.size
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
                        val d = p1.plus(p2)
                        val ap1 = p1.plus(d)
                        val ap2 = p2.plus(d)
                        if (ap1.isIn(bound)) {
                            antinodes += ap1
                        }
                        if (ap2.isIn(bound)) {
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
        val bound = input[0].length to input.size
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
                        val ap1 = p1.plus(d)
                        val ap2 = p2.minus(d)
                        if (ap1.isIn(bound)) {
                            antinodes += ap1
                        }
                        if (ap2.isIn(bound)) {
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