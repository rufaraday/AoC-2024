import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Prepare next day
 * 1. copy initial file
 *     git show 2fba95d80bca71e85f5adb8d23f1ae09d06fed01:src/Day01.kt > src/DayXX.kt
 * 2. change input file to DayXX:
 *     val input = readInput("DayXX")
 */

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun Pair<Int, Int>.minus(p: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first - p.first) to (this.second - p.second)
}

fun Pair<Int, Int>.plus(p: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + p.first) to (this.second + p.second)
}

fun Pair<Int, Int>.isIn(p: Pair<Int, Int>): Boolean {
    return this.first in 0..<p.first && this.second in 0..<p.second
}