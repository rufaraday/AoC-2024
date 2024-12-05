import java.util.Collections

fun main() {
    fun isValid(
        update: List<Int>,
        ordering: MutableList<String>
    ): Boolean {
        var valid = true
        var i = 0;
        var j: Int
        while (i < update.lastIndex && valid) {
            j = i + 1
            while (j <= update.lastIndex && valid) {
                val validator = update[j].toString().plus("|").plus(update[i])
    //                    println("checking \"$validator\"")
                if (ordering.contains(validator)) {
                    println("invalid $validator")
                    valid = false
                }
                j++
            }
            i++
        }
        return valid
    }

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
            val update = it.split(",").map { it.toInt() }
            if (isValid(update, ordering)) {
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
        updates.forEach() {
            println(it)
            var invalid = false
            var i = 0; var j: Int
            val update = it.split(",").map { it.toInt() }.toMutableList()
            while (i < update.lastIndex) {
                j = 0
                while (j <= update.lastIndex) {
                    val validator = update[j].toString().plus("|").plus(update[i])
//                    println("checking \"$validator\"")
                    if (ordering.contains(validator)) {
//                        println("invalid")
                        invalid = true
                        Collections.swap(update, j, i)
                    }
                    j++
                }
                i++
            }
            update.println()
            if (invalid) {
                println("validation: ${isValid(update, ordering)}")
//                assert(isValid(ordered, ordering))
                if (!isValid(update, ordering))
                    throw RuntimeException()

                // add middle element to accumulator
                acc += update[update.lastIndex / 2]
            }
        }
        println(acc)
        return acc
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    val checkInput = readInput("Day05_check")
//    part1(checkInput)
    part2(checkInput)

//    println("part2 test test")
    val testTestInput = readInput("Day05_testtest")
//    part2(testTestInput)
//
//    println("test")
//    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
//    check(part1(testInput) == 143)
//    println("part2 test")
//    check(part2(testInput) == 123)
//
//    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
//    println("real deal part 1")
//    part1(input).println()
//    println("real deal part 2")
//    part2(input).println()
}
