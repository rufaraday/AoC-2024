import java.util.Collections

fun main() {
    fun part1(input: List<String>): Int {
        var acc = 0

        // split lists
        var switch = false
        val ordering = mutableListOf<String>()
        val updates = mutableListOf<String>()
        input.forEach() {
            if (it == "") {
                switch = true
            } else {
                if (!switch) {
                    ordering.add(it)
                } else {
                    updates.add(it)
                }
            }
        }
//        ordering.println()
//        updates.println()

        // find if any rule is violated
        updates.forEach() {
            println(it)
            var invalid = false
            var i = 0; var j = 1
            val update = it.split(",").map { it.toInt() }
            while (i < update.lastIndex && !invalid) {
                j = i + 1
                while (j <= update.lastIndex && !invalid) {
                    val validator = update[j].toString().plus("|").plus(update[i])
                    println("checking \"$validator\"")
                    if (ordering.contains(validator)) {
                        println("invalid")
                        invalid = true
                    }
                    j++
                }
                i++
            }
            if (!invalid) {
//                println(update[update.lastIndex / 2])
                acc += update[update.lastIndex / 2]
            }
        }
        println(acc)
        return acc
    }

    fun part2(input: List<String>): Int {
        var acc = 0

        // split lists
        var switch = false
        val ordering = mutableListOf<String>()
        val updates = mutableListOf<String>()
        input.forEach() {
            if (it == "") {
                switch = true
            } else {
                if (!switch) {
                    ordering.add(it)
                } else {
                    updates.add(it)
                }
            }
        }
//        ordering.println()
//        updates.println()

        // correct violated rules
        var instructions : MutableList<Pair<Int, Int>>
        updates.forEach() {
            instructions = emptyList<Pair<Int, Int>>().toMutableList()
            println(it)
            var invalid = false
            var i = 0; var j: Int
            val update = it.split(",").map { it.toInt() }
            while (i < update.lastIndex) {
                j = i + 1
                while (j <= update.lastIndex) {
                    val validator = update[j].toString().plus("|").plus(update[i])
                    println("checking \"$validator\"")
                    if (ordering.contains(validator)) {
                        println("invalid")
                        invalid = true
                        instructions.add(update[j] to update[i])
                    }
                    j++
                }
                i++
            }
            if (invalid) {
                var ordered = it.split(",").map { it.toInt() }.toMutableList()
                ordered.println()
                instructions.forEach() {instruction ->
                    println("swap instruction $instruction")
                    println("index of ${instruction.first}: ${ordered.indexOf(instruction.first)}")
                    println("index of ${instruction.second}: ${ordered.indexOf(instruction.second)}")
                    Collections.swap(ordered, ordered.indexOf(instruction.first), ordered.indexOf(instruction.second))
                    println(ordered)
                }

                println("invalid $update -> $ordered")

                // add middle element to accumulator
                acc += ordered[ordered.lastIndex / 2]
            }
        }
        println(acc)
        return acc
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    println("part2 test test")
    val testTestInput = readInput("Day05_testtest")
    part2(testTestInput)

    println("test")
    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    println("part2 test")
    check(part2(testInput) == 123)

    println("real deal")
    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
