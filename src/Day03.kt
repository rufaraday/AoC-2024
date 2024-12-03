
fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        val regex = "mul\\(\\d+,\\d+\\)".toRegex()
        for (s in input) {
            regex.findAll(s).forEach {
//                println(it.value)
                val numbers = "\\d+".toRegex().findAll(it.value).map{ it.value }
                count += numbers.first().toInt() * numbers.last().toInt()
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val regex = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)".toRegex()
        var enabled = true
        for (s in input) {
            regex.findAll(s).forEach {
//                println(it.value)
                if (it.value.startsWith("don't")) {
                    enabled = false
                } else if (it.value.startsWith("do(")) {
                    enabled = true
                } else if (it.value.startsWith("mul")) {
//                    print("~~ ${it.value}")
                    if (enabled) {
//                        println(" counts")
                        val numbers = "\\d+".toRegex().findAll(it.value).map { it.value }
                        count += numbers.first().toInt() * numbers.last().toInt()
                    } else {
                        // ignore mul
//                        println(" ignored")
                    }
                }
            }
        }
        return count
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")) == 161)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
