fun main() {
    // Everything after this is in red
    val red = "\u001b[31m"

    // Resets previous color codes
    val reset = "\u001b[0m"

    val bold = "\u001B[1m";

    fun part1(input: List<String>): Int {
        val area = mutableListOf<MutableList<Char>>()
        input.forEach {
            area.add(it.toMutableList())
        }
        printCharMatrix(area)
        val vector = 1 to 0
        val position = findPosition('S', area)
        println("position $bold$position$reset vector $bold$vector$reset")
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val firstTestInput = readInput("Day16_test_first")
    check(part1(firstTestInput) == 7036)
    val secondTestInput = readInput("Day16_test_first")
    check(part1(secondTestInput) == 11048)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day16")
    part1(input).println()
    part2(input).println()
}
