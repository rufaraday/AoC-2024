import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        // What is the fewest tokens you would have to spend to win all possible prizes?
        // You estimate that each button would need to be pressed no more than 100 times to win a prize.
        var cost = 0
        for (i in 0.. input.size / 4) {
            val (dXA, dYA) = "Button A: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(input[4*i])!!.destructured
            val (dXB, dYB) = "Button B: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(input[4*i + 1])!!.destructured
            val (xP, yP) = "Prize: X=(\\d+), Y=(\\d+)".toRegex().find(input[4*i + 2])!!.destructured
            val solutions = mutableListOf<Pair<Int, Int>>()
            var min = Int.MAX_VALUE
            for (a in 0..<100) {
                for (b in 0..<100) {
                    if ((a * dXA.toInt() + b * dXB.toInt() == xP.toInt()) &&
                        (a * dYA.toInt() + b * dYB.toInt() == yP.toInt())) {
                        solutions += a to b
                        if (3 * a + b < min) {
                            min = 3 * a + b
                        }
                    }
                }
            }
            if (min == Int.MAX_VALUE) {
                min = 0
            }
            cost += min
            println("dXA=$dXA, dYA=$dYA; dXB=$dXB, dYB=$dYB; xP=$xP, yP=$yP: $solutions")
        }
        return cost
    }

    fun part2(input: List<String>): Long {
        // What is the fewest tokens you would have to spend to win all possible prizes?
        // You estimate that each button would need to be pressed no more than 100 times to win a prize.
        var cost = 0L
        for (i in 0.. input.size / 4) {
            val (dXA, dYA) = "Button A: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(input[4*i])!!.destructured.toList().map {it.toInt()}
            val (dXB, dYB) = "Button B: X\\+(\\d+), Y\\+(\\d+)".toRegex().find(input[4*i + 1])!!.destructured.toList().map {it.toInt()}
            val (xP, yP) = "Prize: X=(\\d+), Y=(\\d+)".toRegex().find(input[4*i + 2])!!.destructured.toList().map {it.toInt()}
//            val solutions = mutableListOf<Pair<Long, Long>>()
            print("dXA=$dXA, dYA=$dYA; dXB=$dXB, dYB=$dYB; xP=$xP, yP=$yP")
            val pX = (xP + 10000000000000).toBigInteger()
            val pY = (yP + 10000000000000).toBigInteger()

            val b = (pY.multiply(dXA.toBigInteger()) - pX.multiply(dYA.toBigInteger())).divide((dYB * dXA - dXB * dYA).toBigInteger())
            val a = (pY - b.multiply(dYB.toBigInteger())) / dYA.toBigInteger()

            println(" -> a = $a, b = $b")

            println("check pY - b.multiply(dYB.toBigInteger()) = $pY - $b * $dYB = ${pY - b.multiply(dYB.toBigInteger())}")

            println(a * dXA.toBigInteger() + b * dXB.toBigInteger())
            println(a * dYA.toBigInteger() + b * dYB.toBigInteger())

            if ((pX == a * dXA.toBigInteger() + b * dXB.toBigInteger()) && (pY == a * dYA.toBigInteger() + b * dYB.toBigInteger())) {
                println()
                cost += (a * BigInteger("2") + b).toLong()
            } else {
                println(" -xX not a solution")
            }
        }
        return cost
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("Button A: X+94, Y+34\n" +
//            "Button B: X+22, Y+67\n" +
//            "Prize: X=8400, Y=5400\n" +
//            "\n")) == 280)
//
//    check(part1(listOf("Button A: X+26, Y+66\n" +
//            "Button B: X+67, Y+21\n" +
//            "Prize: X=12748, Y=12176\n" +
//            "\n")) == 0)
//
//    check(part1(listOf("Button A: X+17, Y+86\n" +
//            "Button B: X+84, Y+37\n" +
//            "Prize: X=7870, Y=6450\n" +
//            "\n")) == 200)
//
//    check(part1(listOf("Button A: X+69, Y+23\n" +
//            "Button B: X+27, Y+71\n" +
//            "Prize: X=18641, Y=10279\n" +
//            "")) == 0)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 480)
//    check(part2(testInput) == 480L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
