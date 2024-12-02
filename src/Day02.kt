fun main() {
    fun part1(input: List<String>): Int {
        // The levels are either all increasing or all decreasing.
        // Any two adjacent levels differ by at least one and at most three.
        var count = 0
        input.forEach() {
            println(it)
            var safe = true
            val reports = it.split(" ")
            val increase = reports[1].toInt() > reports[0].toInt()
            for (i in 1..<reports.size) {
                val diff = reports[i].toInt() - reports[i - 1].toInt()
                if (increase) {
                    if (diff !in 1..3) {
                        safe = false
                        break
                    }
                } else {
                    if (diff !in -3..-1) {
                        safe = false
                        break
                    }
                }
            }
            if (safe) count++
        }
        return count
    }

    fun part2(input: List<String>): Int {

        // The levels are either all increasing or all decreasing.
        // Any two adjacent levels differ by at least one and at most three.
        var count = 0
        input.forEach() {
//            println(it)
            var safe = true
            val reports = it.split(" ")
            val increase = reports[1].toInt() > reports[0].toInt()
            for (i in 1..<reports.size) {
                val diff = reports[i].toInt() - reports[i - 1].toInt()
                println("${reports[i].toInt()} - ${reports[i - 1].toInt()} = $diff")
                if (increase) {
                    if (diff !in 1..3) {
                        println("not in range")
                        if (reports[i].toInt() - reports[i - 2].toInt() !in 1..3) {
                            println("${reports[i].toInt()} - ${reports[i - 2].toInt()}")
                            println("skip one does not help - no break yet")
                            safe = false

                            println("checking further")
                            if (i == reports.size - 1) {
                                println("last - break")
                                safe = false
                                break
                            }
                            if (reports[i + 1].toInt() - reports[i - 1].toInt() !in 1..3) {
                                println("${reports[i + 1].toInt()} - ${reports[i - 1].toInt()}")
                                println("skip one does not help - break")
                                safe = false
                                break
                            }
                            println("next helped")
                            safe = true
                        }
                    }
                } else {
                    if (diff !in -3..-1) {
                        if (i == reports.size - 1) {
                            safe = false
                            break
                        }
                        if (reports[i + 1].toInt() - reports[i - 1].toInt() !in -3..-1) {
                            safe = false
                            break
                        }
                    }
                }
            }
//            println("safe $safe")
            if (safe) count++
            else println(it)
        }
        println(input.size)
        return count
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    println("PART1 test")
    check(part1(testInput) == 2)
    println("PART2 test")
    check(part2(testInput) == 4)
    println("PART2 custom check")
    check(part2(listOf("41 42 45 47 48 49 53 51")) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
