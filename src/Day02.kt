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

    fun processReport(report: List<String>): Boolean {
//        println("-> $report")
        var safe = true
        var levelSkipped = false
        val increase = report[1].toInt() > report[0].toInt()
        val range = if (increase) -3..-1 else 1..3
        var i = 0
        while (i < report.size - 1) {
            var diff = report[i].toInt() - report[i + 1].toInt()
//            println("${report[i].toInt()} - ${report[i + 1].toInt()} = $diff")

            if (diff !in range) {
//                println("not in range")
                if (levelSkipped) {
//                    println("we've already skipped")
                    safe = false
                    break
                }
                if (i < report.size - 2) {
                    diff = report[i].toInt() - report[i + 2].toInt()
//                    println("${report[i].toInt()} - ${report[i + 2].toInt()} = $diff")
                    if (diff !in range) {
//                        println("skip next does not help")
                        safe = false
                        break
                    } else {
//                        println("next helped")
                        levelSkipped = true
                        safe = true
                        i++
                    }
                } else {
//                    // skip last as we still can
////                    println("skipping last")
//                    safe = true
//                    levelSkipped = true
                }
            }
            i++
        }
//        println("safe $safe")
        return safe
    }

    fun part2(input: List<String>): Int {

        // The levels are either all increasing or all decreasing.
        // Any two adjacent levels differ by at least one and at most three.
        var count = 0
        input.forEach() {
            val report = it.split(" ")
            if (processReport(report)) count++
            else if (processReport(report.reversed())) count++
            else println(it)
        }
        println(input.size)
        return count
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
//    println("PART1 test")
//    check(part1(testInput) == 2)
//    println("PART2 test")
//    check(part2(testInput) == 4)
//    println("PART2 custom check")
//    check(part2(listOf("41 42 45 47 48 49 53 51")) == 1)
//    check(part2(listOf("37 40 42 43 44 47 51")) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}
