fun main() {

    fun findWord(input: List<String>, word: String, x: Int, y: Int, stepX: Int, stepY: Int): Boolean {
        try {
            for (i in 1..<word.length) {
                if (word[i] != input.elementAt(y + i * stepY).elementAt(x + i * stepX)) {
                    return false
                }
            }
            return true
        } catch (e: java.lang.IndexOutOfBoundsException) {
            return false
        }
    }

    fun part1(input: List<String>): Int {
        var count = 0
        val columns = input.first().length
        val rows = input.size
//        println("$input.size = $rows")
        /* direction:
        - 1. horizontal: XMAS
        - 2. horizontal reverse: SMAX
        - 3. r. diagonal
        - 4. r. diagonal reverse
        - 5. l. diagonal
        - 6. l. diagonal reverse
        - 7. vertical
        - 8. vertical reverse
         */
        val word = "XMAS"
        var x = 0
        var y = 0
        while (y < rows) {
            while (x < columns) {
                if (input.elementAt(y).elementAt(x) == 'X') {
                    // search in all directions
                    // ignore direction in case of out of bounds exception
                    if (findWord(input, word, x, y, 1, 1)) {
                        count++
                    }
                    if (findWord(input, word, x, y, 1, 0)) {
                        count++
                    }
                    if (findWord(input, word, x, y, 0, 1)) {
                        count++
                    }
                    if (findWord(input, word, x, y, -1, -1)) {
                        count++
                    }
                    if (findWord(input, word, x, y, -1, 0)) {
                        count++
                    }
                    if (findWord(input, word, x, y, 0, -1)) {
                        count++
                    }
                    if (findWord(input, word, x, y, 1, -1)) {
                        count++
                    }
                    if (findWord(input, word, x, y, -1, 1)) {
                        count++
                    }
                }
                x++
            }
            x = 0
            y++
        }
//        println("found $count matches")
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val columns = input.first().length
        val rows = input.size
//        println("$input.size = $rows")
        var x = 0
        var y = 0
        while (y < rows) {
            while (x < columns) {
                if (input.elementAt(y).elementAt(x) == 'A') {
                    // find "x" surrounding 4 letters
                    // find "+" surrounding 4 letters
                    // see if there are 2 'M' and 2 'S' in surrounding
                    // ignore out of bound exception as previously
                    try {
                        val surrounding = arrayOf(
                            input.elementAt(y + 1).elementAt(x + 1),
                            input.elementAt(y - 1).elementAt(x + 1),
                            input.elementAt(y + 1).elementAt(x - 1),
                            input.elementAt(y - 1).elementAt(x - 1)
                        )
//                        print("${surrounding.joinToString()}: ${surrounding.sortedArray().joinToString()}")
                        if (surrounding.sortedArray().joinToString() == "M, M, S, S") {
//                            println(" ook")
                            count++
                        } else {
//                            println(" nok")
                        }
                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        // do nothing
                    }
//                    try {
//                        val surrounding = arrayOf(
//                            input.elementAt(y + 1).elementAt(x),
//                            input.elementAt(y - 1).elementAt(x),
//                            input.elementAt(y).elementAt(x + 1),
//                            input.elementAt(y).elementAt(x - 1)
//                        )
////                        print("${surrounding.joinToString()}: ${surrounding.sortedArray().joinToString()}")
//                        if (surrounding.sortedArray().joinToString() == "M, M, S, S") {
////                            println(" ook")
//                            count++
//                        } else {
////                            println(" nok")
//                        }
//                    } catch (e: java.lang.IndexOutOfBoundsException) {
//                        // do nothing
//                    }
                }
                x++
            }
            x = 0
            y++
        }
//        println("found $count matches")
        return count
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    println("test data")
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    println("input data")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
