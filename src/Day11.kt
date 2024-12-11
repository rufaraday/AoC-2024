fun main() {
    fun part1(input: List<String>): Int {
        val stones = input[0].split(" ").map{ it.toLong() }.toMutableList()
        println(stones)
        for (i in 0..24) {
            var s = 0
            while(s in stones.indices) {
                println("i = $i, stones[$s] = ${stones[s]}")
                val length = stones[s].toString().length
                when {
                    stones[s] == 0L -> stones[s] = 1L
                    length.rem(2) == 0 -> {
                        val value = stones[s]
                        stones[s] = value.toString().drop(length.div(2)).toLong()
                        stones.add(s, value.toString().dropLast(length.div(2)).toLong())
                        s++
                    }
                    else -> stones[s] = stones[s] * 2024
                }
//                println(stones)
                s++
            }
        }
        return stones.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("125 17")) == 55312)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day11_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
