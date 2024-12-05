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
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
