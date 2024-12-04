fun main() {
    fun checkLetter(letter: Char, word: String, array: Array<Boolean>): Int {
        println("~~$letter, $array")
        var i = 0
        while (i < word.length) {
            if (!array.elementAt(i)) {
                if (word.elementAt(i) == letter) {
                    array[i] = true
                } else {
                    break
                }
            }
            i++
        }
        if (i == word.length) {
            // clean array
            for (j in array.indices)
            array[j] = false
        }
        return i
    }

    fun part1(input: List<String>): Int {
        var count = 0
        val columns = input.first().length
        val rows = input.size
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
        var horizontal = arrayOf(false, false, false, false)
        var horizontalReverse = arrayOf(false, false, false, false)
        var x = 0
        var y = 0
        while (x < columns - 1) {
            if (checkLetter(input.elementAt(y).elementAt(x), word, horizontal) == 4) {
                println("horizontal mach ends at $x, $y")
                count++
            }
            while (y < rows - 1) {
                // do check
                y++
            }
            x++
        }
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
