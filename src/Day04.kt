import kotlin.math.sin

fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        val columns = input.first().length
        val rows = input.size
        println("$input.size = $rows")
        /* direction:
        - 1. horizontal: XMAS
        - 2. horizontal reverse: SMAX
        - 3. r. diagonal
        - 4. r. diagonal reverse
        - 5. l. diagonal
        - 6. l. diagonal reverse
        - 7.  vertical
        - 8. vertical reverse
         */
        val word = "XMAS"
        var horizontal = 0
        var horizontalReverse = 0
        var x = 0
        var y = 0
        while (y < rows) {
            while (x < columns) {
                println("letter ${input.elementAt(y).elementAt(x)} at ($x, $y), looking for word[$horizontal] = ${word[horizontal]}")
                if (input.elementAt(y).elementAt(x) != word[horizontal]) {
                    println("zeroing")
                    horizontal = 0
                }
                println("letter ${input.elementAt(y).elementAt(x)} at ($x, $y), looking for word[$horizontal] = ${word[horizontal]}")
                if (input.elementAt(y).elementAt(x) == word[horizontal]) {
                    horizontal++
                    println("got letter no $horizontal at ($x, $y)")
                    if (horizontal == word.length) {
                        println("horizontal mach ends at ($x, $y)")
                        count++
                        horizontal = 0
                    }
                }
                x++
            }
            x = 0
            y++
        }
        println("found $count matches")
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    println("test data")
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)

    println("input data")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
