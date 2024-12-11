import kotlin.time.measureTime

fun main() {
    fun part1(input: List<String>): Int {
        val stones = input[0].split(" ").map{ it.toLong() }.toMutableList()
//        println(stones)
        for (i in 0..24) {
            var s = 0
            while(s in stones.indices) {
//                println("i = $i, stones[$s] = ${stones[s]}")
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
        println("part 1, stone count ${stones.size}")
        return stones.size
    }

    fun changeStone(stone: Long, blinks: Int) : Long {
        var count = 1L
        var b = blinks
//        println("count[stone=$stone, blinks=$blinks]")
        if (b > 0) {
            b--
            val length = stone.toString().length
            count = when {
                stone == 0L -> changeStone(1L, b)
                length.rem(2) == 0 -> {
                    val value = stone
                    changeStone(value.toString().drop(length.div(2)).toLong(), b) +
                            changeStone(value.toString().dropLast(length.div(2)).toLong(), b)
                }

                else -> changeStone(stone * 2024, b)
            }
        }
//        println("count[stone=$stone, blinks=$blinks] = $count")
//        Thread.sleep(1000)
        return count
    }

    fun part2(input: List<String>, blinks: Int): Long {
        var count = 0L
        val stones = input[0].split(" ").map{ it.toLong() }.toMutableList()
//        println("part 2, stones $stones")
        stones.forEach() {
//            println("part 2, stone $it")
            count += changeStone(it, blinks)
        }
        println("part 2, stone count $count")
        return count
    }

//    println("PART 1, test data, 25 blinks")
//    check(part1(listOf("125")) == 19025)
//    println("PART 2, test data, 25 blinks")
//    check(part2(listOf("125"), 25) == 19025L)

    // Test if implementation meets criteria from the description, like:
    var timeTaken = measureTime {
        println("PART 1, test data, 25 blinks")
        check(part1(listOf("125 17")) == 55312)
    }
    println(timeTaken)
    timeTaken = measureTime {
        println("PART 2, test data, 6 blinks")
        check(part2(listOf("125 17"), 6) == 22L)
    }
    println(timeTaken)
    timeTaken = measureTime {
        println("PART 2, test data, 25 blinks")
        check(part2(listOf("125 17"), 25) == 55312L)
    }
    println(timeTaken)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day11_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    timeTaken = measureTime {
        println("PART 1, input data, 25 blinks")
        part1(input).println()
    }
    println(timeTaken)
    timeTaken = measureTime {
        println("PART 2, input data, 25 blinks")
        part2(input, 25).println()
    }
    println(timeTaken)
//    println("PART 2, input data, 75 blinks")
//    part2(input, 75).println()
}
