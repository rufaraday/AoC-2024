import kotlin.math.sin

fun main() {

    fun checkLetter(letter: Char, word: String, horizontal: Int, count: Int): Pair<Int, Int> {
        var horizontal1 = horizontal
        var count1 = count
        if (letter != word[horizontal1]) {
            // zeroing
            horizontal1 = 0
        }
        if (letter == word[horizontal1]) {
            horizontal1++
            if (horizontal1 == word.length) {
                // horizontal reaches end
                count1++
                horizontal1 = 0
            }
        }
        return Pair(count1, horizontal1)
    }

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
                val (c, h) = checkLetter(input.elementAt(y).elementAt(x), word, horizontal, count)
                count = c
                horizontal = h
                val (cr, hr) = checkLetter(input.elementAt(y).elementAt(columns - x - 1), word, horizontalReverse, count)
                count = cr
                horizontalReverse = hr
                x++
            }
            x = 0
            y++
        }
        x = 0; y = 0
        horizontal = 0; horizontalReverse = 0
        while (x < columns) {
            while (y < rows) {
                val (c, h) = checkLetter(input.elementAt(y).elementAt(x), word, horizontal, count)
                count = c
                horizontal = h
                val (cr, hr) = checkLetter(input.elementAt(rows - y - 1).elementAt(x), word, horizontalReverse, count)
                count = cr
                horizontalReverse = hr
                y++
            }
            y = 0
            x++
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
